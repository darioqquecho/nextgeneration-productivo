package net.royal.erp.modules.hr.api.v1.maestros.tiposeguro.dto;

import jakarta.validation.constraints.*;

public record ListarTiposSeguroRequestV1(@Min(1) Integer tipoSeguro,
		@Size(max = 1) @Pattern(regexp = "^[A-Za-z]*$") String estado) {
}
