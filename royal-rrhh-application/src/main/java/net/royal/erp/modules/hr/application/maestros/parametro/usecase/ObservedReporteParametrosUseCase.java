package net.royal.erp.modules.hr.application.maestros.parametro.usecase;

import net.royal.erp.framework.application.RoyalObservedUseCase;
import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.observability.ObservabilityPort;
import net.royal.erp.modules.hr.application.maestros.parametro.dto.*;

/**
 * Decorador de observabilidad para reporte de parametros.
 */
public class ObservedReporteParametrosUseCase extends RoyalObservedUseCase implements ReporteParametrosUseCase {
	private final ReporteParametrosUseCase delegate;

	public ObservedReporteParametrosUseCase(ReporteParametrosUseCase delegate, ObservabilityPort observability) {
		super(observability);
		this.delegate = delegate;
	}

	@Override
	public ReporteParametrosResult ejecutar(ReporteParametrosQuery query, FunctionalContext context) {
		return observe(context, () -> delegate.ejecutar(query, context));
	}
}
