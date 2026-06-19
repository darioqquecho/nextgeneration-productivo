package net.royal.erp.modules.hr.infrastructure.parametro;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import net.royal.erp.framework.kernel.BusinessException;
import net.royal.erp.modules.hr.application.parametro.dto.ReporteParametrosDocumentData;
import net.royal.erp.modules.hr.application.parametro.port.ReporteParametrosDocumentGenerator;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Adapter JasperReports para generar el PDF del reporte de Parametros.
 */
public class JasperReporteParametrosDocumentGenerator implements ReporteParametrosDocumentGenerator {
	private static final String REPORT_PATH = "recursos/hr/maestros/reporteparametros/parametros.v1.jrxml";

	@Override
	public byte[] generate(ReporteParametrosDocumentData data) {
		try (InputStream template = getClass().getClassLoader().getResourceAsStream(REPORT_PATH)) {
			if (template == null) {
				throw new BusinessException("HR-PAR-REP-001");
			}
			JasperReport report = JasperCompileManager.compileReport(template);
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("TITULO", data.titulo());
			parameters.put("USUARIO", data.usuario());
			parameters.put("TRACE_ID", data.traceId());
			parameters.put("FILTROS", data.filtros());
			JasperPrint print = JasperFillManager.fillReport(report, parameters,
					new JRBeanCollectionDataSource(data.rows()));
			return JasperExportManager.exportReportToPdf(print);
		} catch (JRException e) {
			throw new BusinessException("HR-PAR-REP-002");
		} catch (java.io.IOException e) {
			throw new BusinessException("HR-PAR-REP-003");
		}
	}
}
