package net.royal.erp.modules.hr.api.v1.parametro.dto;

import java.time.Instant;

public record ParametroResponseV1(String compania, String codigo, String nombre, String estado, String ultimoUsuario,
		Instant ultimaFechaModif, String traceId) {
}
