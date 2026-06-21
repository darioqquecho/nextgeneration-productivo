package net.royal.erp.hr.api.maestros.parametro.aprobacionmasiva.v1.dto;

import jakarta.validation.constraints.*;

public record AprobacionMasivaParametroItemV1(
		@NotBlank @Size(max = 10) @Pattern(regexp = "^[A-Za-z0-9_-]+$") String compania,
		@NotBlank @Size(max = 20) @Pattern(regexp = "^[A-Za-z0-9_.-]+$") String codigo) {
}
