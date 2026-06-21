package net.royal.erp.hr.application.maestros.tiposeguro.dto;

import java.time.Instant;

public record TipoSeguroResult(Integer tipoSeguro, String descripcion, String estado, String ultimoUsuario,
		Instant ultimaFechaModif, String traceId) {
}
