package net.royal.erp.modules.rrhh.bootstrap;

import net.royal.erp.framework.audit.*;
import net.royal.erp.framework.database.*;
import net.royal.erp.framework.licensing.*;
import net.royal.erp.framework.observability.ObservabilityPort;
import net.royal.erp.framework.security.*;
import net.royal.erp.framework.versioning.*;
import net.royal.erp.modules.rrhh.application.capacitacion.*;
import net.royal.erp.modules.rrhh.application.parametro.port.*;
import net.royal.erp.modules.rrhh.application.parametro.usecase.*;
import net.royal.erp.modules.rrhh.application.requerimiento.*;
import net.royal.erp.modules.rrhh.domain.capacitacion.CapacitacionRepository;
import net.royal.erp.modules.rrhh.infrastructure.aprobaciones.InMemoryAprobacionesAdapter;
import net.royal.erp.modules.rrhh.infrastructure.capacitacion.InMemoryCapacitacionRepositoryAdapter;
import net.royal.erp.modules.rrhh.infrastructure.parametro.*;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Implementa: - ARCH-008 Bootstrap. - ARCH-009 Bases de Datos. - MOD-013
 * Adapter configurable.
 */
@Configuration
public class RrhhConfiguration {
	@Value("${royal.persistence.adapter:IN_MEMORY}")
	private String persistenceAdapter;

	@Value("${royal.functional-version.default:V1}")
	private String defaultFunctionalVersion;

	@Value("${royal.functional-version.catalog:classpath:functional-version-catalog.xml}")
	private String functionalVersionCatalog;

	@Bean
	PersistenceAdapterType persistenceAdapterType() {
		return new PersistenceAdapterResolver().resolve(persistenceAdapter);
	}

	private JdbcTemplate requiredJdbcTemplate(ObjectProvider<JdbcTemplate> jdbc, PersistenceAdapterType adapterType) {
		JdbcTemplate template = jdbc.getIfAvailable();
		if (template == null) {
			throw new IllegalStateException("JdbcTemplate requerido para adapter " + adapterType);
		}
		return template;
	}

	@Bean
	InMemoryParametroRepositoryAdapter inMemoryParametroRepositoryAdapter() {
		return new InMemoryParametroRepositoryAdapter();
	}

	@Bean
	CapacitacionRepository capacitacionRepository(PersistenceAdapterType adapterType) {
		return switch (adapterType) {
		case IN_MEMORY -> new InMemoryCapacitacionRepositoryAdapter();
		default -> throw new IllegalStateException("CapacitacionRepository no implementado para adapter " + adapterType);
		};
	}

	@Bean
	AuditPort auditPort(PersistenceAdapterType adapterType, ObjectProvider<JdbcTemplate> jdbc) {
		AuditPort console = new ConsoleAuditAdapter();
		return switch (adapterType) {
		case SQL_SERVER -> new CompositeAuditAdapter(console,
				new JdbcFunctionalAuditAdapter(requiredJdbcTemplate(jdbc, adapterType)));
		default -> console;
		};
	}

	@Bean
	PermissionChecker permissionChecker() {
		InMemoryPermissionChecker c = new InMemoryPermissionChecker();
		for (String p : new String[] { "HR_MANTENIMIENTO_DE_PARAMETRO", "HR_REPORTE_DE_PARAMETRO",
				"HR_APROBACION_MASIVA_DE_PARAMETROS", "HR_REGISTRAR_CAPACITACION",
				"HR_APROBAR_REQUERIMIENTO_PERSONAL" }) {
			c.grant("admin", p);
		}
		return c;
	}

	@Bean
	LicenseChecker licenseChecker() {
		InMemoryLicenseChecker c = new InMemoryLicenseChecker();
		c.enable("demo-client", "HR");
		return c;
	}

	@Bean
	UseCaseGuards guards(PermissionChecker p, LicenseChecker l) {
		return new UseCaseGuards(p, l);
	}

	@Bean
	FunctionalVersionResolver functionalVersionResolver(ResourceLoader resourceLoader) {
		FunctionalVersion defaultVersion = FunctionalVersion.valueOf(defaultFunctionalVersion.trim().toUpperCase());
		return new FunctionalVersionXmlCatalogLoader(resourceLoader).load(functionalVersionCatalog, defaultVersion);
	}

	@Bean
	MantenimientoTablaParametrosUseCase mantenimientoTablaParametrosUseCase(FunctionalVersionResolver v,
			PersistenceAdapterType adapterType, ObjectProvider<JdbcTemplate> jdbc,
			InMemoryParametroRepositoryAdapter inMemory, UseCaseGuards g, AuditPort a, ObservabilityPort o) {
		MantenimientoTablaParametrosUseCase v1 = new MantenimientoTablaParametrosV1UseCase(
				mantenimientoRepository(adapterType, jdbc, inMemory, "v1"), g, a);
		MantenimientoTablaParametrosUseCase v2 = new MantenimientoTablaParametrosV2UseCase(
				mantenimientoRepository(adapterType, jdbc, inMemory, "v2"), g, a);
		return new ObservedMantenimientoTablaParametrosUseCase(new MantenimientoTablaParametrosVersionedUseCase(v, v1, v2),
				o);
	}

	@Bean
	ReporteParametrosUseCase reporteParametrosUseCase(PersistenceAdapterType adapterType,
			ObjectProvider<JdbcTemplate> jdbc, InMemoryParametroRepositoryAdapter inMemory, UseCaseGuards g,
			AuditPort a, ObservabilityPort o) {
		return new ObservedReporteParametrosUseCase(
				new ReporteParametrosJasperUseCase(reporteRepository(adapterType, jdbc, inMemory), g, a), o);
	}

	@Bean
	AprobacionMasivaParametrosUseCase aprobacionMasivaParametrosUseCase(PersistenceAdapterType adapterType,
			ObjectProvider<JdbcTemplate> jdbc, InMemoryParametroRepositoryAdapter inMemory, UseCaseGuards g,
			AuditPort a, ObservabilityPort o) {
		return new ObservedAprobacionMasivaParametrosUseCase(
				new AprobacionMasivaParametrosV1UseCase(aprobacionMasivaRepository(adapterType, jdbc, inMemory), g, a), o);
	}

	private MantenimientoTablaParametrosRepository mantenimientoRepository(PersistenceAdapterType adapterType,
			ObjectProvider<JdbcTemplate> jdbc, InMemoryParametroRepositoryAdapter inMemory, String version) {
		return switch (adapterType) {
		case IN_MEMORY -> inMemory;
		case SQL_SERVER -> "v2".equalsIgnoreCase(version)
				? new SqlServerMantenimientoTablaParametrosV2Adapter(requiredJdbcTemplate(jdbc, adapterType))
				: new SqlServerMantenimientoTablaParametrosV1Adapter(requiredJdbcTemplate(jdbc, adapterType));
		default -> throw new IllegalStateException("Mantenimiento de Parametro no implementado para " + adapterType);
		};
	}

	private ReporteParametrosRepository reporteRepository(PersistenceAdapterType adapterType,
			ObjectProvider<JdbcTemplate> jdbc, InMemoryParametroRepositoryAdapter inMemory) {
		return switch (adapterType) {
		case IN_MEMORY -> inMemory;
		case SQL_SERVER -> new SqlServerReporteParametrosV1Adapter(requiredJdbcTemplate(jdbc, adapterType));
		default -> throw new IllegalStateException("Reporte de Parametro no implementado para " + adapterType);
		};
	}

	private AprobacionMasivaParametrosRepository aprobacionMasivaRepository(PersistenceAdapterType adapterType,
			ObjectProvider<JdbcTemplate> jdbc, InMemoryParametroRepositoryAdapter inMemory) {
		return switch (adapterType) {
		case IN_MEMORY -> inMemory;
		case SQL_SERVER -> new SqlServerAprobacionMasivaParametrosV1Adapter(requiredJdbcTemplate(jdbc, adapterType));
		default -> throw new IllegalStateException("Aprobacion masiva de Parametros no implementado para " + adapterType);
		};
	}

	@Bean
	RegistrarCapacitacionUseCase registrarCapacitacionUseCase(FunctionalVersionResolver v, CapacitacionRepository r,
			UseCaseGuards g, AuditPort a, ObservabilityPort o) {
		RegistrarCapacitacionV1UseCase v1 = new RegistrarCapacitacionV1UseCase(r, g, a);
		RegistrarCapacitacionV2UseCase v2 = new RegistrarCapacitacionV2UseCase(v1);
		return new ObservedRegistrarCapacitacionUseCase(new RegistrarCapacitacionVersionedUseCase(v, v1, v2), o);
	}

	@Bean
	AprobacionesPort aprobacionesPort() {
		return new InMemoryAprobacionesAdapter();
	}

	@Bean
	AprobarRequerimientoPersonalUseCase aprobarRequerimientoPersonalUseCase(FunctionalVersionResolver v,
			UseCaseGuards g, AuditPort a, AprobacionesPort p, ObservabilityPort o) {
		AprobarRequerimientoPersonalV1UseCase v1 = new AprobarRequerimientoPersonalV1UseCase(g, a);
		AprobarRequerimientoPersonalV2UseCase v2 = new AprobarRequerimientoPersonalV2UseCase(v1, p);
		return new ObservedAprobarRequerimientoPersonalUseCase(
				new AprobarRequerimientoPersonalVersionedUseCase(v, v1, v2), o);
	}
}
