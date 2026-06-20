package net.royal.erp.framework.application;

import net.royal.erp.framework.kernel.FunctionalContext;

/**
 * Clase base para casos de uso que enrutan a implementaciones versionadas.
 */
public abstract class RoyalBaseVersionedUseCase<T> extends RoyalDelegatingUseCase {
	private final ModuleVersionRouter<T> router;

	protected RoyalBaseVersionedUseCase(ModuleVersionRouter<T> router) {
		this.router = router;
	}

	protected T delegate(FunctionalContext context) {
		return router.delegate(context);
	}
}
