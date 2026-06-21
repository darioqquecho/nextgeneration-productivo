package net.royal.erp.hr.api.maestros.parametro.reporte.v1;

import net.royal.erp.hr.api.maestros.parametro.reporte.v1.dto.ReporteParametrosRequestV1;
import net.royal.erp.hr.application.maestros.parametro.reporte.dto.ReporteParametrosQuery;

final class ReporteParametroApiV1Mapper {
	private ReporteParametroApiV1Mapper() {
	}

	static ReporteParametrosQuery toQuery(ReporteParametrosRequestV1 request) {
		if (request == null) {
			return new ReporteParametrosQuery(null, null, null);
		}
		return new ReporteParametrosQuery(request.compania(), request.codigo(), request.estado());
	}
}
