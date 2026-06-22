package net.royal.erp.hr.application.maestros.parametro.reporte.dto;

import jakarta.validation.constraints.*;

/**
 * Implementa: - MOD-012 CU-007 Reporte de Parametros.
 */
public record ReporteParametrosQuery(@Size(max = 10) @Pattern(regexp = "^[A-Za-z0-9_-]*$") String compania,
		@Size(max = 20) @Pattern(regexp = "^[A-Za-z0-9_.-]*$") String codigo,
		@Size(max = 10) @Pattern(regexp = "^[A-Za-z]*$") String estado) {
}
