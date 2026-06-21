package net.royal.erp.hr.api.maestros.tiposeguro.v1.dto;

import jakarta.validation.constraints.*;

public record CrearTipoSeguroRequestV1(@NotNull @Min(1) Integer tipoSeguro, @Size(max = 40) String descripcion,
		@NotBlank @Size(max = 1) @Pattern(regexp = "^[A-Za-z]$") String estado) {
}
