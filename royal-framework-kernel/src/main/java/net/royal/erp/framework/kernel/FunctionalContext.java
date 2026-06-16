package net.royal.erp.framework.kernel;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

/**
 * Implementa: - ARCH-012 Estructura Lógica. - ARCH-015 Auditoría Técnica y
 * Funcional. - ARCH-016 Observabilidad.
 *
 * Corrige / Evoluciona: - ASIS-033 Ausencia de catálogo funcional
 * estandarizado.
 *
 * Propósito: Contexto funcional estándar utilizado por casos de uso, auditoría,
 * seguridad, licenciamiento y observabilidad.
 */
public record FunctionalContext(String tenantId, String clientId, String userId, String module, String process,
		String functionality, String useCase, String requestedVersion, String traceId, String requestId,
		Instant executedAt) {
	/**
	 * Normaliza TraceId, RequestId y fecha de ejecución.
	 *
	 * Implementa: - ARCH-016 Observabilidad.
	 */
	public FunctionalContext {
		Objects.requireNonNull(clientId, "clientId is required");
		Objects.requireNonNull(userId, "userId is required");
		Objects.requireNonNull(module, "module is required");
		Objects.requireNonNull(useCase, "useCase is required");
		traceId = blank(traceId) ? UUID.randomUUID().toString() : traceId;
		requestId = blank(requestId) ? UUID.randomUUID().toString() : requestId;
		executedAt = executedAt == null ? Instant.now() : executedAt;
	}

	private static boolean blank(String value) {
		return value == null || value.isBlank();
	}
}
