package net.royal.erp.modules.rrhh.application.parametro.usecase;

import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.observability.*;
import net.royal.erp.modules.rrhh.application.parametro.dto.*;

/**
 * Decorador de observabilidad para reporte de parametros.
 */
public class ObservedReporteParametrosUseCase implements ReporteParametrosUseCase {
	private final ReporteParametrosUseCase delegate;
	private final ObservabilityPort observability;

	public ObservedReporteParametrosUseCase(ReporteParametrosUseCase delegate, ObservabilityPort observability) {
		this.delegate = delegate;
		this.observability = observability;
	}

	@Override
	public ReporteParametrosResult ejecutar(ReporteParametrosQuery query, FunctionalContext context) {
		ExecutionTimer timer = ExecutionTimer.start();
		try {
			ReporteParametrosResult result = delegate.ejecutar(query, context);
			observability.succeeded(context, timer.stopMillis());
			return result;
		} catch (RuntimeException e) {
			observability.failed(context, timer.stopMillis(), e);
			throw e;
		}
	}
}
