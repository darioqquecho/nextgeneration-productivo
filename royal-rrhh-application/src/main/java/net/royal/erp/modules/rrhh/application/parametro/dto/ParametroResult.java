package net.royal.erp.modules.rrhh.application.parametro.dto;

import java.time.Instant;

/**
 * Implementa: - MOD-012 CU-001 CRUD HR_Parametros.
 */
public record ParametroResult(String compania, String codigo, String nombre, String estado, String ultimoUsuario,
		Instant ultimaFechaModif, String traceId) {
}
