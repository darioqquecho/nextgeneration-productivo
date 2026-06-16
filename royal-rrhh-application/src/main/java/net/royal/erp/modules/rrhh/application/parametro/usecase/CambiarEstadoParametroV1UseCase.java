package net.royal.erp.modules.rrhh.application.parametro.usecase;

import net.royal.erp.framework.audit.*;
import net.royal.erp.framework.kernel.*;
import net.royal.erp.modules.rrhh.application.common.UseCaseGuards;
import net.royal.erp.modules.rrhh.application.parametro.dto.*;
import net.royal.erp.modules.rrhh.domain.parametro.*;

/**
 * Implementa: - MOD-012 CU-001 CambiarEstadoParametro V1.
 */
public class CambiarEstadoParametroV1UseCase implements CambiarEstadoParametroUseCase {
	private final ParametroRepository repository;
	private final UseCaseGuards guards;
	private final AuditPort auditPort;

	public CambiarEstadoParametroV1UseCase(ParametroRepository repository, UseCaseGuards guards, AuditPort auditPort) {
		this.repository = repository;
		this.guards = guards;
		this.auditPort = auditPort;
	}

	protected String functionalVersion() {
		return "V1";
	}

	public ParametroResult execute(CambiarEstadoParametroCommand command, FunctionalContext context) {
		guards.check(context, "RRHH_PARAMETRO_CAMBIARESTADO");
		ParametroId id = new ParametroId(command.compania(), command.codigo());
		Parametro parametro = repository.findById(id)
				.orElseThrow(() -> new BusinessException("HR-PAR-404", "Parametro no encontrado"));
		parametro.cambiarEstado(ParametroEstado.valueOf(command.estado().toUpperCase()), context.userId(),
				context.executedAt());
		repository.save(parametro);
		auditPort.register(new FunctionalAuditRecord("RRHH", "Parametros", "Cambiar Estado Parametro",
				"CambiarEstadoParametroUseCase", functionalVersion(), context.userId(), "OK", "HR_Parametros",
				id.value(), context.traceId(), context.requestId(), context.executedAt()));
		return ParametroResultMapper.toResult(parametro, context.traceId());
	}
}
