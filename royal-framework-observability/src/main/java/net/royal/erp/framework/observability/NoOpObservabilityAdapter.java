package net.royal.erp.framework.observability;

import net.royal.erp.framework.kernel.FunctionalContext;

/**
 * Adapter nulo para pruebas o despliegues sin colector de observabilidad.
 */
public class NoOpObservabilityAdapter implements ObservabilityPort {
	@Override
	public void succeeded(FunctionalContext context, long durationMillis) {
	}

	@Override
	public void failed(FunctionalContext context, long durationMillis, Throwable error) {
	}
}
