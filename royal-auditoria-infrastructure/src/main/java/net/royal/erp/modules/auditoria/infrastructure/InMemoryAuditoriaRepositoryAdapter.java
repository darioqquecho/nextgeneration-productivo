package net.royal.erp.modules.auditoria.infrastructure;

import net.royal.erp.modules.auditoria.domain.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Implementa: - MOD-013 Persistencia in-memory como adapter.
 */
public class InMemoryAuditoriaRepositoryAdapter implements AuditoriaRepository {
	private final List<AuditoriaFuncional> data = new CopyOnWriteArrayList<>();

	/**
	 * Guarda auditoría en memoria.
	 *
	 * Implementa: - ARCH-015 Auditoría.
	 */
	public void save(AuditoriaFuncional auditoria) {
		data.add(auditoria);
	}

	/**
	 * Consulta auditoría por TraceId.
	 *
	 * Implementa: - MOD-012 CU-005 ConsultarAuditoria.
	 */
	public List<AuditoriaFuncional> findByTraceId(String traceId) {
		return data.stream().filter(a -> a.traceId().equals(traceId)).toList();
	}
}
