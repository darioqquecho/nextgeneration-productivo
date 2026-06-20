package net.royal.erp.framework.web;

import java.util.List;

/**
 * Respuesta estandar de error para APIs REST.
 */
public record ApiErrorResponse(String code, String message, List<ApiFieldError> fields) {
	public ApiErrorResponse(String code, String message) {
		this(code, message, List.of());
	}

	public record ApiFieldError(String field, String message) {
	}
}
