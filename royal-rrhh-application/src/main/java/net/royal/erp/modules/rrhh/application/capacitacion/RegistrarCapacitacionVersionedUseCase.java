package net.royal.erp.modules.rrhh.application.capacitacion;

import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.versioning.*;

/**
 * Implementa: - ARCH-010 Versionamiento Funcional.
 */
public class RegistrarCapacitacionVersionedUseCase implements RegistrarCapacitacionUseCase {
	private final FunctionalVersionResolver resolver;
	private final RegistrarCapacitacionUseCase v1;
	private final RegistrarCapacitacionUseCase v2;

	public RegistrarCapacitacionVersionedUseCase(FunctionalVersionResolver resolver, RegistrarCapacitacionUseCase v1,
			RegistrarCapacitacionUseCase v2) {
		this.resolver = resolver;
		this.v1 = v1;
		this.v2 = v2;
	}

	public RegistrarCapacitacionResult execute(RegistrarCapacitacionCommand command, FunctionalContext context) {
		return resolver.resolve(context) == FunctionalVersion.V2 ? v2.execute(command, context)
				: v1.execute(command, context);
	}
}
