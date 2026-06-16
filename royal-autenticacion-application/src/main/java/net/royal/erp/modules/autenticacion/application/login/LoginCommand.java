package net.royal.erp.modules.autenticacion.application.login;

/**
 * Implementa: - ARCH-006 Seguridad e Identidad.
 *
 * Propósito: Entrada del caso de uso Login.
 */
public record LoginCommand(String username, String password) {
}
