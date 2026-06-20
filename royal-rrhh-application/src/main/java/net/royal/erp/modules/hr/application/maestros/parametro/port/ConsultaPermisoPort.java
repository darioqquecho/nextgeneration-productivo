package net.royal.erp.modules.hr.application.maestros.parametro.port;

import net.royal.erp.framework.kernel.FunctionalContext;

/**
 * Puerto para consultar permisos externos antes de ejecutar operaciones sensibles.
 */
public interface ConsultaPermisoPort {
	boolean autorizado(ConsultaPermisoQuery query, FunctionalContext context);

	static ConsultaPermisoPort permitirSiempre() {
		return (query, context) -> true;
	}
}
