package net.royal.erp.modules.hr.application.maestros.parametro.dto;

/**
 * Implementa: - MOD-012 CU-007 Reporte de Parametros.
 */
public record ReporteParametrosResult(byte[] pdf, String fileName, String traceId) {
}
