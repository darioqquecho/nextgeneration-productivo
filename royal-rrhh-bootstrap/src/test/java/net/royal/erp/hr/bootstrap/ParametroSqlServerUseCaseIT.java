package net.royal.erp.hr.bootstrap;

import net.royal.erp.framework.audit.*;
import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.licensing.InMemoryLicenseChecker;
import net.royal.erp.framework.security.InMemoryPermissionChecker;
import net.royal.erp.framework.security.UseCaseGuards;
import net.royal.erp.hr.application.maestros.parametro.mantenimiento.dto.*;
import net.royal.erp.hr.application.maestros.parametro.mantenimiento.usecase.*;
import net.royal.erp.hr.domain.parametro.ParametroId;
import net.royal.erp.hr.infrastructure.maestros.parametro.sqlserver.SqlServerMantenimientoTablaParametrosV1Adapter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration test manual contra SQL Server real para HR_Parametros.
 */
@EnabledIfSystemProperty(named = "it.sqlserver", matches = "true")
class ParametroSqlServerUseCaseIT {
	@Test
	void crearParametroContraSqlServerDejandoRegistro() {
		SqlServerMantenimientoTablaParametrosV1Adapter repository =
				new SqlServerMantenimientoTablaParametrosV1Adapter(jdbcTemplate());
		MantenimientoTablaParametrosV1UseCase mantenimiento = new MantenimientoTablaParametrosV1UseCase(repository, guards(),
				auditPort());
		String compania = System.getProperty("it.sqlserver.compania", "COMP01");
		String codigo = System.getProperty("it.sqlserver.codigo", "ITVISIBLE");
		String nombre = System.getProperty("it.sqlserver.nombre", "Parametro visible desde IT");

		repository.deleteById(new ParametroId(compania, codigo));
		var creado = mantenimiento.crear(new CrearParametroCommand(compania, codigo, nombre), context("Registrar"));

		assertEquals("CREADO", creado.estado());
		assertTrue(repository.existsById(new ParametroId(compania, codigo)));
	}

	@Test
	void crudParametroContraSqlServer() {
		SqlServerMantenimientoTablaParametrosV1Adapter repository =
				new SqlServerMantenimientoTablaParametrosV1Adapter(jdbcTemplate());
		UseCaseGuards guards = guards();
		AuditPort audit = auditPort();
		String compania = System.getProperty("it.sqlserver.compania", "COMP01");
		String codigo = ("IT" + UUID.randomUUID().toString().replace("-", "")).substring(0, 10).toUpperCase();

		repository.deleteById(new ParametroId(compania, codigo));
		try {
			MantenimientoTablaParametrosV1UseCase mantenimiento = new MantenimientoTablaParametrosV1UseCase(repository, guards,
					audit);

			var creado = mantenimiento.crear(new CrearParametroCommand(compania, codigo, "Parametro IT SQL"),
					context("Registrar"));
			assertEquals("CREADO", creado.estado());

			var obtenido = mantenimiento.obtener(new ObtenerParametroQuery(compania, codigo), context("Obtener"));
			assertEquals("Parametro IT SQL", obtenido.nombre());
			assertEquals("admin", obtenido.ultimoUsuario());

			var actualizado = mantenimiento.actualizar(new ActualizarParametroCommand(compania, codigo, "Parametro IT SQL 2"),
					context("Actualizar"));
			assertEquals("ACTUALIZADO", actualizado.estado());

			var inactivo = mantenimiento.cambiarEstado(new CambiarEstadoParametroCommand(compania, codigo, "INACTIVO"),
					context("Cambiar de Estado"));
			assertEquals("INACTIVO", inactivo.estado());

			var eliminado = mantenimiento.eliminar(new EliminarParametroCommand(compania, codigo), context("Eliminar"));
			assertTrue(eliminado.eliminado());
			assertFalse(repository.existsById(new ParametroId(compania, codigo)));
		} finally {
			repository.deleteById(new ParametroId(compania, codigo));
		}
	}

	private JdbcTemplate jdbcTemplate() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(required("it.sqlserver.url"));
		dataSource.setUsername(required("it.sqlserver.username"));
		dataSource.setPassword(required("it.sqlserver.password"));
		return new JdbcTemplate(dataSource);
	}

	private AuditPort auditPort() {
		return new CompositeAuditAdapter(new ConsoleAuditAdapter(), new JdbcFunctionalAuditAdapter(jdbcTemplate()));
	}

	private FunctionalContext context(String functionality) {
		return new FunctionalContext("default", "demo-client", "admin", "HR", "Maestros", functionality,
				"Mantenimiento de Parametro", "V1", null, null, Instant.now());
	}

	private UseCaseGuards guards() {
		InMemoryPermissionChecker permissions = new InMemoryPermissionChecker();
		permissions.grant("admin", "HR_MANTENIMIENTO_DE_PARAMETRO");
		InMemoryLicenseChecker licenses = new InMemoryLicenseChecker();
		licenses.enable("demo-client", "HR");
		return new UseCaseGuards(permissions, licenses);
	}

	private String required(String property) {
		String value = System.getProperty(property);
		if (value == null || value.isBlank()) {
			throw new IllegalArgumentException("Propiedad requerida: " + property);
		}
		return value;
	}
}
