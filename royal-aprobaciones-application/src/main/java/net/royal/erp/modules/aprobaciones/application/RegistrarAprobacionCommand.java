package net.royal.erp.modules.aprobaciones.application;

/**
 * Implementa: - ASIS-028 Workflow y Aprobaciones. - ARCH-018 Reglas de
 * Dependencia.
 */
public record RegistrarAprobacionCommand(String businessKey, String accion, String usuario) {
}
