package net.royal.erp.hr.api.maestros.tiposeguro.v1.dto;

import jakarta.validation.constraints.*;

public record ListarTiposSeguroRequestV1(@Min(1) Integer tipoSeguro,
		@Size(max = 1) @Pattern(regexp = "^[A-Za-z]*$") String estado) {
}
