package net.royal.erp.modules.hr.api.v1.maestros.tiposeguro.dto;

import jakarta.validation.constraints.*;

public record ActualizarTipoSeguroRequestV1(@Size(max = 40) String descripcion,
		@NotBlank @Size(max = 1) @Pattern(regexp = "^[A-Za-z]$") String estado) {
}
