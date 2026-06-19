package net.royal.erp.modules.rrhh.application.requerimiento;

import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.observability.*;

/**
 * Decorador de observabilidad para aprobacion de requerimientos.
 */
public class ObservedAprobarRequerimientoPersonalUseCase implements AprobarRequerimientoPersonalUseCase {
	private final AprobarRequerimientoPersonalUseCase delegate;
	private final ObservabilityPort observability;

	public ObservedAprobarRequerimientoPersonalUseCase(AprobarRequerimientoPersonalUseCase delegate,
			ObservabilityPort observability) {
		this.delegate = delegate;
		this.observability = observability;
	}

	@Override
	public AprobarRequerimientoPersonalResult execute(AprobarRequerimientoPersonalCommand command,
			FunctionalContext context) {
		ExecutionTimer timer = ExecutionTimer.start();
		try {
			AprobarRequerimientoPersonalResult result = delegate.execute(command, context);
			observability.succeeded(context, timer.stopMillis());
			return result;
		} catch (RuntimeException e) {
			observability.failed(context, timer.stopMillis(), e);
			throw e;
		}
	}
}
