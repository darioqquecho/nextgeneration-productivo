package net.royal.erp.hr.api.maestros.tiposeguro.v1.dto;

import java.time.Instant;

public record TipoSeguroResponseV1(Integer tipoSeguro, String descripcion, String estado, String ultimoUsuario,
		Instant ultimaFechaModif, String traceId) {
}
