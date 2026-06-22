package net.royal.erp.hr.application.maestros.parametro.mantenimiento.dto;

import jakarta.validation.constraints.*;

/**
 * Implementa: - MOD-012 CU-001 CRUD HR_Parametros.
 */
public record CambiarEstadoParametroCommand(
		@NotBlank @Size(max = 10) @Pattern(regexp = "^[A-Za-z0-9_-]+$") String compania,
		@NotBlank @Size(max = 20) @Pattern(regexp = "^[A-Za-z0-9_.-]+$") String codigo,
		@NotBlank @Size(max = 10) @Pattern(regexp = "^[A-Za-z]+$") String estado) {
}
