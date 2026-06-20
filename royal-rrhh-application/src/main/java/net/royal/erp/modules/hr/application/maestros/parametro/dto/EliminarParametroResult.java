package net.royal.erp.modules.hr.application.maestros.parametro.dto;

/**
 * Implementa: - MOD-012 CU-001 EliminarParametro.
 */
public record EliminarParametroResult(String compania, String codigo, boolean eliminado, String traceId) {
}
