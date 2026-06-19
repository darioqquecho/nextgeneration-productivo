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
public record FunctionalAuditRecord(String tenantId, String clientId, String module, String process, String useCase,
		String functionality, String version, String userId, String result, String entity, String entityId,
		String traceId, String requestId, Instant executedAt, String language, String message) {
	public FunctionalAuditRecord(String module, String process, String useCase, String functionality, String version,
			String userId, String result, String entity, String entityId, String traceId, String requestId,
			Instant executedAt) {
		this(null, null, module, process, useCase, functionality, version, userId, result, entity, entityId, traceId,
				requestId, executedAt, null, null);
	}
}
