package net.royal.erp.modules.hr.api.v1.parametro.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record CrearParametroRequestV1(String compania, String codigo, String nombre, BigDecimal precio,
		Integer cantidad, Instant fechaProceso) {
}
