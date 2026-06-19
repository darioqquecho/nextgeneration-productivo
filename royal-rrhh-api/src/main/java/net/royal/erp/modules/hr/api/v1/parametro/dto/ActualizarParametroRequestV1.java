package net.royal.erp.modules.hr.api.v1.parametro.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record ActualizarParametroRequestV1(String nombre, BigDecimal precio, Integer cantidad, Instant fechaProceso) {
}
