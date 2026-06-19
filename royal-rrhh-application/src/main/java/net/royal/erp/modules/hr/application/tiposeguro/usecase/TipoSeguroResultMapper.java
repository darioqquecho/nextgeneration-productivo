package net.royal.erp.modules.hr.application.tiposeguro.usecase;

import net.royal.erp.modules.hr.application.tiposeguro.dto.TipoSeguroResult;
import net.royal.erp.modules.hr.domain.tiposeguro.TipoSeguro;

final class TipoSeguroResultMapper {
	private TipoSeguroResultMapper() {
	}

	static TipoSeguroResult toResult(TipoSeguro tipoSeguro, String traceId) {
		return new TipoSeguroResult(tipoSeguro.id().value(), tipoSeguro.descripcion(), tipoSeguro.estado(),
				tipoSeguro.ultimoUsuario(), tipoSeguro.ultimaFechaModif(), traceId);
	}
}
