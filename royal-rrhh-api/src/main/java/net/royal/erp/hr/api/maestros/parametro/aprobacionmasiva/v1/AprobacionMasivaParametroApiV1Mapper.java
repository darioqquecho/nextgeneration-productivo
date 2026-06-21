package net.royal.erp.hr.api.maestros.parametro.aprobacionmasiva.v1;

import java.util.List;

import net.royal.erp.hr.api.maestros.parametro.aprobacionmasiva.v1.dto.*;
import net.royal.erp.hr.application.maestros.parametro.aprobacionmasiva.dto.*;

final class AprobacionMasivaParametroApiV1Mapper {
	private AprobacionMasivaParametroApiV1Mapper() {
	}

	static AprobarMasivamenteParametrosCommand toCommand(AprobacionMasivaParametrosRequestV1 request) {
		List<AprobarParametroItem> parametros = request == null || request.parametros() == null ? List.of()
				: request.parametros().stream().map(p -> new AprobarParametroItem(p.compania(), p.codigo())).toList();
		return new AprobarMasivamenteParametrosCommand(parametros);
	}

	static AprobacionMasivaParametrosResponseV1 toResponse(AprobarMasivamenteParametrosResult result) {
		return new AprobacionMasivaParametrosResponseV1(result.solicitados(), result.aprobados(), result.omitidos(),
				result.parametros().stream().map(AprobacionMasivaParametroApiV1Mapper::toResponse).toList(),
				result.traceId());
	}

	private static AprobacionMasivaParametroItemResponseV1 toResponse(AprobarParametroItemResult result) {
		return new AprobacionMasivaParametroItemResponseV1(result.compania(), result.codigo(), result.estadoAnterior(),
				result.estadoFinal(), result.resultado());
	}
}
