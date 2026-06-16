package net.royal.erp.modules.auditoria.domain;

import java.time.Instant;

/**
 * Implementa: - ARCH-015 Auditoría Técnica y Funcional.
 *
 * Propósito: Entidad funcional de auditoría.
 */
public record AuditoriaFuncional(String traceId, String userId, String module, String useCase, String result,
		Instant executedAt) {
}
