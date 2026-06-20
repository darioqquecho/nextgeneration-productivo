package net.royal.erp.framework.application;

import java.util.function.Supplier;

import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.observability.ExecutionTimer;
import net.royal.erp.framework.observability.ObservabilityPort;

/**
 * Clase maestra para decoradores de observabilidad de casos de uso.
 */
public abstract class RoyalObservedUseCase extends RoyalDelegatingUseCase {
	private final ObservabilityPort observability;

	protected RoyalObservedUseCase(ObservabilityPort observability) {
		this.observability = observability;
	}

	protected <T> T observe(FunctionalContext context, Supplier<T> action) {
		ExecutionTimer timer = ExecutionTimer.start();
		try {
			T result = action.get();
			observability.succeeded(context, timer.stopMillis());
			return result;
		} catch (RuntimeException e) {
			observability.failed(context, timer.stopMillis(), e);
			throw e;
		}
	}
}
