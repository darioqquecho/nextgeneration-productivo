package net.royal.erp.modules.rrhh.application.parametro.usecase;

import net.royal.erp.framework.audit.*;
import net.royal.erp.framework.kernel.*;
import net.royal.erp.modules.rrhh.application.common.UseCaseGuards;
import net.royal.erp.modules.rrhh.application.parametro.dto.*;
import net.royal.erp.modules.rrhh.domain.parametro.*;

/**
 * Implementa: - MOD-012 CU-001 ActualizarParametro V1.
 */
public class ActualizarParametroV1UseCase implements ActualizarParametroUseCase {
	private final ParametroRepository repository;
	private final UseCaseGuards guards;
	private final AuditPort auditPort;

	public ActualizarParametroV1UseCase(ParametroRepository repository, UseCaseGuards guards, AuditPort auditPort) {
		this.repository = repository;
		this.guards = guards;
		this.auditPort = auditPort;
	}

	protected String functionalVersion() {
		return "V1";
	}

	public ActualizarParametroResult execute(ActualizarParametroCommand command, FunctionalContext context) {
		guards.check(context, "RRHH_PARAMETRO_ACTUALIZAR");
		ParametroId id = new ParametroId(command.compania(), command.codigo());
		Parametro parametro = repository.findById(id)
				.orElseThrow(() -> new BusinessException("HR-PAR-404", "Parametro no encontrado"));
		parametro.actualizarNombre(command.nombre(), context.userId(), context.executedAt());
		repository.save(parametro);
		auditPort.register(new FunctionalAuditRecord("RRHH", "Parametros", "Actualizar Parametro",
				"ActualizarParametroUseCase", functionalVersion(), context.userId(), "OK", "HR_Parametros",
				id.value(), context.traceId(), context.requestId(), context.executedAt()));
		return new ActualizarParametroResult(command.compania(), command.codigo(), "ACTUALIZADO", context.traceId());
	}
}
