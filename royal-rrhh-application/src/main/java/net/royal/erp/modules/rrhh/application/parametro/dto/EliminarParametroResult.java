package net.royal.erp.modules.rrhh.application.parametro.dto;

/**
 * Implementa: - MOD-012 CU-001 EliminarParametro.
 */
public record EliminarParametroResult(String compania, String codigo, boolean eliminado, String traceId) {
}
