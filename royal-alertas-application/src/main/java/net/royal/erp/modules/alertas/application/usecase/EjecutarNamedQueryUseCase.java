package net.royal.erp.modules.alertas.application.usecase;

import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.kernel.UseCase;
import net.royal.erp.modules.alertas.application.common.AlertasUseCaseGuards;
import net.royal.erp.modules.alertas.application.dto.*;
import net.royal.erp.modules.alertas.domain.repository.AlertasNamedQueryRepository;

/**
 * Implementa: - ARCH-009 Bases de Datos. - ASIS-024 SQL HBM XML. - MOD-012
 * Migración funcional de queries legacy.
 *
 * Propósito: Ejecuta queries SQL HBM XML heredadas sin acoplar API a
 * JdbcTemplate.
 */
public class EjecutarNamedQueryUseCase implements UseCase<NamedQueryCommand, AlertasListResult> {
	private final AlertasNamedQueryRepository repository;
	private final AlertasUseCaseGuards guards;

	public EjecutarNamedQueryUseCase(AlertasNamedQueryRepository repository, AlertasUseCaseGuards guards) {
		this.repository = repository;
		this.guards = guards;
	}

	/**
	 * Ejecuta query legacy por nombre.
	 *
	 * Implementa: - ASIS-024 SQL HBM XML.
	 */
	@Override
	public AlertasListResult execute(NamedQueryCommand command, FunctionalContext context) {
		guards.check(context, "ALERTAS_SQL_EJECUTAR");
		return new AlertasListResult(command.queryName(), repository.query(command.queryName(), command.parameters()),
				context.traceId());
	}
}
