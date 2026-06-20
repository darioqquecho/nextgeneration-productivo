package net.royal.erp.modules.hr.application.maestros.parametro.usecase;

import net.royal.erp.framework.application.RoyalObservedUseCase;
import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.observability.ObservabilityPort;
import net.royal.erp.modules.hr.application.maestros.parametro.dto.*;

/**
 * Decorador de observabilidad para aprobacion masiva de parametros.
 */
public class ObservedAprobacionMasivaParametrosUseCase extends RoyalObservedUseCase
		implements AprobacionMasivaParametrosUseCase {
	private final AprobacionMasivaParametrosUseCase delegate;

	public ObservedAprobacionMasivaParametrosUseCase(AprobacionMasivaParametrosUseCase delegate,
			ObservabilityPort observability) {
		super(observability);
		this.delegate = delegate;
	}

	@Override
	public AprobarMasivamenteParametrosResult aprobar(AprobarMasivamenteParametrosCommand command,
			FunctionalContext context) {
		return observe(context, () -> delegate.aprobar(command, context));
	}
}
