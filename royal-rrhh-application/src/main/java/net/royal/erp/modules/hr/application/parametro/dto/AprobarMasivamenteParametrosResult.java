package net.royal.erp.modules.hr.application.parametro.dto;

import java.util.List;

/**
 * Implementa: - MOD-012 CU-008 Aprobacion masiva de Parametros.
 */
public record AprobarMasivamenteParametrosResult(int solicitados, int aprobados, int omitidos,
		List<AprobarParametroItemResult> parametros, String traceId) {
}
