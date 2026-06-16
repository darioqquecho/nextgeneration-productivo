package net.royal.erp.framework.audit;

import java.time.Instant;

/**
 * Implementa: - ARCH-015 Auditoría Técnica y Funcional. - ARCH-016
 * Observabilidad.
 *
 * Corrige / Evoluciona: - ASIS-031 Falta de auditoría centralizada. - ASIS-033
 * Ausencia de catálogo funcional.
 *
 * Propósito: Registro estándar de auditoría funcional.
 */
public record FunctionalAuditRecord(String module, String process, String functionality, String useCase, String version,
		String userId, String result, String entity, String entityId, String traceId, String requestId,
		Instant executedAt) {
}
