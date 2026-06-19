package net.royal.erp.modules.rrhh.application.capacitacion;

import net.royal.erp.framework.audit.*;
import net.royal.erp.framework.kernel.*;
import net.royal.erp.framework.security.UseCaseGuards;
import net.royal.erp.modules.rrhh.domain.capacitacion.*;

/**
 * Implementa: - ARCH-010 Versionamiento Funcional. - MOD-012 CU-002
 * RegistrarCapacitacion.
 */
public class RegistrarCapacitacionV1UseCase implements RegistrarCapacitacionUseCase {
	private final CapacitacionRepository repository;
	private final UseCaseGuards guards;
	private final AuditPort auditPort;

	public RegistrarCapacitacionV1UseCase(CapacitacionRepository repository, UseCaseGuards guards,
			AuditPort auditPort) {
		this.repository = repository;
		this.guards = guards;
		this.auditPort = auditPort;
	}

	public RegistrarCapacitacionResult execute(RegistrarCapacitacionCommand command, FunctionalContext context) {
		guards.check(context);
		if (repository.existsByCodigo(command.codigo()))
			throw new BusinessException("CAP-004");
		Capacitacion c = Capacitacion.registrar(command.codigo(), command.nombre(), command.fechaInicio(),
				command.fechaFin(), command.instructor());
		repository.save(c);
		auditPort.register(new FunctionalAuditRecord(context.tenantId(), context.clientId(), "HR", context.process(),
				context.useCase(), context.functionality(), "V1", context.userId(), "OK", "Capacitacion",
				command.codigo(), context.traceId(), context.requestId(), context.executedAt(), context.language(),
				null));
		return new RegistrarCapacitacionResult(command.codigo(), "REGISTRADA", "V1", context.traceId());
	}
}
