package net.royal.erp.modules.hr.application.capacitacion.registrar;

import net.royal.erp.framework.application.RoyalDelegatingUseCase;
import net.royal.erp.framework.kernel.*;

/**
 * Implementa: - ARCH-010 Versionamiento Funcional.
 *
 * Propósito: V2 exige instructor obligatorio.
 */
public class RegistrarCapacitacionV2UseCase extends RoyalDelegatingUseCase implements RegistrarCapacitacionUseCase {
	private final RegistrarCapacitacionV1UseCase base;

	public RegistrarCapacitacionV2UseCase(RegistrarCapacitacionV1UseCase base) {
		this.base = base;
	}

	public RegistrarCapacitacionResult execute(RegistrarCapacitacionCommand command, FunctionalContext context) {
		if (command.instructor() == null || command.instructor().isBlank())
			throw new BusinessException("CAP-V2-001");
		RegistrarCapacitacionResult r = base.execute(command, context);
		return new RegistrarCapacitacionResult(r.codigo(), r.estado(), "V2", r.traceId());
	}
}
