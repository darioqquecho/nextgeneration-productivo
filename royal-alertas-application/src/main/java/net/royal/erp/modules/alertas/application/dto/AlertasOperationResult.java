package net.royal.erp.modules.alertas.application.dto;

import java.util.Map;

/**
 * Implementa: - ARCH-013 CQRS. - ARCH-015 Auditoría Técnica y Funcional.
 *
 * Propósito: Resultado estándar para operaciones funcionales del módulo
 * Alertas.
 */
public record AlertasOperationResult(String entityName, String operation, Map<String, Object> data, String traceId) {
}
