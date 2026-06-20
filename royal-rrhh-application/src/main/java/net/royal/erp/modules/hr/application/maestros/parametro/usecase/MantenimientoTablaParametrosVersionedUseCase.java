package net.royal.erp.modules.hr.application.maestros.parametro.usecase;

import net.royal.erp.framework.application.*;
import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.versioning.*;
import net.royal.erp.modules.hr.application.maestros.parametro.dto.*;

/**
 * Implementa: - MOD-012 CU-001 Mantenimiento de Tabla Parametros. - ARCH-010
 * Versionamiento Funcional.
 */
public class MantenimientoTablaParametrosVersionedUseCase extends RoyalBaseVersionedUseCase<MantenimientoTablaParametrosUseCase>
		implements MantenimientoTablaParametrosUseCase {
	public MantenimientoTablaParametrosVersionedUseCase(FunctionalVersionResolver resolver,
			MantenimientoTablaParametrosUseCase v1, MantenimientoTablaParametrosUseCase v2) {
		super(new ModuleVersionRouter<MantenimientoTablaParametrosUseCase>(resolver).register(FunctionalVersion.V1, v1)
				.register(FunctionalVersion.V2, v2));
	}

	@Override
	public ListarParametrosResult listar(ListarParametrosQuery query, FunctionalContext context) {
		return delegate(context).listar(query, context);
	}

	@Override
	public CrearParametroResult crear(CrearParametroCommand command, FunctionalContext context) {
		return delegate(context).crear(command, context);
	}

	@Override
	public ParametroResult obtener(ObtenerParametroQuery query, FunctionalContext context) {
		return delegate(context).obtener(query, context);
	}

	@Override
	public ActualizarParametroResult actualizar(ActualizarParametroCommand command, FunctionalContext context) {
		return delegate(context).actualizar(command, context);
	}

	@Override
	public ParametroResult cambiarEstado(CambiarEstadoParametroCommand command, FunctionalContext context) {
		return delegate(context).cambiarEstado(command, context);
	}

	@Override
	public EliminarParametroResult eliminar(EliminarParametroCommand command, FunctionalContext context) {
		return delegate(context).eliminar(command, context);
	}
}
