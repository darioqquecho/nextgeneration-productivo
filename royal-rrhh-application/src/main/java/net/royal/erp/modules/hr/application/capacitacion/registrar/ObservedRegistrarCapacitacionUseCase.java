package net.royal.erp.modules.hr.application.capacitacion.registrar;

import net.royal.erp.framework.application.RoyalObservedUseCase;
import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.observability.ObservabilityPort;

/**
 * Decorador de observabilidad para registrar capacitacion.
 */
public class ObservedRegistrarCapacitacionUseCase extends RoyalObservedUseCase implements RegistrarCapacitacionUseCase {
	private final RegistrarCapacitacionUseCase delegate;

	public ObservedRegistrarCapacitacionUseCase(RegistrarCapacitacionUseCase delegate, ObservabilityPort observability) {
		super(observability);
		this.delegate = delegate;
	}

	@Override
	public RegistrarCapacitacionResult execute(RegistrarCapacitacionCommand command, FunctionalContext context) {
		return observe(context, () -> delegate.execute(command, context));
	}
}
