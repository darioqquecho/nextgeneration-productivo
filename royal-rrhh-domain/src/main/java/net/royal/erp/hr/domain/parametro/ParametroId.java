package net.royal.erp.hr.domain.parametro;

/**
 * Implementa: - ARCH-003 DDD. - MOD-012 CU-001 CRUD HR_Parametros.
 */
public record ParametroId(String compania, String codigo) {
	public ParametroId {
		compania = required(compania, "Compania requerida", 10);
		codigo = required(codigo, "Codigo requerido", 10);
	}

	private static String required(String value, String message, int maxLength) {
		if (value == null || value.isBlank()) {
			throw new IllegalArgumentException(message);
		}
		String trimmed = value.trim();
		if (trimmed.length() > maxLength) {
			throw new IllegalArgumentException(message + ": longitud maxima " + maxLength);
		}
		return trimmed;
	}

	public String value() {
		return compania + "|" + codigo;
	}
}
