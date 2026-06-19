package net.royal.erp.modules.hr.application.parametro.dto;

import java.util.List;

/**
 * Implementa: - MOD-012 CU-001 ListarParametros.
 */
public record ListarParametrosResult(List<ParametroResult> parametros, String traceId) {
}
