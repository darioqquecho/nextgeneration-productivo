package net.royal.erp.modules.hr.application.parametro.usecase;

import java.util.function.Supplier;

import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.observability.*;
import net.royal.erp.modules.hr.application.parametro.dto.*;

/**
 * Decorador de observabilidad para el mantenimiento de parametros.
 */
public class ObservedMantenimientoTablaParametrosUseCase implements MantenimientoTablaParametrosUseCase {
	private final MantenimientoTablaParametrosUseCase delegate;
	private final ObservabilityPort observability;

	public ObservedMantenimientoTablaParametrosUseCase(MantenimientoTablaParametrosUseCase delegate,
			ObservabilityPort observability) {
		this.delegate = delegate;
		this.observability = observability;
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

	private <T> T observe(FunctionalContext context, Supplier<T> action) {
		ExecutionTimer timer = ExecutionTimer.start();
		try {
			T result = action.get();
			observability.succeeded(context, timer.stopMillis());
			return result;
		} catch (RuntimeException e) {
			observability.failed(context, timer.stopMillis(), e);
			throw e;
		}
	}
}
