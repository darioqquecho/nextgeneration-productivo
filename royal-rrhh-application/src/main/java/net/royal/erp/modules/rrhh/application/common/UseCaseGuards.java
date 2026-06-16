package net.royal.erp.modules.rrhh.application.common;

import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.licensing.LicenseChecker;
import net.royal.erp.framework.security.PermissionChecker;

/**
 * Implementa: - ARCH-006 Seguridad. - ARCH-025 Licenciamiento.
 *
 * Propósito: Valida licencia y permiso antes de ejecutar casos de uso RRHH.
 */
public class UseCaseGuards {
	private final PermissionChecker permissionChecker;
	private final LicenseChecker licenseChecker;

	public UseCaseGuards(PermissionChecker permissionChecker, LicenseChecker licenseChecker) {
		this.permissionChecker = permissionChecker;
		this.licenseChecker = licenseChecker;
	}

	/**
	 * Valida módulo RRHH y permiso requerido.
	 *
	 * Implementa: - ARCH-006 Seguridad. - ARCH-025 Licenciamiento.
	 */
	public void check(FunctionalContext context, String permission) {
		licenseChecker.checkModuleEnabled(context, "RRHH");
		permissionChecker.check(context, permission);
	}
}
