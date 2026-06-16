package net.royal.erp.modules.autenticacion.application.login;

/**
 * Implementa: - ARCH-006 Seguridad e Identidad.
 *
 * Propósito: Resultado del caso de uso Login.
 */
public record LoginResult(String userId, String token, String tokenType) {
}
