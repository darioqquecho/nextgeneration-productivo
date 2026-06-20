package net.royal.erp.modules.hr.api.v1.maestros.parametro.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record ParametroResponseV1(String compania, String codigo, String nombre, BigDecimal precio, Integer cantidad,
		Instant fechaProceso, String estado, String ultimoUsuario, Instant ultimaFechaModif, String traceId) {
}
