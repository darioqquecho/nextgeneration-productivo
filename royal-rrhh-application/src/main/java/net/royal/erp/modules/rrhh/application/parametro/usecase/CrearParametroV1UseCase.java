package net.royal.erp.modules.rrhh.application.parametro.usecase;

import net.royal.erp.framework.audit.*;
import net.royal.erp.framework.kernel.*;
import net.royal.erp.modules.rrhh.application.common.UseCaseGuards;
import net.royal.erp.modules.rrhh.application.parametro.dto.*;
import net.royal.erp.modules.rrhh.domain.parametro.*;

/**
 * Implementa: - MOD-012 CU-001 CrearParametro V1. - ARCH-013 CQRS.
 */
public class CrearParametroV1UseCase implements CrearParametroUseCase {
	private final ParametroRepository repository;
	private final UseCaseGuards guards;
	private final AuditPort auditPort;

	public CrearParametroV1UseCase(ParametroRepository repository, UseCaseGuards guards, AuditPort auditPort) {
		this.repository = repository;
		this.guards = guards;
		this.auditPort = auditPort;
	}

	protected String functionalVersion() {
		return "V1";
	}

	public CrearParametroResult execute(CrearParametroCommand command, FunctionalContext context) {
		guards.check(context, "RRHH_PARAMETRO_CREAR");
		ParametroId id = new ParametroId(command.compania(), command.codigo());
		if (repository.existsById(id)) {
			throw new BusinessException("HR-PAR-004", "Parametro ya existe");
		}
		Parametro parametro = Parametro.crear(command.compania(), command.codigo(), command.nombre(), context.userId(),
				context.executedAt());
		repository.save(parametro);
		auditPort.register(new FunctionalAuditRecord("RRHH", "Parametros", "Crear Parametro", "CrearParametroUseCase",
				functionalVersion(), context.userId(), "OK", "HR_Parametros", id.value(), context.traceId(),
				context.requestId(), context.executedAt()));
		return new CrearParametroResult(command.compania(), command.codigo(), "CREADO", context.traceId());
	}
}
