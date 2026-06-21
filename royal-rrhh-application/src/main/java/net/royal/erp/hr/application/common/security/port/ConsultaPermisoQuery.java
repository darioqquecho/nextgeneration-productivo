package net.royal.erp.hr.application.common.security.port;

/**
 * Solicitud de validacion de permiso.
 */
public record ConsultaPermisoQuery(String usuario, String concepto, String funcionalidad, String permiso) {
}
