package net.royal.erp.modules.hr.application.requerimiento.aprobar;

import net.royal.erp.framework.application.*;
import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.versioning.*;

/**
 * Implementa: - ARCH-010 Versionamiento Funcional.
 */
public class AprobarRequerimientoPersonalVersionedUseCase
		extends RoyalBaseVersionedUseCase<AprobarRequerimientoPersonalUseCase>
		implements AprobarRequerimientoPersonalUseCase {
	public AprobarRequerimientoPersonalVersionedUseCase(FunctionalVersionResolver resolver,
			AprobarRequerimientoPersonalUseCase v1, AprobarRequerimientoPersonalUseCase v2) {
		super(new ModuleVersionRouter<AprobarRequerimientoPersonalUseCase>(resolver)
				.register(FunctionalVersion.V1, v1).register(FunctionalVersion.V2, v2));
	}

	public AprobarRequerimientoPersonalResult execute(AprobarRequerimientoPersonalCommand command,
			FunctionalContext context) {
		return delegate(context).execute(command, context);
	}
}
