package net.royal.erp.framework.security;

import net.royal.erp.framework.kernel.FunctionalContext;

/**
 * Implementa: - ARCH-006 Seguridad e Identidad. - ARCH-025 Control de
 * Funcionalidades.
 *
 * Corrige / Evoluciona: - ASIS-027 Seguridad centrada en menú.
 *
 * Propósito: Contrato técnico de validación de permisos por caso de uso.
 */
public interface PermissionChecker {
	/**
	 * Verifica si el usuario tiene permiso.
	 *
	 * Implementa: - ARCH-006 Seguridad.
	 */
	void check(FunctionalContext context, String permission);
}
