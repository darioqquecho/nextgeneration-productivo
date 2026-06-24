package net.royal.erp.hr.application.requerimiento.aprobar;

import net.royal.erp.framework.application.RoyalBaseUseCase;
import net.royal.erp.framework.audit.*;
import net.royal.erp.framework.kernel.*;
import net.royal.erp.framework.security.UseCaseGuards;
import net.royal.erp.hr.application.process.RrhhProcessCatalog;

/**
 * Implementa: - MOD-012 CU-003 AprobarRequerimientoPersonal.
 */
public class AprobarRequerimientoPersonalV1UseCase extends RoyalBaseUseCase {
	public AprobarRequerimientoPersonalV1UseCase(UseCaseGuards guards, AuditPort auditPort) {
		super(RrhhProcessCatalog.MODULE, "Requerimiento", "V1", guards, auditPort);
	}

	public AprobarRequerimientoPersonalResult execute(AprobarRequerimientoPersonalCommand command,
			FunctionalContext context) {
		checkGuards(context);
		audit(context, command.accion(), command.codigoRequerimiento(), null);
		return new AprobarRequerimientoPersonalResult(command.codigoRequerimiento(), command.accion(), "V1",
				context.traceId());
	}
}
