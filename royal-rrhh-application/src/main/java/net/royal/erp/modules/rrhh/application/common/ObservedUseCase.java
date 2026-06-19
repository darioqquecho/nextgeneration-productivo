package net.royal.erp.modules.rrhh.application.common;

import net.royal.erp.framework.kernel.*;
import net.royal.erp.framework.observability.*;

/**
 * Decorador generico para observar casos de uso simples.
 */
public class ObservedUseCase<C, R> implements UseCase<C, R> {
	private final UseCase<C, R> delegate;
	private final ObservabilityPort observability;

	public ObservedUseCase(UseCase<C, R> delegate, ObservabilityPort observability) {
		this.delegate = delegate;
		this.observability = observability;
	}

	@Override
	public R execute(C command, FunctionalContext context) {
		ExecutionTimer timer = ExecutionTimer.start();
		try {
			R result = delegate.execute(command, context);
			observability.succeeded(context, timer.stopMillis());
			return result;
		} catch (RuntimeException e) {
			observability.failed(context, timer.stopMillis(), e);
			throw e;
		}
	}
}
