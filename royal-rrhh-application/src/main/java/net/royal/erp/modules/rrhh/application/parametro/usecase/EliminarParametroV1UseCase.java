package net.royal.erp.modules.rrhh.application.parametro.usecase;

import net.royal.erp.framework.audit.*;
import net.royal.erp.framework.kernel.*;
import net.royal.erp.modules.rrhh.application.common.UseCaseGuards;
import net.royal.erp.modules.rrhh.application.parametro.dto.*;
import net.royal.erp.modules.rrhh.domain.parametro.*;

/**
 * Implementa: - MOD-012 CU-001 EliminarParametro V1.
 */
public class EliminarParametroV1UseCase implements EliminarParametroUseCase {
	private final ParametroRepository repository;
	private final UseCaseGuards guards;
	private final AuditPort auditPort;

	public EliminarParametroV1UseCase(ParametroRepository repository, UseCaseGuards guards, AuditPort auditPort) {
		this.repository = repository;
		this.guards = guards;
		this.auditPort = auditPort;
	}

	protected String functionalVersion() {
		return "V1";
	}

	public EliminarParametroResult execute(EliminarParametroCommand command, FunctionalContext context) {
		guards.check(context, "RRHH_PARAMETRO_ELIMINAR");
		ParametroId id = new ParametroId(command.compania(), command.codigo());
		repository.deleteById(id);
		auditPort.register(new FunctionalAuditRecord("RRHH", "Parametros", "Eliminar Parametro",
				"EliminarParametroUseCase", functionalVersion(), context.userId(), "OK", "HR_Parametros",
				id.value(), context.traceId(), context.requestId(), context.executedAt()));
		return new EliminarParametroResult(command.compania(), command.codigo(), true, context.traceId());
	}
}
