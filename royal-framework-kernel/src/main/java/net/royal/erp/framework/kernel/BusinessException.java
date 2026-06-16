package net.royal.erp.framework.kernel;

/**
 * Implementa: - ARCH-006 Seguridad e Identidad.
 *
 * Corrige / Evoluciona: - ASIS-031 Estandarización de errores.
 *
 * Propósito: Excepción funcional controlada para evitar exposición de errores
 * técnicos.
 */
public class BusinessException extends RuntimeException {
	private final String code;

	/**
	 * Crea una excepción funcional controlada.
	 *
	 * Implementa: - ARCH-006 Seguridad por diseño.
	 */
	public BusinessException(String code, String message) {
		super(message);
		this.code = code;
	}

	/**
	 * Retorna código funcional.
	 *
	 * Implementa: - ARCH-015 Auditoría funcional.
	 */
	public String code() {
		return code;
	}
}
