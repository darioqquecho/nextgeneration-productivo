package net.royal.erp.hr.api.maestros.parametro.mantenimiento.v1.dto;

import java.math.BigDecimal;
import java.time.Instant;

import jakarta.validation.constraints.*;

public record ActualizarParametroRequestV1(@NotBlank @Size(max = 100) String nombre,
		@DecimalMin(value = "0.00") @Digits(integer = 16, fraction = 2) BigDecimal precio,
		@Min(0) @Max(2147483647) Integer cantidad, Instant fechaProceso) {
}
