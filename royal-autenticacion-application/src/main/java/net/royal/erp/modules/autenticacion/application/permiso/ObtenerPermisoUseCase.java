package net.royal.erp.modules.autenticacion.application.permiso;

import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.kernel.UseCase;

/**
 * Consulta permisos y devuelve el resultado detallado.
 */
public interface ObtenerPermisoUseCase extends UseCase<ObtenerPermisoCommand, ObtenerPermisoResult> {
	ObtenerPermisoResult execute(ObtenerPermisoCommand command, FunctionalContext context);
}
