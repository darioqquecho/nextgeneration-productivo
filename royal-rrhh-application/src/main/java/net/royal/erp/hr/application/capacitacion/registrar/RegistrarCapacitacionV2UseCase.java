package net.royal.erp.hr.application.capacitacion.registrar;

import net.royal.erp.framework.audit.AuditPort;
import net.royal.erp.framework.kernel.*;
import net.royal.erp.framework.security.UseCaseGuards;
import net.royal.erp.hr.application.capacitacion.registrar.port.CapacitacionRepository;

/**
 * Implementa: - ARCH-010 Versionamiento Funcional.
 *
 * Propósito: V2 exige instructor obligatorio.
 */
public class RegistrarCapacitacionV2UseCase extends RegistrarCapacitacionV1UseCase {
	public RegistrarCapacitacionV2UseCase(CapacitacionRepository repository, UseCaseGuards guards,
			AuditPort auditPort) {
		super(repository, guards, auditPort);
	}

	@Override
	public RegistrarCapacitacionResult execute(RegistrarCapacitacionCommand command, FunctionalContext context) {
		if (command.instructor() == null || command.instructor().isBlank())
			throw new BusinessException("CAP-V2-001");
		RegistrarCapacitacionResult r = super.execute(command, context);
		return new RegistrarCapacitacionResult(r.codigo(), r.estado(), "V2", r.traceId());
	}
}
