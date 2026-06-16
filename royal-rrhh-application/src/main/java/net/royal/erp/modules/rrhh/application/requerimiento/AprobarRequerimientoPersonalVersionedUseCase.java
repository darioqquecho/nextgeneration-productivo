package net.royal.erp.modules.rrhh.application.requerimiento;

import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.versioning.*;

/**
 * Implementa: - ARCH-010 Versionamiento Funcional.
 */
public class AprobarRequerimientoPersonalVersionedUseCase implements AprobarRequerimientoPersonalUseCase {
	private final FunctionalVersionResolver resolver;
	private final AprobarRequerimientoPersonalUseCase v1;
	private final AprobarRequerimientoPersonalUseCase v2;

	public AprobarRequerimientoPersonalVersionedUseCase(FunctionalVersionResolver resolver,
			AprobarRequerimientoPersonalUseCase v1, AprobarRequerimientoPersonalUseCase v2) {
		this.resolver = resolver;
		this.v1 = v1;
		this.v2 = v2;
	}

	public AprobarRequerimientoPersonalResult execute(AprobarRequerimientoPersonalCommand command,
			FunctionalContext context) {
		return resolver.resolve(context) == FunctionalVersion.V2 ? v2.execute(command, context)
				: v1.execute(command, context);
	}
}
