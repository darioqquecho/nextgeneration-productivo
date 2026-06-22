package net.royal.erp.hr.application.maestros.parametro.mantenimiento.dto;

import java.math.BigDecimal;
import java.time.Instant;

import jakarta.validation.constraints.*;

/**
 * Implementa: - MOD-012 CU-001 CRUD HR_Parametros.
 */
public record CrearParametroCommand(@NotBlank @Size(max = 10) @Pattern(regexp = "^[A-Za-z0-9_-]+$") String compania,
		@NotBlank @Size(max = 20) @Pattern(regexp = "^[A-Za-z0-9_.-]+$") String codigo,
		@NotBlank @Size(max = 100) String nombre, BigDecimal precio, Integer cantidad, Instant fechaProceso) {
	public CrearParametroCommand(String compania, String codigo, String nombre) {
		this(compania, codigo, nombre, null, null, null);
	}

	public CrearParametroCommand(String compania, String codigo, String nombre, BigDecimal precio, Integer cantidad) {
		this(compania, codigo, nombre, precio, cantidad, null);
	}
}
