package net.royal.erp.hr.application.maestros.parametro.aprobacionmasiva.dto;

import jakarta.validation.constraints.*;

/**
 * Implementa: - MOD-012 CU-008 Aprobacion masiva de Parametros.
 */
public record AprobarParametroItem(@NotBlank @Size(max = 10) @Pattern(regexp = "^[A-Za-z0-9_-]+$") String compania,
		@NotBlank @Size(max = 20) @Pattern(regexp = "^[A-Za-z0-9_.-]+$") String codigo) {
}
