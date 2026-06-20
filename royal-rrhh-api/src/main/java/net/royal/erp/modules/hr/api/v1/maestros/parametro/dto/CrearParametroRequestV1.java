package net.royal.erp.modules.hr.api.v1.maestros.parametro.dto;

import java.math.BigDecimal;
import java.time.Instant;

import jakarta.validation.constraints.*;

public record CrearParametroRequestV1(
		@NotBlank @Size(max = 10) @Pattern(regexp = "^[A-Za-z0-9_-]+$") String compania,
		@NotBlank @Size(max = 20) @Pattern(regexp = "^[A-Za-z0-9_.-]+$") String codigo,
		@NotBlank @Size(max = 100) String nombre,
		@DecimalMin(value = "0.00") @Digits(integer = 16, fraction = 2) BigDecimal precio,
		@Min(0) @Max(2147483647) Integer cantidad, Instant fechaProceso) {
}
