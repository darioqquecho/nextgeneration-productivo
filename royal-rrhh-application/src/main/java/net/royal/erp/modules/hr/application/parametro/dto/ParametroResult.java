package net.royal.erp.modules.hr.application.parametro.dto;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Implementa: - MOD-012 CU-001 CRUD HR_Parametros.
 */
public record ParametroResult(String compania, String codigo, String nombre, BigDecimal precio, Integer cantidad,
		Instant fechaProceso, String estado, String ultimoUsuario, Instant ultimaFechaModif, String traceId) {
}
