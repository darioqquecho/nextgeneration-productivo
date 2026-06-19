package net.royal.erp.modules.hr.application.tiposeguro.usecase;

import java.util.function.Supplier;

import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.observability.*;
import net.royal.erp.modules.hr.application.tiposeguro.dto.*;

public class ObservedMantenimientoTipoSeguroUseCase implements MantenimientoTipoSeguroUseCase {
	private final MantenimientoTipoSeguroUseCase delegate;
	private final ObservabilityPort observability;

	public ObservedMantenimientoTipoSeguroUseCase(MantenimientoTipoSeguroUseCase delegate,
			ObservabilityPort observability) {
		this.delegate = delegate;
		this.observability = observability;
	}

	@Override
	public ListarTiposSeguroResult listar(ListarTiposSeguroQuery query, FunctionalContext context) {
		return observe(context, () -> delegate.listar(query, context));
	}

	@Override
	public CrearTipoSeguroResult crear(CrearTipoSeguroCommand command, FunctionalContext context) {
		return observe(context, () -> delegate.crear(command, context));
	}

	@Override
	public TipoSeguroResult obtener(ObtenerTipoSeguroQuery query, FunctionalContext context) {
		return observe(context, () -> delegate.obtener(query, context));
	}

	@Override
	public ActualizarTipoSeguroResult actualizar(ActualizarTipoSeguroCommand command, FunctionalContext context) {
		return observe(context, () -> delegate.actualizar(command, context));
	}

	@Override
	public TipoSeguroResult cambiarEstado(CambiarEstadoTipoSeguroCommand command, FunctionalContext context) {
		return observe(context, () -> delegate.cambiarEstado(command, context));
	}

	@Override
	public EliminarTipoSeguroResult eliminar(EliminarTipoSeguroCommand command, FunctionalContext context) {
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
