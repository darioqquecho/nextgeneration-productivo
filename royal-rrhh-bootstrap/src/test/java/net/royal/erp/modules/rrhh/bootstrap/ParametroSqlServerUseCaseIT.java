package net.royal.erp.modules.rrhh.bootstrap;

import net.royal.erp.framework.audit.AuditPort;
import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.licensing.InMemoryLicenseChecker;
import net.royal.erp.framework.security.InMemoryPermissionChecker;
import net.royal.erp.modules.rrhh.application.common.UseCaseGuards;
import net.royal.erp.modules.rrhh.application.parametro.dto.*;
import net.royal.erp.modules.rrhh.application.parametro.usecase.*;
import net.royal.erp.modules.rrhh.domain.parametro.ParametroId;
import net.royal.erp.modules.rrhh.infrastructure.audit.ConsoleAuditAdapter;
import net.royal.erp.modules.rrhh.infrastructure.parametro.SqlServerMantenimientoTablaParametrosV1Adapter;
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
		MantenimientoTablaParametrosUseCase mantenimiento = new MantenimientoTablaParametrosV1UseCase(repository, guards(),
				new ConsoleAuditAdapter());
		String compania = System.getProperty("it.sqlserver.compania", "COMP01");
		String codigo = System.getProperty("it.sqlserver.codigo", "ITVISIBLE");
		String nombre = System.getProperty("it.sqlserver.nombre", "Parametro visible desde IT");
		FunctionalContext context = new FunctionalContext("default", "demo-client", "admin", "HR", "Maestros",
				"Registrar", "Mantenimiento de Parametro", "V1", null, null, Instant.now());

		repository.deleteById(new ParametroId(compania, codigo));
		var creado = mantenimiento.crear(new CrearParametroCommand(compania, codigo, nombre), context);

		assertEquals("CREADO", creado.estado());
		assertTrue(repository.existsById(new ParametroId(compania, codigo)));
	}

	@Test
	void crudParametroContraSqlServer() {
		SqlServerMantenimientoTablaParametrosV1Adapter repository =
				new SqlServerMantenimientoTablaParametrosV1Adapter(jdbcTemplate());
		UseCaseGuards guards = guards();
		AuditPort audit = new ConsoleAuditAdapter();
		String compania = System.getProperty("it.sqlserver.compania", "COMP01");
		String codigo = ("IT" + UUID.randomUUID().toString().replace("-", "")).substring(0, 10).toUpperCase();
		FunctionalContext context = new FunctionalContext("default", "demo-client", "admin", "HR", "Maestros",
				"Registrar", "Mantenimiento de Parametro", "V1", null, null, Instant.now());

		repository.deleteById(new ParametroId(compania, codigo));
		try {
			MantenimientoTablaParametrosUseCase mantenimiento = new MantenimientoTablaParametrosV1UseCase(repository, guards,
					audit);

			var creado = mantenimiento.crear(new CrearParametroCommand(compania, codigo, "Parametro IT SQL"), context);
			assertEquals("CREADO", creado.estado());

			var obtenido = mantenimiento.obtener(new ObtenerParametroQuery(compania, codigo), context);
			assertEquals("Parametro IT SQL", obtenido.nombre());
			assertEquals("admin", obtenido.ultimoUsuario());

			var actualizado = mantenimiento.actualizar(new ActualizarParametroCommand(compania, codigo, "Parametro IT SQL 2"),
					context);
			assertEquals("ACTUALIZADO", actualizado.estado());

			var inactivo = mantenimiento.cambiarEstado(new CambiarEstadoParametroCommand(compania, codigo, "INACTIVO"),
					context);
			assertEquals("INACTIVO", inactivo.estado());

			var eliminado = mantenimiento.eliminar(new EliminarParametroCommand(compania, codigo), context);
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
