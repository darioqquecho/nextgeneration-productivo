package net.royal.erp.modules.hr.application.maestros.parametro.port;

/**
 * Solicitud de validacion de permiso.
 */
public record ConsultaPermisoQuery(String usuario, String concepto, String funcionalidad, String permiso) {
}
