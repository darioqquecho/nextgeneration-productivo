package net.royal.erp.framework.licensing;

import net.royal.erp.framework.kernel.FunctionalContext;

/**
 * Implementa: - ARCH-025 Control de Funcionalidades y Licenciamiento.
 *
 * Corrige / Evoluciona: - ASIS-029 Licenciamiento actual.
 *
 * Propósito: Contrato para validar licenciamiento por cliente.
 */
public interface LicenseChecker {
	/**
	 * Valida módulo habilitado.
	 *
	 * Implementa: - ARCH-025 Licenciamiento.
	 */
	void checkModuleEnabled(FunctionalContext context, String moduleCode);
}
