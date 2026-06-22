package net.royal.erp.hr.application.maestros.tiposeguro.dto;

import jakarta.validation.constraints.*;

public record CambiarEstadoTipoSeguroCommand(@NotNull @Min(1) Integer tipoSeguro,
		@NotBlank @Size(max = 1) @Pattern(regexp = "^[A-Za-z]$") String estado) {
}
