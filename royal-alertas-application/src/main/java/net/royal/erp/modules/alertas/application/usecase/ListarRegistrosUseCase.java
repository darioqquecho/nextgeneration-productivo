package net.royal.erp.modules.alertas.application.usecase;

import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.kernel.UseCase;
import net.royal.erp.modules.alertas.application.common.AlertasUseCaseGuards;
import net.royal.erp.modules.alertas.application.dto.*;
import net.royal.erp.modules.alertas.domain.repository.AlertasRecordRepository;

/**
 * Implementa: - MOD-012 Migración funcional de listados legacy. - ARCH-013 CQRS
 * Query.
 */
public class ListarRegistrosUseCase implements UseCase<AlertasIdCommand, AlertasListResult> {
	private final AlertasRecordRepository repository;
	private final AlertasUseCaseGuards guards;

	public ListarRegistrosUseCase(AlertasRecordRepository repository, AlertasUseCaseGuards guards) {
		this.repository = repository;
		this.guards = guards;
	}

	/**
	 * Lista registros de una entidad legacy.
	 *
	 * Implementa: - ARCH-013 CQRS Query.
	 */
	@Override
	public AlertasListResult execute(AlertasIdCommand command, FunctionalContext context) {
		guards.check(context, "ALERTAS_CONSULTAR");
		return new AlertasListResult(command.entityName(), repository.findAll(command.entityName(), 200),
				context.traceId());
	}
}
