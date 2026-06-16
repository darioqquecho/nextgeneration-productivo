package net.royal.erp.modules.seguridad.application.permiso;

/**
 * Implementa: - ARCH-006 Seguridad. - ASIS-027 Seguridad y Menús.
 */
public record ValidarPermisoCommand(String userId, String permission) {
}
