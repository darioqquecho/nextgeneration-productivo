package net.royal.erp.modules.alertas.application.dto;

import java.util.Map;

/**
 * Implementa: - ARCH-013 CQRS. - MOD-012 Migración funcional de
 * royal-alertas-api.
 *
 * Propósito: Command de identificación para obtener, anular o eliminar
 * registros legacy.
 */
public record AlertasIdCommand(String entityName, Map<String, Object> id) {
}
