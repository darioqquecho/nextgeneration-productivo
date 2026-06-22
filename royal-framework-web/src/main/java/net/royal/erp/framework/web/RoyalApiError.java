package net.royal.erp.framework.web;

/**
 * Error estandar dentro de una respuesta API Royal.
 */
public record RoyalApiError(String code, String field, String message) {
	public RoyalApiError(String code, String message) {
		this(code, null, message);
	}
}
