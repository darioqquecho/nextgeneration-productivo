package net.royal.erp.modules.hr.application.maestros.tiposeguro.usecase;

import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.modules.hr.application.maestros.tiposeguro.dto.*;

public interface MantenimientoTipoSeguroUseCase {
	ListarTiposSeguroResult listar(ListarTiposSeguroQuery query, FunctionalContext context);

	CrearTipoSeguroResult crear(CrearTipoSeguroCommand command, FunctionalContext context);

	TipoSeguroResult obtener(ObtenerTipoSeguroQuery query, FunctionalContext context);

	ActualizarTipoSeguroResult actualizar(ActualizarTipoSeguroCommand command, FunctionalContext context);

	TipoSeguroResult cambiarEstado(CambiarEstadoTipoSeguroCommand command, FunctionalContext context);

	EliminarTipoSeguroResult eliminar(EliminarTipoSeguroCommand command, FunctionalContext context);
}
