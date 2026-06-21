package net.royal.erp.hr.application.maestros.parametro.aprobacionmasiva.dto;

/**
 * Implementa: - MOD-012 CU-008 Aprobacion masiva de Parametros.
 */
public record AprobarParametroItemResult(String compania, String codigo, String estadoAnterior, String estadoFinal,
		String resultado) {
}
