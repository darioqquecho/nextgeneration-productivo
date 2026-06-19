package net.royal.erp.framework.web;

/**
 * Respuesta estandar de error para APIs REST.
 */
public record ApiErrorResponse(String code, String message) {
}
