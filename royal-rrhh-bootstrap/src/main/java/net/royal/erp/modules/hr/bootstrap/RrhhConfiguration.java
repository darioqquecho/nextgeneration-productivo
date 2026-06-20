package net.royal.erp.modules.hr.bootstrap;

import net.royal.erp.framework.audit.*;
import net.royal.erp.framework.database.*;
import net.royal.erp.framework.kernel.client.ClientCatalog;
import net.royal.erp.framework.licensing.*;
import net.royal.erp.framework.observability.ObservabilityPort;
import net.royal.erp.framework.security.*;
import net.royal.erp.framework.versioning.*;
import net.royal.erp.framework.web.client.RestApiClient;
import net.royal.erp.modules.hr.application.capacitacion.registrar.*;
import net.royal.erp.modules.hr.application.capacitacion.registrar.port.CapacitacionRepository;
import net.royal.erp.modules.hr.application.maestros.parametro.port.*;
import net.royal.erp.modules.hr.application.maestros.parametro.usecase.*;
import net.royal.erp.modules.hr.application.requerimiento.aprobar.*;
import net.royal.erp.modules.hr.application.maestros.tiposeguro.port.MantenimientoTipoSeguroRepository;
import net.royal.erp.modules.hr.application.maestros.tiposeguro.usecase.*;
import net.royal.erp.modules.hr.infrastructure.requerimiento.aprobar.InMemoryAprobacionesAdapter;
import net.royal.erp.modules.hr.infrastructure.capacitacion.registrar.InMemoryCapacitacionRepositoryAdapter;
import net.royal.erp.modules.hr.infrastructure.maestros.parametro.inmemory.InMemoryParametroRepositoryAdapter;
import net.royal.erp.modules.hr.infrastructure.maestros.parametro.jasper.JasperReporteParametrosDocumentGenerator;
import net.royal.erp.modules.hr.infrastructure.maestros.parametro.oracle.OracleAprobacionMasivaParametrosV1Adapter;
import net.royal.erp.modules.hr.infrastructure.maestros.parametro.oracle.OracleMantenimientoTablaParametrosV1Adapter;
import net.royal.erp.modules.hr.infrastructure.maestros.parametro.oracle.OracleMantenimientoTablaParametrosV2Adapter;
import net.royal.erp.modules.hr.infrastructure.maestros.parametro.oracle.OracleReporteParametrosV1Adapter;
import net.royal.erp.modules.hr.infrastructure.maestros.parametro.sqlserver.SqlServerAprobacionMasivaParametrosV1Adapter;
import net.royal.erp.modules.hr.infrastructure.maestros.parametro.sqlserver.SqlServerMantenimientoTablaParametrosV1Adapter;
import net.royal.erp.modules.hr.infrastructure.maestros.parametro.sqlserver.SqlServerMantenimientoTablaParametrosV2Adapter;
import net.royal.erp.modules.hr.infrastructure.maestros.parametro.sqlserver.SqlServerReporteParametrosV1Adapter;
import net.royal.erp.modules.hr.infrastructure.seguridad.RestConsultaPermisoAdapter;
import net.royal.erp.modules.hr.infrastructure.maestros.tiposeguro.inmemory.InMemoryTipoSeguroRepositoryAdapter;
import net.royal.erp.modules.hr.infrastructure.maestros.tiposeguro.oracle.OracleMantenimientoTipoSeguroV1Adapter;
import net.royal.erp.modules.hr.infrastructure.maestros.tiposeguro.sqlserver.SqlServerMantenimientoTipoSeguroV1Adapter;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

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

	@Value("${royal.client.catalog:classpath:client-catalog.xml}")
	private String clientCatalogLocation;

	@Value("${spring.datasource.url:}")
	private String datasourceUrl;

	@Value("${spring.datasource.username:}")
	private String datasourceUsername;

	@Value("${spring.datasource.password:}")
	private String datasourcePassword;

	@Value("${spring.datasource.driver-class-name:}")
	private String datasourceDriverClassName;

	@Value("${royal.autenticacion.base-url:http://localhost:8080}")
	private String autenticacionBaseUrl;

	@Value("${royal.security.api-key.value:}")
	private String apiKey;

	@Bean
	PersistenceAdapterType persistenceAdapterType() {
		return new PersistenceAdapterResolver().resolve(persistenceAdapter);
	}

	@Bean
	@ConditionalOnProperty(name = "royal.persistence.adapter", havingValue = "SQL_SERVER")
	JdbcTemplate sqlServerJdbcTemplate() {
		return jdbcTemplate("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	}

	@Bean
	@ConditionalOnProperty(name = "royal.persistence.adapter", havingValue = "ORACLE")
	JdbcTemplate oracleJdbcTemplate() {
		return jdbcTemplate("oracle.jdbc.OracleDriver");
	}

	private JdbcTemplate jdbcTemplate(String defaultDriverClassName) {
		if (datasourceUrl == null || datasourceUrl.isBlank()) {
			throw new IllegalStateException("spring.datasource.url requerido para adapter " + persistenceAdapter);
		}
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(datasourceDriverClassName == null || datasourceDriverClassName.isBlank()
				? defaultDriverClassName
				: datasourceDriverClassName);
		dataSource.setUrl(datasourceUrl);
		dataSource.setUsername(datasourceUsername);
		dataSource.setPassword(datasourcePassword);
		return new JdbcTemplate(dataSource);
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
	InMemoryTipoSeguroRepositoryAdapter inMemoryTipoSeguroRepositoryAdapter() {
		return new InMemoryTipoSeguroRepositoryAdapter();
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
				"HR_APROBACION_MASIVA_DE_PARAMETROS", "HR_MANTENIMIENTO_DE_TIPO_SEGURO", "HR_REGISTRAR_CAPACITACION",
				"HR_APROBAR_REQUERIMIENTO_PERSONAL" }) {
			c.grant("demo-client", "admin", p);
			c.grant("acme", "admin", p);
			c.grant("contoso", "admin", p);
		}
		return c;
	}

	@Bean
	ClientCatalog clientCatalog(ResourceLoader resourceLoader) {
		return new ClientCatalogXmlLoader(resourceLoader).load(clientCatalogLocation);
	}

	@Bean
	LicenseChecker licenseChecker(ClientCatalog clientCatalog) {
		return new ClientCatalogLicenseChecker(clientCatalog);
	}

	@Bean
	UseCaseGuards guards(PermissionChecker p, LicenseChecker l) {
		return new UseCaseGuards(p, l);
	}

	@Bean
	ConsultaPermisoPort consultaPermisoPort() {
		return new RestConsultaPermisoAdapter(autenticacionRestApiClient());
	}

	@Bean
	RestApiClient autenticacionRestApiClient() {
		return new RestApiClient(autenticacionBaseUrl, "SECURITY-PERMISSION-SERVICE", apiKey);
	}

	@Bean
	FunctionalVersionResolver functionalVersionResolver(ResourceLoader resourceLoader) {
		FunctionalVersion defaultVersion = FunctionalVersion.valueOf(defaultFunctionalVersion.trim().toUpperCase());
		return new FunctionalVersionXmlCatalogLoader(resourceLoader).load(functionalVersionCatalog, defaultVersion);
	}

	@Bean
	MantenimientoTablaParametrosUseCase mantenimientoTablaParametrosUseCase(FunctionalVersionResolver v,
			PersistenceAdapterType adapterType, ObjectProvider<JdbcTemplate> jdbc,
			InMemoryParametroRepositoryAdapter inMemory, ConsultaPermisoPort consultaPermiso, UseCaseGuards g,
			AuditPort a, ObservabilityPort o) {
		MantenimientoTablaParametrosUseCase v1 = new MantenimientoTablaParametrosV1UseCase(
				mantenimientoRepository(adapterType, jdbc, inMemory, "v1"), consultaPermiso, g, a);
		MantenimientoTablaParametrosUseCase v2 = new MantenimientoTablaParametrosV2UseCase(
				mantenimientoRepository(adapterType, jdbc, inMemory, "v2"), consultaPermiso, g, a);
		return new ObservedMantenimientoTablaParametrosUseCase(new MantenimientoTablaParametrosVersionedUseCase(v, v1, v2),
				o);
	}

	@Bean
	ReporteParametrosUseCase reporteParametrosUseCase(PersistenceAdapterType adapterType,
			ObjectProvider<JdbcTemplate> jdbc, InMemoryParametroRepositoryAdapter inMemory, UseCaseGuards g,
			AuditPort a, ObservabilityPort o) {
		return new ObservedReporteParametrosUseCase(
				new ReporteParametrosV1UseCase(reporteRepository(adapterType, jdbc, inMemory),
						new JasperReporteParametrosDocumentGenerator(), g, a),
				o);
	}

	@Bean
	AprobacionMasivaParametrosUseCase aprobacionMasivaParametrosUseCase(PersistenceAdapterType adapterType,
			ObjectProvider<JdbcTemplate> jdbc, InMemoryParametroRepositoryAdapter inMemory, UseCaseGuards g,
			AuditPort a, ObservabilityPort o) {
		return new ObservedAprobacionMasivaParametrosUseCase(
				new AprobacionMasivaParametrosV1UseCase(aprobacionMasivaRepository(adapterType, jdbc, inMemory), g, a), o);
	}

	@Bean
	MantenimientoTipoSeguroUseCase mantenimientoTipoSeguroUseCase(PersistenceAdapterType adapterType,
			ObjectProvider<JdbcTemplate> jdbc, InMemoryTipoSeguroRepositoryAdapter inMemory, UseCaseGuards g,
			AuditPort a, ObservabilityPort o) {
		return new ObservedMantenimientoTipoSeguroUseCase(
				new MantenimientoTipoSeguroV1UseCase(tipoSeguroRepository(adapterType, jdbc, inMemory), g, a), o);
	}

	private MantenimientoTablaParametrosRepository mantenimientoRepository(PersistenceAdapterType adapterType,
			ObjectProvider<JdbcTemplate> jdbc, InMemoryParametroRepositoryAdapter inMemory, String version) {
		return switch (adapterType) {
		case IN_MEMORY -> inMemory;
		case SQL_SERVER -> "v2".equalsIgnoreCase(version)
				? new SqlServerMantenimientoTablaParametrosV2Adapter(requiredJdbcTemplate(jdbc, adapterType))
				: new SqlServerMantenimientoTablaParametrosV1Adapter(requiredJdbcTemplate(jdbc, adapterType));
		case ORACLE -> "v2".equalsIgnoreCase(version)
				? new OracleMantenimientoTablaParametrosV2Adapter(requiredJdbcTemplate(jdbc, adapterType))
				: new OracleMantenimientoTablaParametrosV1Adapter(requiredJdbcTemplate(jdbc, adapterType));
		default -> throw new IllegalStateException("Mantenimiento de Parametro no implementado para " + adapterType);
		};
	}

	private ReporteParametrosRepository reporteRepository(PersistenceAdapterType adapterType,
			ObjectProvider<JdbcTemplate> jdbc, InMemoryParametroRepositoryAdapter inMemory) {
		return switch (adapterType) {
		case IN_MEMORY -> inMemory;
		case SQL_SERVER -> new SqlServerReporteParametrosV1Adapter(requiredJdbcTemplate(jdbc, adapterType));
		case ORACLE -> new OracleReporteParametrosV1Adapter(requiredJdbcTemplate(jdbc, adapterType));
		default -> throw new IllegalStateException("Reporte de Parametro no implementado para " + adapterType);
		};
	}

	private AprobacionMasivaParametrosRepository aprobacionMasivaRepository(PersistenceAdapterType adapterType,
			ObjectProvider<JdbcTemplate> jdbc, InMemoryParametroRepositoryAdapter inMemory) {
		return switch (adapterType) {
		case IN_MEMORY -> inMemory;
		case SQL_SERVER -> new SqlServerAprobacionMasivaParametrosV1Adapter(requiredJdbcTemplate(jdbc, adapterType));
		case ORACLE -> new OracleAprobacionMasivaParametrosV1Adapter(requiredJdbcTemplate(jdbc, adapterType));
		default -> throw new IllegalStateException("Aprobacion masiva de Parametros no implementado para " + adapterType);
		};
	}

	private MantenimientoTipoSeguroRepository tipoSeguroRepository(PersistenceAdapterType adapterType,
			ObjectProvider<JdbcTemplate> jdbc, InMemoryTipoSeguroRepositoryAdapter inMemory) {
		return switch (adapterType) {
		case IN_MEMORY -> inMemory;
		case SQL_SERVER -> new SqlServerMantenimientoTipoSeguroV1Adapter(requiredJdbcTemplate(jdbc, adapterType));
		case ORACLE -> new OracleMantenimientoTipoSeguroV1Adapter(requiredJdbcTemplate(jdbc, adapterType));
		default -> throw new IllegalStateException("Mantenimiento de Tipo Seguro no implementado para " + adapterType);
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
