package net.royal.erp.hr.application.maestros.tiposeguro.dto;

import jakarta.validation.constraints.*;

public record ActualizarTipoSeguroCommand(@NotNull @Min(1) Integer tipoSeguro, @Size(max = 40) String descripcion,
		@Size(max = 1) @Pattern(regexp = "^[A-Za-z]?$") String estado) {
}
