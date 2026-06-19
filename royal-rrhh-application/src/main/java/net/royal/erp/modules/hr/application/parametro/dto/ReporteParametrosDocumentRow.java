package net.royal.erp.modules.hr.application.parametro.dto;

/**
 * Fila plana del documento del reporte de Parametros.
 */
public record ReporteParametrosDocumentRow(String compania, String codigo, String nombre, String estado,
		String ultimoUsuario, String ultimaFechaModif) {
}
