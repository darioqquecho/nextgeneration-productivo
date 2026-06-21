package net.royal.erp.hr.application.maestros.parametro.reporte.dto;

/**
 * Implementa: - MOD-012 CU-007 Reporte de Parametros.
 */
public record ReporteParametrosResult(byte[] pdf, String fileName, String traceId) {
}
