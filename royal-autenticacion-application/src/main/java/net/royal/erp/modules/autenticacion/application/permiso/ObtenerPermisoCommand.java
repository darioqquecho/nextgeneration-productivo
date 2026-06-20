package net.royal.erp.modules.autenticacion.application.permiso;

/**
 * Solicitud para consultar si un usuario tiene un permiso.
 */
public record ObtenerPermisoCommand(String usuario, String concepto, String funcionalidad, String permiso) {
}
