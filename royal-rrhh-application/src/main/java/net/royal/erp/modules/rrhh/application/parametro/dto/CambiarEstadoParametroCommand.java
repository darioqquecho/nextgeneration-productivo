package net.royal.erp.modules.rrhh.application.parametro.dto;

/**
 * Implementa: - MOD-012 CU-001 CRUD HR_Parametros.
 */
public record CambiarEstadoParametroCommand(String compania, String codigo, String estado) {
}
