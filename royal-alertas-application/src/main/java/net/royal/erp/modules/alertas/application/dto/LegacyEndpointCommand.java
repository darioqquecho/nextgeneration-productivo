package net.royal.erp.modules.alertas.application.dto;

import java.util.Map;

/**
 * Implementa: - MOD-012 Migración funcional de endpoints legacy.
 *
 * Propósito: Command que representa una llamada legacy
 * /spring/alertas/{resource}/{action}.
 */
public record LegacyEndpointCommand(String httpMethod, String resource, String action, String methodName,
		Map<String, Object> payload) {
}
