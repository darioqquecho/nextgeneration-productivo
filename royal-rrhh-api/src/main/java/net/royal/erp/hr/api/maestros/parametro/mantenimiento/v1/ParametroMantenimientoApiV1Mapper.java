package net.royal.erp.hr.api.maestros.parametro.mantenimiento.v1;

import net.royal.erp.hr.api.maestros.parametro.mantenimiento.v1.dto.*;
import net.royal.erp.hr.application.maestros.parametro.mantenimiento.dto.*;

final class ParametroMantenimientoApiV1Mapper {
	private ParametroMantenimientoApiV1Mapper() {
	}

	static CrearParametroCommand toCommand(CrearParametroRequestV1 request) {
		return new CrearParametroCommand(request.compania(), request.codigo(), request.nombre(), request.precio(),
				request.cantidad(), request.fechaProceso());
	}

	static ActualizarParametroCommand toCommand(String compania, String codigo, ActualizarParametroRequestV1 request) {
		return new ActualizarParametroCommand(compania, codigo, request.nombre(), request.precio(), request.cantidad(),
				request.fechaProceso());
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

	static CrearParametroResponseV1 toResponse(CrearParametroResult result) {
		return new CrearParametroResponseV1(result.compania(), result.codigo(), result.estado(), result.traceId());
	}

	static ActualizarParametroResponseV1 toResponse(ActualizarParametroResult result) {
		return new ActualizarParametroResponseV1(result.compania(), result.codigo(), result.estado(), result.traceId());
	}

	static ParametroResponseV1 toResponse(ParametroResult result) {
		return new ParametroResponseV1(result.compania(), result.codigo(), result.nombre(), result.precio(),
				result.cantidad(), result.fechaProceso(), result.estado(), result.ultimoUsuario(),
				result.ultimaFechaModif(), result.traceId());
	}

	static ListarParametrosResponseV1 toResponse(ListarParametrosResult result) {
		return new ListarParametrosResponseV1(
				result.parametros().stream().map(ParametroMantenimientoApiV1Mapper::toResponse).toList(),
				result.traceId());
	}

	static EliminarParametroResponseV1 toResponse(EliminarParametroResult result) {
		return new EliminarParametroResponseV1(result.compania(), result.codigo(), result.eliminado(), result.traceId());
	}
}
