package net.royal.erp.modules.autenticacion.application.permiso;

import java.time.Instant;

/**
 * Resultado detallado de una consulta de permiso.
 */
public record ObtenerPermisoResult(String usuario, String concepto, String funcionalidad, String permiso,
		String autorizado, String mensaje, Instant fechaConsulta) {
}
