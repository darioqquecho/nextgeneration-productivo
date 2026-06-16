package net.royal.erp.modules.auditoria.domain;

import java.util.List;

/**
 * Implementa: - ARCH-018 Reglas de Dependencia.
 */
public interface AuditoriaRepository {
	/**
	 * Guarda auditoría.
	 *
	 * Implementa: - ARCH-015 Auditoría.
	 */
	void save(AuditoriaFuncional auditoria);

	/**
	 * Busca auditorías por TraceId.
	 *
	 * Implementa: - MOD-012 CU-005 ConsultarAuditoria.
	 */
	List<AuditoriaFuncional> findByTraceId(String traceId);
}
