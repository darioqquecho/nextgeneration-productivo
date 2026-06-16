package net.royal.erp.modules.rrhh.bootstrap;

import net.royal.erp.framework.audit.AuditPort;
import net.royal.erp.framework.database.*;
import net.royal.erp.framework.licensing.*;
import net.royal.erp.framework.security.*;
import net.royal.erp.framework.versioning.*;
import net.royal.erp.modules.rrhh.api.FunctionalContextFactory;
import net.royal.erp.modules.rrhh.application.capacitacion.*;
import net.royal.erp.modules.rrhh.application.common.UseCaseGuards;
import net.royal.erp.modules.rrhh.application.parametro.usecase.*;
import net.royal.erp.modules.rrhh.application.requerimiento.*;
import net.royal.erp.modules.rrhh.domain.capacitacion.CapacitacionRepository;
import net.royal.erp.modules.rrhh.domain.parametro.ParametroRepository;
import net.royal.erp.modules.rrhh.infrastructure.aprobaciones.InMemoryAprobacionesAdapter;
import net.royal.erp.modules.rrhh.infrastructure.audit.ConsoleAuditAdapter;
import net.royal.erp.modules.rrhh.infrastructure.capacitacion.InMemoryCapacitacionRepositoryAdapter;
import net.royal.erp.modules.rrhh.infrastructure.parametro.InMemoryParametroRepositoryAdapter;
import net.royal.erp.modules.rrhh.infrastructure.parametro.SqlServerParametroRepositoryAdapter;
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

	@Bean
	ParametroRepository parametroRepository(PersistenceAdapterType adapterType, ObjectProvider<JdbcTemplate> jdbc) {
		return switch (adapterType) {
		case IN_MEMORY -> new InMemoryParametroRepositoryAdapter();
		case SQL_SERVER -> new SqlServerParametroRepositoryAdapter(requiredJdbcTemplate(jdbc, adapterType));
		default -> throw new IllegalStateException("ParametroRepository no implementado para adapter " + adapterType);
		};
	}

	private JdbcTemplate requiredJdbcTemplate(ObjectProvider<JdbcTemplate> jdbc, PersistenceAdapterType adapterType) {
		JdbcTemplate template = jdbc.getIfAvailable();
		if (template == null) {
			throw new IllegalStateException("JdbcTemplate requerido para adapter " + adapterType);
		}
		return template;
	}

	@Bean
	CapacitacionRepository capacitacionRepository(PersistenceAdapterType adapterType) {
		return switch (adapterType) {
		case IN_MEMORY -> new InMemoryCapacitacionRepositoryAdapter();
		default -> throw new IllegalStateException("CapacitacionRepository no implementado para adapter " + adapterType);
		};
	}

	@Bean
	AuditPort auditPort() {
		return new ConsoleAuditAdapter();
	}

	@Bean
	FunctionalContextFactory functionalContextFactory() {
		return new FunctionalContextFactory();
	}

	@Bean
	PermissionChecker permissionChecker() {
		InMemoryPermissionChecker c = new InMemoryPermissionChecker();
		for (String p : new String[] { "RRHH_PARAMETRO_CREAR", "RRHH_PARAMETRO_ACTUALIZAR", "RRHH_PARAMETRO_CONSULTAR",
				"RRHH_PARAMETRO_CAMBIARESTADO", "RRHH_PARAMETRO_ELIMINAR", "RRHH_CAPACITACION_REGISTRAR",
				"RRHH_REQUERIMIENTO_APROBAR" }) {
			c.grant("admin", p);
		}
		return c;
	}

	@Bean
	LicenseChecker licenseChecker() {
		InMemoryLicenseChecker c = new InMemoryLicenseChecker();
		c.enable("demo-client", "RRHH");
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
	CrearParametroUseCase crearParametroUseCase(FunctionalVersionResolver v, ParametroRepository r, UseCaseGuards g,
			AuditPort a) {
		CrearParametroUseCase v1 = new CrearParametroV1UseCase(r, g, a);
		CrearParametroUseCase v2 = new CrearParametroV2UseCase(r, g, a);
		return new CrearParametroVersionedUseCase(v, v1, v2);
	}

	@Bean
	ActualizarParametroUseCase actualizarParametroUseCase(FunctionalVersionResolver v, ParametroRepository r,
			UseCaseGuards g, AuditPort a) {
		ActualizarParametroUseCase v1 = new ActualizarParametroV1UseCase(r, g, a);
		ActualizarParametroUseCase v2 = new ActualizarParametroV2UseCase(r, g, a);
		return new ActualizarParametroVersionedUseCase(v, v1, v2);
	}

	@Bean
	ObtenerParametroUseCase obtenerParametroUseCase(FunctionalVersionResolver v, ParametroRepository r,
			UseCaseGuards g) {
		ObtenerParametroUseCase v1 = new ObtenerParametroV1UseCase(r, g);
		ObtenerParametroUseCase v2 = new ObtenerParametroV2UseCase(r, g);
		return new ObtenerParametroVersionedUseCase(v, v1, v2);
	}

	@Bean
	ListarParametrosUseCase listarParametrosUseCase(FunctionalVersionResolver v, ParametroRepository r,
			UseCaseGuards g) {
		ListarParametrosUseCase v1 = new ListarParametrosV1UseCase(r, g);
		ListarParametrosUseCase v2 = new ListarParametrosV2UseCase(r, g);
		return new ListarParametrosVersionedUseCase(v, v1, v2);
	}

	@Bean
	CambiarEstadoParametroUseCase cambiarEstadoParametroUseCase(FunctionalVersionResolver v, ParametroRepository r,
			UseCaseGuards g, AuditPort a) {
		CambiarEstadoParametroUseCase v1 = new CambiarEstadoParametroV1UseCase(r, g, a);
		CambiarEstadoParametroUseCase v2 = new CambiarEstadoParametroV2UseCase(r, g, a);
		return new CambiarEstadoParametroVersionedUseCase(v, v1, v2);
	}

	@Bean
	EliminarParametroUseCase eliminarParametroUseCase(FunctionalVersionResolver v, ParametroRepository r,
			UseCaseGuards g, AuditPort a) {
		EliminarParametroUseCase v1 = new EliminarParametroV1UseCase(r, g, a);
		EliminarParametroUseCase v2 = new EliminarParametroV2UseCase(r, g, a);
		return new EliminarParametroVersionedUseCase(v, v1, v2);
	}

	@Bean
	RegistrarCapacitacionUseCase registrarCapacitacionUseCase(FunctionalVersionResolver v, CapacitacionRepository r,
			UseCaseGuards g, AuditPort a) {
		RegistrarCapacitacionV1UseCase v1 = new RegistrarCapacitacionV1UseCase(r, g, a);
		RegistrarCapacitacionV2UseCase v2 = new RegistrarCapacitacionV2UseCase(v1);
		return new RegistrarCapacitacionVersionedUseCase(v, v1, v2);
	}

	@Bean
	AprobacionesPort aprobacionesPort() {
		return new InMemoryAprobacionesAdapter();
	}

	@Bean
	AprobarRequerimientoPersonalUseCase aprobarRequerimientoPersonalUseCase(FunctionalVersionResolver v,
			UseCaseGuards g, AuditPort a, AprobacionesPort p) {
		AprobarRequerimientoPersonalV1UseCase v1 = new AprobarRequerimientoPersonalV1UseCase(g, a);
		AprobarRequerimientoPersonalV2UseCase v2 = new AprobarRequerimientoPersonalV2UseCase(v1, p);
		return new AprobarRequerimientoPersonalVersionedUseCase(v, v1, v2);
	}
}
