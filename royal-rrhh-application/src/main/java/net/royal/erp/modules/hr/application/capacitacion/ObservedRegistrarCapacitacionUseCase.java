package net.royal.erp.modules.hr.application.capacitacion;

import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.observability.*;

/**
 * Decorador de observabilidad para registrar capacitacion.
 */
public class ObservedRegistrarCapacitacionUseCase implements RegistrarCapacitacionUseCase {
	private final RegistrarCapacitacionUseCase delegate;
	private final ObservabilityPort observability;

	public ObservedRegistrarCapacitacionUseCase(RegistrarCapacitacionUseCase delegate, ObservabilityPort observability) {
		this.delegate = delegate;
		this.observability = observability;
	}

	@Override
	public RegistrarCapacitacionResult execute(RegistrarCapacitacionCommand command, FunctionalContext context) {
		ExecutionTimer timer = ExecutionTimer.start();
		try {
			RegistrarCapacitacionResult result = delegate.execute(command, context);
			observability.succeeded(context, timer.stopMillis());
			return result;
		} catch (RuntimeException e) {
			observability.failed(context, timer.stopMillis(), e);
			throw e;
		}
	}
}
