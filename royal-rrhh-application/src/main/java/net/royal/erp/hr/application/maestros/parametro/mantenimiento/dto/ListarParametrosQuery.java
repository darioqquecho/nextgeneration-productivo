package net.royal.erp.hr.application.maestros.parametro.mantenimiento.dto;

/**
 * Implementa: - MOD-012 CU-001 CRUD HR_Parametros.
 *
 * Propósito: Entrada del caso de uso ListarParametros.
 */
public record ListarParametrosQuery(String compania, String codigo, String estado) {
}
