package net.royal.erp.modules.rrhh.application.parametro.dto;

/**
 * Implementa: - MOD-012 CU-008 Aprobacion masiva de Parametros.
 */
public record AprobarParametroItemResult(String compania, String codigo, String estadoAnterior, String estadoFinal,
		String resultado) {
}
