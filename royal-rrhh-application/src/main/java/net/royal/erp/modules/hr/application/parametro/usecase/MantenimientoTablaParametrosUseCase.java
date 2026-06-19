package net.royal.erp.modules.hr.application.parametro.usecase;

import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.modules.hr.application.parametro.dto.*;

/**
 * Implementa: - MOD-012 CU-001 Mantenimiento de Tabla Parametros.
 */
public interface MantenimientoTablaParametrosUseCase {
	ListarParametrosResult listar(ListarParametrosQuery query, FunctionalContext context);

	CrearParametroResult crear(CrearParametroCommand command, FunctionalContext context);

	ParametroResult obtener(ObtenerParametroQuery query, FunctionalContext context);

	ActualizarParametroResult actualizar(ActualizarParametroCommand command, FunctionalContext context);

	ParametroResult cambiarEstado(CambiarEstadoParametroCommand command, FunctionalContext context);

	EliminarParametroResult eliminar(EliminarParametroCommand command, FunctionalContext context);
}
