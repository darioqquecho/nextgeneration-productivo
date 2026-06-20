package net.royal.erp.modules.hr.application.maestros.parametro.dto;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Implementa: - MOD-012 CU-001 CRUD HR_Parametros.
 */
public record ActualizarParametroCommand(String compania, String codigo, String nombre, BigDecimal precio,
		Integer cantidad, Instant fechaProceso) {
	public ActualizarParametroCommand(String compania, String codigo, String nombre) {
		this(compania, codigo, nombre, null, null, null);
	}

	public ActualizarParametroCommand(String compania, String codigo, String nombre, BigDecimal precio,
			Integer cantidad) {
		this(compania, codigo, nombre, precio, cantidad, null);
	}
}
