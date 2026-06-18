package net.royal.erp.modules.alertas.application.usecase;

import net.royal.erp.framework.audit.AuditPort;
import net.royal.erp.framework.audit.FunctionalAuditRecord;
import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.kernel.UseCase;
import net.royal.erp.modules.alertas.application.common.AlertasUseCaseGuards;
import net.royal.erp.modules.alertas.application.dto.NamedQueryCommand;
import java.util.Map;

/**
 * Implementa: - ARCH-008 Ejecución batch/scheduler. - ASIS-017 Procesos Batch y
 * Scheduler.
 *
 * Propósito: Caso de uso productivo inicial para ejecutar ciclo de Alertas sin
 * que el scheduler contenga reglas de negocio.
 */
public class ProcesarCicloAlertasUseCase implements UseCase<NamedQueryCommand, String> {
	private final EjecutarNamedQueryUseCase namedQueryUseCase;
	private final AlertasUseCaseGuards guards;
	private final AuditPort auditPort;

	public ProcesarCicloAlertasUseCase(EjecutarNamedQueryUseCase namedQueryUseCase, AlertasUseCaseGuards guards,
			AuditPort auditPort) {
		this.namedQueryUseCase = namedQueryUseCase;
		this.guards = guards;
		this.auditPort = auditPort;
	}

	/**
	 * Ejecuta una operación de ciclo de alertas.
	 *
	 * Implementa: - ARCH-008 Scheduler.
	 */
	@Override
	public String execute(NamedQueryCommand command, FunctionalContext context) {
		guards.check(context, "ALERTAS_SCHEDULER_EJECUTAR");
		if (command != null && command.queryName() != null && !command.queryName().isBlank()) {
			namedQueryUseCase.execute(command, context);
		}
		auditPort.register(new FunctionalAuditRecord("ALERTAS", "Scheduler", "ProcesarCicloAlertasUseCase",
				"Procesar Ciclo Alertas", "V1", context.userId(), "OK", "Scheduler",
				String.valueOf(command == null ? "default" : command.queryName()), context.traceId(),
				context.requestId(), context.executedAt()));
		return "OK";
	}
}
