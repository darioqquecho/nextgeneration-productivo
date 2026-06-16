package net.royal.erp.modules.alertas.application.common;

import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.licensing.LicenseChecker;
import net.royal.erp.framework.security.PermissionChecker;

/**
 * Implementa: - ARCH-006 Seguridad. - ARCH-025 Licenciamiento.
 *
 * Propósito: Valida licencia y permiso antes de ejecutar casos de uso del
 * módulo Alertas.
 */
public class AlertasUseCaseGuards {
	private final PermissionChecker permissionChecker;
	private final LicenseChecker licenseChecker;

	public AlertasUseCaseGuards(PermissionChecker permissionChecker, LicenseChecker licenseChecker) {
		this.permissionChecker = permissionChecker;
		this.licenseChecker = licenseChecker;
	}

	/**
	 * Valida módulo ALERTAS y permiso requerido.
	 *
	 * Implementa: - ARCH-006 Seguridad. - ARCH-025 Licenciamiento.
	 */
	public void check(FunctionalContext context, String permission) {
		licenseChecker.checkModuleEnabled(context, "ALERTAS");
		permissionChecker.check(context, permission);
	}
}
