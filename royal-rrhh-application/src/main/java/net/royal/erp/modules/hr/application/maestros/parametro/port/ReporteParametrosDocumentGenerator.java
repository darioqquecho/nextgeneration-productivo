package net.royal.erp.modules.hr.application.maestros.parametro.port;

import net.royal.erp.modules.hr.application.maestros.parametro.dto.ReporteParametrosDocumentData;

/**
 * Puerto de salida para generar el documento del reporte de Parametros.
 */
public interface ReporteParametrosDocumentGenerator {
	byte[] generate(ReporteParametrosDocumentData data);
}
