package net.royal.erp.modules.workflow.application;

/**
 * Implementa: - ASIS-028 Workflow y Aprobaciones. - ARCH-018 Reglas de
 * Dependencia.
 */
public record IniciarFlujoCommand(String businessKey, String accion, String usuario) {
}
