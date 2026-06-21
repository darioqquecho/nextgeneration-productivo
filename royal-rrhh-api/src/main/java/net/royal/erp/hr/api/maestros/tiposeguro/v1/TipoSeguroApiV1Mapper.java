package net.royal.erp.hr.api.maestros.tiposeguro.v1;

import net.royal.erp.hr.api.maestros.tiposeguro.v1.dto.*;
import net.royal.erp.hr.application.maestros.tiposeguro.dto.*;

final class TipoSeguroApiV1Mapper {
	private TipoSeguroApiV1Mapper() {
	}

	static CrearTipoSeguroCommand toCommand(CrearTipoSeguroRequestV1 request) {
		return new CrearTipoSeguroCommand(request.tipoSeguro(), request.descripcion(), request.estado());
	}

	static ActualizarTipoSeguroCommand toCommand(Integer tipoSeguro, ActualizarTipoSeguroRequestV1 request) {
		return new ActualizarTipoSeguroCommand(tipoSeguro, request.descripcion(), request.estado());
	}

	static CambiarEstadoTipoSeguroCommand toCommand(Integer tipoSeguro, CambiarEstadoTipoSeguroRequestV1 request) {
		return new CambiarEstadoTipoSeguroCommand(tipoSeguro, request.estado());
	}

	static ListarTiposSeguroQuery toQuery(ListarTiposSeguroRequestV1 request) {
		if (request == null) {
			return new ListarTiposSeguroQuery(null, null);
		}
		return new ListarTiposSeguroQuery(request.tipoSeguro(), request.estado());
	}

	static CrearTipoSeguroResponseV1 toResponse(CrearTipoSeguroResult result) {
		return new CrearTipoSeguroResponseV1(result.tipoSeguro(), result.estado(), result.traceId());
	}

	static ActualizarTipoSeguroResponseV1 toResponse(ActualizarTipoSeguroResult result) {
		return new ActualizarTipoSeguroResponseV1(result.tipoSeguro(), result.estado(), result.traceId());
	}

	static TipoSeguroResponseV1 toResponse(TipoSeguroResult result) {
		return new TipoSeguroResponseV1(result.tipoSeguro(), result.descripcion(), result.estado(),
				result.ultimoUsuario(), result.ultimaFechaModif(), result.traceId());
	}

	static ListarTiposSeguroResponseV1 toResponse(ListarTiposSeguroResult result) {
		return new ListarTiposSeguroResponseV1(
				result.tiposSeguro().stream().map(TipoSeguroApiV1Mapper::toResponse).toList(), result.traceId());
	}

	static EliminarTipoSeguroResponseV1 toResponse(EliminarTipoSeguroResult result) {
		return new EliminarTipoSeguroResponseV1(result.tipoSeguro(), result.eliminado(), result.traceId());
	}
}
