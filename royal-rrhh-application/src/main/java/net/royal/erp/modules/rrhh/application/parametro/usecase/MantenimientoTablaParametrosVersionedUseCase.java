package net.royal.erp.modules.rrhh.application.parametro.usecase;

import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.versioning.*;
import net.royal.erp.modules.rrhh.application.parametro.dto.*;

/**
 * Implementa: - MOD-012 CU-001 Mantenimiento de Tabla Parametros. - ARCH-010
 * Versionamiento Funcional.
 */
public class MantenimientoTablaParametrosVersionedUseCase implements MantenimientoTablaParametrosUseCase {
	private final FunctionalVersionResolver resolver;
	private final MantenimientoTablaParametrosUseCase v1;
	private final MantenimientoTablaParametrosUseCase v2;

	public MantenimientoTablaParametrosVersionedUseCase(FunctionalVersionResolver resolver,
			MantenimientoTablaParametrosUseCase v1, MantenimientoTablaParametrosUseCase v2) {
		this.resolver = resolver;
		this.v1 = v1;
		this.v2 = v2;
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

	private MantenimientoTablaParametrosUseCase delegate(FunctionalContext context) {
		return resolver.resolve(context) == FunctionalVersion.V2 ? v2 : v1;
	}
}
