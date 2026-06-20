package net.royal.erp.modules.hr.application.maestros.parametro.usecase;

import net.royal.erp.framework.application.RoyalObservedUseCase;
import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.observability.ObservabilityPort;
import net.royal.erp.modules.hr.application.maestros.parametro.dto.*;

/**
 * Decorador de observabilidad para el mantenimiento de parametros.
 */
public class ObservedMantenimientoTablaParametrosUseCase extends RoyalObservedUseCase
		implements MantenimientoTablaParametrosUseCase {
	private final MantenimientoTablaParametrosUseCase delegate;

	public ObservedMantenimientoTablaParametrosUseCase(MantenimientoTablaParametrosUseCase delegate,
			ObservabilityPort observability) {
		super(observability);
		this.delegate = delegate;
	}

	@Override
	public ListarParametrosResult listar(ListarParametrosQuery query, FunctionalContext context) {
		return observe(context, () -> delegate.listar(query, context));
	}

	@Override
	public CrearParametroResult crear(CrearParametroCommand command, FunctionalContext context) {
		return observe(context, () -> delegate.crear(command, context));
	}

	@Override
	public ParametroResult obtener(ObtenerParametroQuery query, FunctionalContext context) {
		return observe(context, () -> delegate.obtener(query, context));
	}

	@Override
	public ActualizarParametroResult actualizar(ActualizarParametroCommand command, FunctionalContext context) {
		return observe(context, () -> delegate.actualizar(command, context));
	}

	@Override
	public ParametroResult cambiarEstado(CambiarEstadoParametroCommand command, FunctionalContext context) {
		return observe(context, () -> delegate.cambiarEstado(command, context));
	}

	@Override
	public EliminarParametroResult eliminar(EliminarParametroCommand command, FunctionalContext context) {
		return observe(context, () -> delegate.eliminar(command, context));
	}
}
