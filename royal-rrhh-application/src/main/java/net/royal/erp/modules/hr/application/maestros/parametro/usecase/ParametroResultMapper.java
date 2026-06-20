package net.royal.erp.modules.hr.application.maestros.parametro.usecase;

import net.royal.erp.modules.hr.application.maestros.parametro.dto.ParametroResult;
import net.royal.erp.modules.hr.domain.parametro.Parametro;

final class ParametroResultMapper {
	private ParametroResultMapper() {
	}

	static ParametroResult toResult(Parametro parametro, String traceId) {
		return new ParametroResult(parametro.id().compania(), parametro.id().codigo(), parametro.nombre(),
				parametro.precio(), parametro.cantidad(), parametro.fechaProceso(),
				parametro.estado() == null ? null : parametro.estado().name(), parametro.ultimoUsuario(),
				parametro.ultimaFechaModif(), traceId);
	}
}
