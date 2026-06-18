package net.royal.erp.modules.rrhh.application.common;

import java.text.Normalizer;
import java.util.Locale;

import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.licensing.LicenseChecker;
import net.royal.erp.framework.security.PermissionChecker;

/**
 * Implementa: - ARCH-006 Seguridad. - ARCH-025 Licenciamiento.
 *
 * Proposito: Valida licencia y permiso a nivel de caso de uso RRHH.
 */
public class UseCaseGuards {
	private final PermissionChecker permissionChecker;
	private final LicenseChecker licenseChecker;

	public UseCaseGuards(PermissionChecker permissionChecker, LicenseChecker licenseChecker) {
		this.permissionChecker = permissionChecker;
		this.licenseChecker = licenseChecker;
	}

	/**
	 * Valida modulo HR y permiso a nivel de caso de uso.
	 *
	 * Implementa: - ARCH-006 Seguridad. - ARCH-025 Licenciamiento.
	 */
	public void check(FunctionalContext context) {
		licenseChecker.checkModuleEnabled(context, "HR");
		permissionChecker.check(context, permissionFor(context));
	}

	private String permissionFor(FunctionalContext context) {
		return normalize(context.module()) + "_" + normalize(context.useCase());
	}

	private String normalize(String value) {
		String normalized = Normalizer.normalize(value == null ? "" : value, Normalizer.Form.NFD)
				.replaceAll("\\p{M}", "");
		return normalized.trim().replaceAll("[^A-Za-z0-9]+", "_").replaceAll("^_|_$", "")
				.toUpperCase(Locale.ROOT);
	}
}
