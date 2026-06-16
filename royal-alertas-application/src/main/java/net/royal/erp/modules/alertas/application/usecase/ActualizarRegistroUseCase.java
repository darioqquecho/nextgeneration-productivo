package net.royal.erp.modules.alertas.application.usecase;

import net.royal.erp.framework.audit.AuditPort;
import net.royal.erp.framework.audit.FunctionalAuditRecord;
import net.royal.erp.framework.kernel.BusinessException;
import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.kernel.UseCase;
import net.royal.erp.modules.alertas.application.common.AlertasUseCaseGuards;
import net.royal.erp.modules.alertas.application.dto.*;
import net.royal.erp.modules.alertas.domain.model.AlertasRecord;
import net.royal.erp.modules.alertas.domain.repository.AlertasRecordRepository;

/**
 * Implementa: - MOD-012 Migración funcional de royal-alertas-api. - ARCH-013
 * CQRS. - ARCH-015 Auditoría. - ARCH-025 Licenciamiento.
 *
 * Corrige / Evoluciona: - ASIS-016 Reglas de negocio concentradas en Services.
 *
 * Propósito: Caso de uso migrado para operación Actualizar Registro.
 */
public class ActualizarRegistroUseCase implements UseCase<AlertasCrudCommand, AlertasOperationResult> {
	private final AlertasRecordRepository repository;
	private final AlertasUseCaseGuards guards;
	private final AuditPort auditPort;

	public ActualizarRegistroUseCase(AlertasRecordRepository repository, AlertasUseCaseGuards guards,
			AuditPort auditPort) {
		this.repository = repository;
		this.guards = guards;
		this.auditPort = auditPort;
	}

	/**
	 * Ejecuta operación Actualizar Registro en SQL Server mediante puerto de
	 * dominio.
	 *
	 * Implementa: - ARCH-018 Reglas de Dependencia. - MOD-012 Migración funcional.
	 */
	@Override
	public AlertasOperationResult execute(AlertasCrudCommand command, FunctionalContext context) {
		guards.check(context, "ALERTAS_ACTUALIZAR");
		var record = repository.update(new AlertasRecord(command.entityName(), command.values()));
		var data = record.values();
		auditPort.register(new FunctionalAuditRecord("ALERTAS", "Migración Legacy", "Actualizar Registro",
				"ActualizarRegistroUseCase", "V1", context.userId(), "OK", command.entityName(), String.valueOf(data),
				context.traceId(), context.requestId(), context.executedAt()));
		return new AlertasOperationResult(command.entityName(), "update", data, context.traceId());
	}
}
