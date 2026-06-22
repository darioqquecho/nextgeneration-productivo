package net.royal.erp.hr.application.maestros.parametro.mantenimiento.dto;

import jakarta.validation.constraints.*;

/**
 * Entrada del caso de uso ListarParametros.
 */
public record ListarParametrosQuery(@Size(max = 10) @Pattern(regexp = "^[A-Za-z0-9_-]*$") String compania,
		@Size(max = 20) @Pattern(regexp = "^[A-Za-z0-9_.-]*$") String codigo,
		@Size(max = 10) @Pattern(regexp = "^[A-Za-z]*$") String estado) {
}
