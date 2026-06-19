package net.royal.erp.modules.hr.api.v1.tiposeguro.dto;

import java.time.Instant;

public record TipoSeguroResponseV1(Integer tipoSeguro, String descripcion, String estado, String ultimoUsuario,
		Instant ultimaFechaModif, String traceId) {
}
