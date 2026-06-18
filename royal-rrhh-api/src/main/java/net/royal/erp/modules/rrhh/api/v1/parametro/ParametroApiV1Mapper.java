package net.royal.erp.modules.rrhh.api.v1.parametro;

import java.util.List;

import net.royal.erp.modules.rrhh.api.v1.parametro.dto.*;
import net.royal.erp.modules.rrhh.application.parametro.dto.*;

final class ParametroApiV1Mapper {
	private ParametroApiV1Mapper() {
	}

	static CrearParametroCommand toCommand(CrearParametroRequestV1 request) {
		return new CrearParametroCommand(request.compania(), request.codigo(), request.nombre());
	}

	static ActualizarParametroCommand toCommand(String compania, String codigo, ActualizarParametroRequestV1 request) {
		return new ActualizarParametroCommand(compania, codigo, request.nombre());
	}

	static CambiarEstadoParametroCommand toCommand(String compania, String codigo,
			CambiarEstadoParametroRequestV1 request) {
		return new CambiarEstadoParametroCommand(compania, codigo, request.estado());
	}

	static ListarParametrosQuery toQuery(ListarParametrosRequestV1 request) {
		if (request == null) {
			return new ListarParametrosQuery(null, null, null);
		}
		return new ListarParametrosQuery(request.compania(), request.codigo(), request.estado());
	}

	static ReporteParametrosQuery toQuery(ReporteParametrosRequestV1 request) {
		if (request == null) {
			return new ReporteParametrosQuery(null, null, null);
		}
		return new ReporteParametrosQuery(request.compania(), request.codigo(), request.estado());
	}

	static AprobarMasivamenteParametrosCommand toCommand(AprobacionMasivaParametrosRequestV1 request) {
		List<AprobarParametroItem> parametros = request == null || request.parametros() == null ? List.of()
				: request.parametros().stream().map(p -> new AprobarParametroItem(p.compania(), p.codigo())).toList();
		return new AprobarMasivamenteParametrosCommand(parametros);
	}

	static CrearParametroResponseV1 toResponse(CrearParametroResult result) {
		return new CrearParametroResponseV1(result.compania(), result.codigo(), result.estado(), result.traceId());
	}

	static ActualizarParametroResponseV1 toResponse(ActualizarParametroResult result) {
		return new ActualizarParametroResponseV1(result.compania(), result.codigo(), result.estado(), result.traceId());
	}

	static ParametroResponseV1 toResponse(ParametroResult result) {
		return new ParametroResponseV1(result.compania(), result.codigo(), result.nombre(), result.estado(),
				result.ultimoUsuario(), result.ultimaFechaModif(), result.traceId());
	}

	static ListarParametrosResponseV1 toResponse(ListarParametrosResult result) {
		return new ListarParametrosResponseV1(result.parametros().stream().map(ParametroApiV1Mapper::toResponse).toList(),
				result.traceId());
	}

	static EliminarParametroResponseV1 toResponse(EliminarParametroResult result) {
		return new EliminarParametroResponseV1(result.compania(), result.codigo(), result.eliminado(), result.traceId());
	}

	static AprobacionMasivaParametrosResponseV1 toResponse(AprobarMasivamenteParametrosResult result) {
		return new AprobacionMasivaParametrosResponseV1(result.solicitados(), result.aprobados(), result.omitidos(),
				result.parametros().stream().map(ParametroApiV1Mapper::toResponse).toList(), result.traceId());
	}

	private static AprobacionMasivaParametroItemResponseV1 toResponse(AprobarParametroItemResult result) {
		return new AprobacionMasivaParametroItemResponseV1(result.compania(), result.codigo(), result.estadoAnterior(),
				result.estadoFinal(), result.resultado());
	}
}
