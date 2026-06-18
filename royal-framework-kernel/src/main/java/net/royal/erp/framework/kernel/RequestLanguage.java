package net.royal.erp.framework.kernel;

/**
 * Implementa: - ARCH-026 Mensajes multi idioma.
 */
public final class RequestLanguage {
	private RequestLanguage() {
	}

	public static String fromHeaders(String xLanguage, String acceptLanguage) {
		if (xLanguage != null && !xLanguage.isBlank()) {
			return xLanguage.trim();
		}
		if (acceptLanguage != null && !acceptLanguage.isBlank()) {
			return acceptLanguage.split(",")[0].trim();
		}
		return "es";
	}
}
