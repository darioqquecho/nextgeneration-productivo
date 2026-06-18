package net.royal.erp.modules.rrhh.application.parametro.dto;

/**
 * Implementa: - MOD-012 CU-001 CRUD HR_Parametros.
 *
 * Propósito: Entrada del caso de uso ListarParametros.
 */
public record ListarParametrosQuery(String compania, String codigo, String estado) {
}
