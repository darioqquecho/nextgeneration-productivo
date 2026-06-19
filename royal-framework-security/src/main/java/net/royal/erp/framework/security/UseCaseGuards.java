package net.royal.erp.framework.security;

import java.text.Normalizer;
import java.util.Locale;

import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.licensing.LicenseChecker;

/**
 * Valida licencia y permiso a nivel de caso de uso.
 */
public class UseCaseGuards {
	private final PermissionChecker permissionChecker;
	private final LicenseChecker licenseChecker;

	public UseCaseGuards(PermissionChecker permissionChecker, LicenseChecker licenseChecker) {
		this.permissionChecker = permissionChecker;
		this.licenseChecker = licenseChecker;
	}

	public void check(FunctionalContext context) {
		licenseChecker.checkModuleEnabled(context, context.module());
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
