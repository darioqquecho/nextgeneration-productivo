package net.royal.erp.modules.rrhh.application.parametro.usecase;

import net.royal.erp.framework.kernel.*;
import net.royal.erp.modules.rrhh.application.common.UseCaseGuards;
import net.royal.erp.modules.rrhh.application.parametro.dto.*;
import net.royal.erp.modules.rrhh.domain.parametro.*;

/**
 * Implementa: - MOD-012 CU-001 ObtenerParametro V1. - ARCH-013 CQRS Query.
 */
public class ObtenerParametroV1UseCase implements ObtenerParametroUseCase {
	private final ParametroRepository repository;
	private final UseCaseGuards guards;

	public ObtenerParametroV1UseCase(ParametroRepository repository, UseCaseGuards guards) {
		this.repository = repository;
		this.guards = guards;
	}

	public ParametroResult execute(ObtenerParametroQuery command, FunctionalContext context) {
		guards.check(context, "RRHH_PARAMETRO_CONSULTAR");
		Parametro p = repository.findById(new ParametroId(command.compania(), command.codigo()))
				.orElseThrow(() -> new BusinessException("HR-PAR-404", "Parametro no encontrado"));
		return ParametroResultMapper.toResult(p, context.traceId());
	}
}
