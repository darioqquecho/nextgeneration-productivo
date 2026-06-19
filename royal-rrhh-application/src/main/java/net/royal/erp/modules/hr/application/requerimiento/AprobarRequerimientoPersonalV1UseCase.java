package net.royal.erp.modules.hr.application.requerimiento;

import net.royal.erp.framework.audit.*;
import net.royal.erp.framework.kernel.*;
import net.royal.erp.framework.security.UseCaseGuards;

/**
 * Implementa: - MOD-012 CU-003 AprobarRequerimientoPersonal.
 */
public class AprobarRequerimientoPersonalV1UseCase implements AprobarRequerimientoPersonalUseCase {
	private final UseCaseGuards guards;
	private final AuditPort auditPort;

	public AprobarRequerimientoPersonalV1UseCase(UseCaseGuards guards, AuditPort auditPort) {
		this.guards = guards;
		this.auditPort = auditPort;
	}

	public AprobarRequerimientoPersonalResult execute(AprobarRequerimientoPersonalCommand command,
			FunctionalContext context) {
		guards.check(context);
		auditPort.register(new FunctionalAuditRecord(context.tenantId(), context.clientId(), "HR", context.process(),
				context.useCase(), context.functionality(), "V1", context.userId(), command.accion(), "Requerimiento",
				command.codigoRequerimiento(), context.traceId(), context.requestId(), context.executedAt(),
				context.language(), null));
		return new AprobarRequerimientoPersonalResult(command.codigoRequerimiento(), command.accion(), "V1",
				context.traceId());
	}
}
