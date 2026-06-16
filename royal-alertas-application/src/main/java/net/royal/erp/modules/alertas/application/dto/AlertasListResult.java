package net.royal.erp.modules.alertas.application.dto;

import java.util.List;
import java.util.Map;

/**
 * Implementa: - ARCH-013 CQRS Query.
 *
 * Propósito: Resultado de consultas tipo lista/paginación legacy.
 */
public record AlertasListResult(String entityName, List<Map<String, Object>> rows, String traceId) {
}
