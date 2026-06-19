package net.royal.erp.modules.hr.application.parametro.dto;

import java.math.BigDecimal;

/**
 * Fila plana del documento del reporte de Parametros.
 */
public record ReporteParametrosDocumentRow(String compania, String codigo, String nombre, BigDecimal precio,
		Integer cantidad, String fechaProceso, String estado, String ultimoUsuario, String ultimaFechaModif) {
}
