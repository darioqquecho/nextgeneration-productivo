package net.royal.erp.modules.alertas.application.dto;

import java.util.Map;

/**
 * Implementa: - ARCH-013 CQRS. - MOD-012 Migración funcional de
 * royal-alertas-api.
 *
 * Propósito: Command genérico para operaciones CRUD migradas de Alertas.
 */
public record AlertasCrudCommand(String entityName, Map<String, Object> values) {
}
