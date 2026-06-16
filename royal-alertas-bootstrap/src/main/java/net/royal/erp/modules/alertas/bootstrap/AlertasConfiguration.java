package net.royal.erp.modules.alertas.bootstrap;

import net.royal.erp.framework.audit.AuditPort;
import net.royal.erp.framework.licensing.*;
import net.royal.erp.framework.security.*;
import net.royal.erp.modules.alertas.api.AlertasContextFactory;
import net.royal.erp.modules.alertas.application.common.AlertasUseCaseGuards;
import net.royal.erp.modules.alertas.application.usecase.*;
import net.royal.erp.modules.alertas.domain.repository.*;
import net.royal.erp.modules.alertas.infrastructure.audit.SqlServerAuditAdapter;
import net.royal.erp.modules.alertas.infrastructure.persistence.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * Implementa: - ARCH-008 Bootstrap. - ARCH-009 SQL Server. - ARCH-025
 * Licenciamiento.
 *
 * Propósito: Wiring productivo inicial del módulo Alertas migrado.
 */
@Configuration
public class AlertasConfiguration {
	@Bean
	AlertasContextFactory alertasContextFactory() {
		return new AlertasContextFactory();
	}

	@Bean
	SqlHbmCatalog sqlHbmCatalog() {
		return new SqlHbmCatalog();
	}

	@Bean
	AlertasRecordRepository alertasRecordRepository(NamedParameterJdbcTemplate jdbc) {
		return new SqlServerAlertasRecordRepository(jdbc);
	}

	@Bean
	AlertasNamedQueryRepository alertasNamedQueryRepository(NamedParameterJdbcTemplate jdbc, SqlHbmCatalog catalog) {
		return new SqlServerNamedQueryRepository(jdbc, catalog);
	}

	@Bean
	AuditPort alertasAuditPort() {
		return new SqlServerAuditAdapter();
	}

	/** Implementa ARCH-006. Permisos iniciales para operación productiva. */
	@Bean
	PermissionChecker alertasPermissionChecker() {
		InMemoryPermissionChecker checker = new InMemoryPermissionChecker();
		for (String p : new String[] { "ALERTAS_REGISTRAR", "ALERTAS_ACTUALIZAR", "ALERTAS_CONSULTAR", "ALERTAS_ANULAR",
				"ALERTAS_ELIMINAR", "ALERTAS_SQL_EJECUTAR", "ALERTAS_SCHEDULER_EJECUTAR" })
			checker.grant("admin", p);
		return checker;
	}

	/** Implementa ARCH-025. Licencia inicial del módulo Alertas. */
	@Bean
	LicenseChecker alertasLicenseChecker() {
		InMemoryLicenseChecker checker = new InMemoryLicenseChecker();
		checker.enable("demo-client", "ALERTAS");
		return checker;
	}

	@Bean
	AlertasUseCaseGuards alertasUseCaseGuards(PermissionChecker p, LicenseChecker l) {
		return new AlertasUseCaseGuards(p, l);
	}

	@Bean
	CrearRegistroUseCase crearRegistroUseCase(AlertasRecordRepository r, AlertasUseCaseGuards g, AuditPort a) {
		return new CrearRegistroUseCase(r, g, a);
	}

	@Bean
	ActualizarRegistroUseCase actualizarRegistroUseCase(AlertasRecordRepository r, AlertasUseCaseGuards g,
			AuditPort a) {
		return new ActualizarRegistroUseCase(r, g, a);
	}

	@Bean
	ObtenerRegistroUseCase obtenerRegistroUseCase(AlertasRecordRepository r, AlertasUseCaseGuards g, AuditPort a) {
		return new ObtenerRegistroUseCase(r, g, a);
	}

	@Bean
	ListarRegistrosUseCase listarRegistrosUseCase(AlertasRecordRepository r, AlertasUseCaseGuards g) {
		return new ListarRegistrosUseCase(r, g);
	}

	@Bean
	AnularRegistroUseCase anularRegistroUseCase(AlertasRecordRepository r, AlertasUseCaseGuards g, AuditPort a) {
		return new AnularRegistroUseCase(r, g, a);
	}

	@Bean
	EliminarRegistroUseCase eliminarRegistroUseCase(AlertasRecordRepository r, AlertasUseCaseGuards g, AuditPort a) {
		return new EliminarRegistroUseCase(r, g, a);
	}

	@Bean
	EjecutarNamedQueryUseCase ejecutarNamedQueryUseCase(AlertasNamedQueryRepository r, AlertasUseCaseGuards g) {
		return new EjecutarNamedQueryUseCase(r, g);
	}

	@Bean
	EjecutarLegacyEndpointUseCase ejecutarLegacyEndpointUseCase(CrearRegistroUseCase c, ActualizarRegistroUseCase u,
			ObtenerRegistroUseCase o, ListarRegistrosUseCase l, AnularRegistroUseCase a, EliminarRegistroUseCase e,
			EjecutarNamedQueryUseCase q) {
		return new EjecutarLegacyEndpointUseCase(c, u, o, l, a, e, q);
	}

	@Bean
	ProcesarCicloAlertasUseCase procesarCicloAlertasUseCase(EjecutarNamedQueryUseCase q, AlertasUseCaseGuards g,
			AuditPort a) {
		return new ProcesarCicloAlertasUseCase(q, g, a);
	}
}
