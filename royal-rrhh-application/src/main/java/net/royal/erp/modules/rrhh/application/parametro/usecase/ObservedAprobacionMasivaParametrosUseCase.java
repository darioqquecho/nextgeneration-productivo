package net.royal.erp.modules.rrhh.application.parametro.usecase;

import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.observability.*;
import net.royal.erp.modules.rrhh.application.parametro.dto.*;

/**
 * Decorador de observabilidad para aprobacion masiva de parametros.
 */
public class ObservedAprobacionMasivaParametrosUseCase implements AprobacionMasivaParametrosUseCase {
	private final AprobacionMasivaParametrosUseCase delegate;
	private final ObservabilityPort observability;

	public ObservedAprobacionMasivaParametrosUseCase(AprobacionMasivaParametrosUseCase delegate,
			ObservabilityPort observability) {
		this.delegate = delegate;
		this.observability = observability;
	}

	@Override
	public AprobarMasivamenteParametrosResult aprobar(AprobarMasivamenteParametrosCommand command,
			FunctionalContext context) {
		ExecutionTimer timer = ExecutionTimer.start();
		try {
			AprobarMasivamenteParametrosResult result = delegate.aprobar(command, context);
			observability.succeeded(context, timer.stopMillis());
			return result;
		} catch (RuntimeException e) {
			observability.failed(context, timer.stopMillis(), e);
			throw e;
		}
	}
}
