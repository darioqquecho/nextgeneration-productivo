package net.royal.erp.modules.hr.application.requerimiento.aprobar;

import net.royal.erp.framework.application.RoyalObservedUseCase;
import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.observability.ObservabilityPort;

/**
 * Decorador de observabilidad para aprobacion de requerimientos.
 */
public class ObservedAprobarRequerimientoPersonalUseCase extends RoyalObservedUseCase
		implements AprobarRequerimientoPersonalUseCase {
	private final AprobarRequerimientoPersonalUseCase delegate;

	public ObservedAprobarRequerimientoPersonalUseCase(AprobarRequerimientoPersonalUseCase delegate,
			ObservabilityPort observability) {
		super(observability);
		this.delegate = delegate;
	}

	@Override
	public AprobarRequerimientoPersonalResult execute(AprobarRequerimientoPersonalCommand command,
			FunctionalContext context) {
		return observe(context, () -> delegate.execute(command, context));
	}
}
