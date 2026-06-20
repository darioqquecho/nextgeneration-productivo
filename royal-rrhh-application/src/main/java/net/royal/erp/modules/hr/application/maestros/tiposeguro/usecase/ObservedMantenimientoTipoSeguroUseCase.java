package net.royal.erp.modules.hr.application.maestros.tiposeguro.usecase;

import net.royal.erp.framework.application.RoyalObservedUseCase;
import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.observability.ObservabilityPort;
import net.royal.erp.modules.hr.application.maestros.tiposeguro.dto.*;

public class ObservedMantenimientoTipoSeguroUseCase extends RoyalObservedUseCase
		implements MantenimientoTipoSeguroUseCase {
	private final MantenimientoTipoSeguroUseCase delegate;

	public ObservedMantenimientoTipoSeguroUseCase(MantenimientoTipoSeguroUseCase delegate,
			ObservabilityPort observability) {
		super(observability);
		this.delegate = delegate;
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
}
