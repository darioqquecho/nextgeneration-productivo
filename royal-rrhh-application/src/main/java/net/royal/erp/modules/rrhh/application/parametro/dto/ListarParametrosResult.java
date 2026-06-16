package net.royal.erp.modules.rrhh.application.parametro.dto;

import java.util.List;

/**
 * Implementa: - MOD-012 CU-001 ListarParametros.
 */
public record ListarParametrosResult(List<ParametroResult> parametros, String traceId) {
}
