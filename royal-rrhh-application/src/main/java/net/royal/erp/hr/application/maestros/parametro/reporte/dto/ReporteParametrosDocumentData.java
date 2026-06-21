package net.royal.erp.hr.application.maestros.parametro.reporte.dto;

import java.util.List;

/**
 * Datos necesarios para renderizar el documento del reporte de Parametros.
 */
public record ReporteParametrosDocumentData(String titulo, String usuario, String traceId, String filtros,
		List<ReporteParametrosDocumentRow> rows) {
	public ReporteParametrosDocumentData {
		rows = rows == null ? List.of() : List.copyOf(rows);
	}
}
