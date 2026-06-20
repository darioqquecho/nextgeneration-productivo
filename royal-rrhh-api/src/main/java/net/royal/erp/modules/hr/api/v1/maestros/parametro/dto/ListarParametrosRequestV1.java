package net.royal.erp.modules.hr.api.v1.maestros.parametro.dto;

import jakarta.validation.constraints.*;

public record ListarParametrosRequestV1(@Size(max = 10) @Pattern(regexp = "^[A-Za-z0-9_-]*$") String compania,
		@Size(max = 20) @Pattern(regexp = "^[A-Za-z0-9_.-]*$") String codigo,
		@Size(max = 10) @Pattern(regexp = "^[A-Za-z]*$") String estado) {
}
