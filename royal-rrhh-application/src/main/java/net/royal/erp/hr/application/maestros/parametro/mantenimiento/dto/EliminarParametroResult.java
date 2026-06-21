package net.royal.erp.hr.application.maestros.parametro.mantenimiento.dto;

/**
 * Implementa: - MOD-012 CU-001 EliminarParametro.
 */
public record EliminarParametroResult(String compania, String codigo, boolean eliminado, String traceId) {
}
