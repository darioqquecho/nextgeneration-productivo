package net.royal.erp.hr.application.maestros.parametro.mantenimiento.dto;

import java.util.List;

/**
 * Implementa: - MOD-012 CU-001 ListarParametros.
 */
public record ListarParametrosResult(List<ParametroResult> parametros, String traceId) {
}
