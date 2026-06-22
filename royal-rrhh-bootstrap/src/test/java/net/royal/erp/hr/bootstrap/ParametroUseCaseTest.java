package net.royal.erp.hr.bootstrap;

import net.royal.erp.framework.audit.*;
import net.royal.erp.framework.application.RoyalFunctionalVersionProxyFactory;
import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.licensing.InMemoryLicenseChecker;
import net.royal.erp.framework.security.InMemoryPermissionChecker;
import net.royal.erp.framework.versioning.*;
import net.royal.erp.framework.security.UseCaseGuards;
import net.royal.erp.hr.application.maestros.parametro.aprobacionmasiva.dto.*;
import net.royal.erp.hr.application.maestros.parametro.aprobacionmasiva.usecase.*;
import net.royal.erp.hr.application.maestros.parametro.mantenimiento.dto.*;
import net.royal.erp.hr.application.maestros.parametro.mantenimiento.usecase.*;
import net.royal.erp.hr.application.maestros.parametro.reporte.dto.*;
import net.royal.erp.hr.application.maestros.parametro.reporte.usecase.*;
import net.royal.erp.hr.domain.parametro.Parametro;
import net.royal.erp.hr.domain.parametro.ParametroId;
import net.royal.erp.hr.infrastructure.maestros.parametro.inmemory.InMemoryParametroRepositoryAdapter;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Implementa: - MOD-012 CU-001 Mantenimiento de Tabla Parametros.
 */
class ParametroUseCaseTest {
	@Test
	void crearParametroOk() {
		InMemoryPermissionChecker p = new InMemoryPermissionChecker();
		p.grant("admin", "HR_MANTENIMIENTO_DE_PARAMETRO");
		InMemoryLicenseChecker l = new InMemoryLicenseChecker();
		l.enable("demo-client", "HR");
		AuditPort audit = new ConsoleAuditAdapter();
		MantenimientoTablaParametrosV1UseCase useCase = new MantenimientoTablaParametrosV1UseCase(
				new InMemoryParametroRepositoryAdapter(), new UseCaseGuards(p, l), audit);

		var result = useCase.crear(new CrearParametroCommand("COMP01", "P1", "Parametro 1"),
				new FunctionalContext("default", "demo-client", "admin", "HR", "Maestros", "Registrar",
						"Mantenimiento de Parametro", null, null, null, Instant.now()));

		assertEquals("CREADO", result.estado());
		assertEquals("COMP01", result.compania());
	}

	@Test
	void crearParametroVersionadoOk() {
		InMemoryPermissionChecker p = new InMemoryPermissionChecker();
		p.grant("admin", "HR_MANTENIMIENTO_DE_PARAMETRO");
		InMemoryLicenseChecker l = new InMemoryLicenseChecker();
		l.enable("demo-client", "HR");
		UseCaseGuards guards = new UseCaseGuards(p, l);
		AuditPort audit = new ConsoleAuditAdapter();
		InMemoryParametroRepositoryAdapter repository = new InMemoryParametroRepositoryAdapter();
		InMemoryFunctionalVersionResolver resolver = new InMemoryFunctionalVersionResolver(FunctionalVersion.V1);
		resolver.register("demo-client", "HR", "Mantenimiento de Parametro", FunctionalVersion.V2);
		MantenimientoTablaParametrosV1UseCase useCase = RoyalFunctionalVersionProxyFactory
				.builder(MantenimientoTablaParametrosV1UseCase.class, resolver)
				.register(FunctionalVersion.V1, new MantenimientoTablaParametrosV1UseCase(repository, guards, audit))
				.register(FunctionalVersion.V2, new MantenimientoTablaParametrosV2UseCase(repository, guards, audit))
				.build();

		var result = useCase.crear(new CrearParametroCommand("COMP01", "P2", "Parametro 2"),
				new FunctionalContext("default", "demo-client", "admin", "HR", "Maestros", "Registrar",
						"Mantenimiento de Parametro", null, null, null, Instant.now()));

		assertEquals("CREADO", result.estado());
		assertEquals("COMP01", result.compania());
	}

	@Test
	void listarParametrosConFiltrosOk() {
		InMemoryPermissionChecker p = new InMemoryPermissionChecker();
		p.grant("admin", "HR_MANTENIMIENTO_DE_PARAMETRO");
		InMemoryLicenseChecker l = new InMemoryLicenseChecker();
		l.enable("demo-client", "HR");
		UseCaseGuards guards = new UseCaseGuards(p, l);
		AuditPort audit = new ConsoleAuditAdapter();
		InMemoryParametroRepositoryAdapter repository = new InMemoryParametroRepositoryAdapter();
		MantenimientoTablaParametrosV1UseCase useCase = new MantenimientoTablaParametrosV1UseCase(repository, guards,
				audit);
		FunctionalContext context = new FunctionalContext("default", "demo-client", "admin", "HR", "Maestros", "Listar",
				"Mantenimiento de Parametro", null, null, null, Instant.now());

		useCase.crear(new CrearParametroCommand("COMP01", "P4", "Parametro 4"), context);
		useCase.crear(new CrearParametroCommand("COMP02", "P5", "Parametro 5"), context);

		var result = useCase.listar(new ListarParametrosQuery("COMP01", null, "ACTIVO"), context);

		assertEquals(1, result.parametros().size());
		assertEquals("COMP01", result.parametros().get(0).compania());
		assertEquals("P4", result.parametros().get(0).codigo());
	}

	@Test
	void eliminarParametroConsultaPermisoAntesDeEliminar() {
		InMemoryPermissionChecker p = new InMemoryPermissionChecker();
		p.grant("admin", "HR_MANTENIMIENTO_DE_PARAMETRO");
		InMemoryLicenseChecker l = new InMemoryLicenseChecker();
		l.enable("demo-client", "HR");
		UseCaseGuards guards = new UseCaseGuards(p, l);
		AuditPort audit = new ConsoleAuditAdapter();
		InMemoryParametroRepositoryAdapter repository = new InMemoryParametroRepositoryAdapter();
		repository.save(Parametro.cargar("COMP01", "P9", "Parametro protegido", "ACTIVO", "admin", Instant.now()));
		MantenimientoTablaParametrosV1UseCase useCase = new MantenimientoTablaParametrosV1UseCase(repository,
				(query, context) -> false, guards, audit);
		FunctionalContext context = new FunctionalContext("default", "demo-client", "admin", "HR", "Maestros",
				"Eliminar", "Mantenimiento de Parametro", null, null, null, Instant.now());

		assertThrows(net.royal.erp.framework.kernel.BusinessException.class,
				() -> useCase.eliminar(new EliminarParametroCommand("COMP01", "P9"), context));
		assertTrue(repository.existsById(new ParametroId("COMP01", "P9")));
	}

	@Test
	void reporteParametrosPdfOk() {
		InMemoryPermissionChecker p = new InMemoryPermissionChecker();
		p.grant("admin", "HR_MANTENIMIENTO_DE_PARAMETRO");
		p.grant("admin", "HR_REPORTE_DE_PARAMETRO");
		InMemoryLicenseChecker l = new InMemoryLicenseChecker();
		l.enable("demo-client", "HR");
		UseCaseGuards guards = new UseCaseGuards(p, l);
		AuditPort audit = new ConsoleAuditAdapter();
		InMemoryParametroRepositoryAdapter repository = new InMemoryParametroRepositoryAdapter();
		MantenimientoTablaParametrosV1UseCase mantenimiento = new MantenimientoTablaParametrosV1UseCase(repository,
				guards, audit);
		ReporteParametrosV1UseCase reporte = new ReporteParametrosV1UseCase(repository,
				data -> "%PDF-test".getBytes(java.nio.charset.StandardCharsets.US_ASCII), guards, audit);
		FunctionalContext maintenanceContext = new FunctionalContext("default", "demo-client", "admin", "HR",
				"Maestros", "Registrar", "Mantenimiento de Parametro", null, null, null, Instant.now());
		FunctionalContext reportContext = new FunctionalContext("default", "demo-client", "admin", "HR", "Maestros",
				"Reporte", "Reporte de Parametro", null, null, null, Instant.now());

		mantenimiento.crear(new CrearParametroCommand("COMP01", "P3", "Parametro reporte"), maintenanceContext);
		var result = reporte.ejecutar(new ReporteParametrosQuery("COMP01", null, "ACTIVO"), reportContext);

		assertEquals("reporte-parametros.pdf", result.fileName());
		assertTrue(result.pdf().length > 4);
		assertEquals("%PDF", new String(result.pdf(), 0, 4, java.nio.charset.StandardCharsets.US_ASCII));
	}

	@Test
	void aprobacionMasivaSoloCambiaDeAaAp() {
		InMemoryPermissionChecker p = new InMemoryPermissionChecker();
		p.grant("admin", "HR_APROBACION_MASIVA_DE_PARAMETROS");
		InMemoryLicenseChecker l = new InMemoryLicenseChecker();
		l.enable("demo-client", "HR");
		UseCaseGuards guards = new UseCaseGuards(p, l);
		AuditPort audit = new ConsoleAuditAdapter();
		InMemoryParametroRepositoryAdapter repository = new InMemoryParametroRepositoryAdapter();
		repository.save(Parametro.cargar("COMP01", "P6", "Parametro aprobable", "A", "admin", Instant.now()));
		repository.save(Parametro.cargar("COMP01", "P7", "Parametro omitido", "ACTIVO", "admin", Instant.now()));
		AprobacionMasivaParametrosV1UseCase useCase = new AprobacionMasivaParametrosV1UseCase(repository, guards,
				audit);
		FunctionalContext context = new FunctionalContext("default", "demo-client", "admin", "HR", "Maestros",
				"Aprobar", "Aprobacion masiva de Parametros", null, null, null, Instant.now());

		var result = useCase
				.aprobar(
						new AprobarMasivamenteParametrosCommand(List.of(new AprobarParametroItem("COMP01", "P6"),
								new AprobarParametroItem("COMP01", "P7"), new AprobarParametroItem("COMP01", "PX"))),
						context);

		assertEquals(3, result.solicitados());
		assertEquals(1, result.aprobados());
		assertEquals(2, result.omitidos());
		assertEquals("AP", repository.findById(new ParametroId("COMP01", "P6")).orElseThrow().estado().name());
		assertEquals("ACTIVO", repository.findById(new ParametroId("COMP01", "P7")).orElseThrow().estado().name());
		assertEquals("APROBADO", result.parametros().get(0).resultado());
		assertEquals("OMITIDO", result.parametros().get(1).resultado());
		assertEquals("NO_ENCONTRADO", result.parametros().get(2).resultado());
	}
}
