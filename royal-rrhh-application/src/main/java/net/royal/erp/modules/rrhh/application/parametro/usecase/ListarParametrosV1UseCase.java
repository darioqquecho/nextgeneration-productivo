package net.royal.erp.modules.rrhh.application.parametro.usecase;

import net.royal.erp.framework.kernel.*;
import net.royal.erp.modules.rrhh.application.common.UseCaseGuards;
import net.royal.erp.modules.rrhh.application.parametro.dto.*;
import net.royal.erp.modules.rrhh.domain.parametro.*;

/**
 * Implementa: - MOD-012 CU-001 ListarParametros V1. - ARCH-013 CQRS Query.
 */
public class ListarParametrosV1UseCase implements ListarParametrosUseCase {
	private final ParametroRepository repository;
	private final UseCaseGuards guards;

	public ListarParametrosV1UseCase(ParametroRepository repository, UseCaseGuards guards) {
		this.repository = repository;
		this.guards = guards;
	}

	public ListarParametrosResult execute(ListarParametrosQuery command, FunctionalContext context) {
		guards.check(context, "RRHH_PARAMETRO_CONSULTAR");
		return new ListarParametrosResult(repository.findAll().stream()
				.map(p -> ParametroResultMapper.toResult(p, context.traceId())).toList(), context.traceId());
	}
}
