package net.royal.erp.hr.application.maestros.parametro.aprobacionmasiva.dto;

import java.util.List;

/**
 * Implementa: - MOD-012 CU-008 Aprobacion masiva de Parametros.
 */
public record AprobarMasivamenteParametrosResult(int solicitados, int aprobados, int omitidos,
		List<AprobarParametroItemResult> parametros, String traceId) {
}
