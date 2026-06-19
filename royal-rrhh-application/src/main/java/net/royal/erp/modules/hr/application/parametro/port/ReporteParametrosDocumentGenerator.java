package net.royal.erp.modules.hr.application.parametro.port;

import net.royal.erp.modules.hr.application.parametro.dto.ReporteParametrosDocumentData;

/**
 * Puerto de salida para generar el documento del reporte de Parametros.
 */
public interface ReporteParametrosDocumentGenerator {
	byte[] generate(ReporteParametrosDocumentData data);
}
