package net.royal.erp.modules.hr.application.capacitacion.registrar;

import net.royal.erp.framework.application.*;
import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.versioning.*;

/**
 * Implementa: - ARCH-010 Versionamiento Funcional.
 */
public class RegistrarCapacitacionVersionedUseCase extends RoyalBaseVersionedUseCase<RegistrarCapacitacionUseCase>
		implements RegistrarCapacitacionUseCase {
	public RegistrarCapacitacionVersionedUseCase(FunctionalVersionResolver resolver, RegistrarCapacitacionUseCase v1,
			RegistrarCapacitacionUseCase v2) {
		super(new ModuleVersionRouter<RegistrarCapacitacionUseCase>(resolver).register(FunctionalVersion.V1, v1)
				.register(FunctionalVersion.V2, v2));
	}

	public RegistrarCapacitacionResult execute(RegistrarCapacitacionCommand command, FunctionalContext context) {
		return delegate(context).execute(command, context);
	}
}
