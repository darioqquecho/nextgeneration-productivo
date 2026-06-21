package net.royal.erp.hr.application.maestros.parametro.reporte.port;

import net.royal.erp.hr.application.maestros.parametro.reporte.dto.ReporteParametrosDocumentData;

/**
 * Puerto de salida para generar el documento del reporte de Parametros.
 */
public interface ReporteParametrosDocumentGenerator {
	byte[] generate(ReporteParametrosDocumentData data);
}
