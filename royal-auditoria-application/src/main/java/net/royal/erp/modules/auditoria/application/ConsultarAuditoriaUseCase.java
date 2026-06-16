package net.royal.erp.modules.auditoria.application;

import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.kernel.UseCase;
import net.royal.erp.modules.auditoria.domain.AuditoriaRepository;

/**
 * Implementa: - ARCH-015 Auditoría. - MOD-012 CU-005 ConsultarAuditoria.
 */
public class ConsultarAuditoriaUseCase implements UseCase<ConsultarAuditoriaQuery, ConsultarAuditoriaResult> {
	private final AuditoriaRepository repository;

	/**
	 * Crea caso de uso de consulta de auditoría.
	 *
	 * Implementa: - ARCH-018 Reglas de dependencia.
	 */
	public ConsultarAuditoriaUseCase(AuditoriaRepository repository) {
		this.repository = repository;
	}

	/**
	 * Consulta auditoría por TraceId.
	 *
	 * Implementa: - MOD-012 CU-005 ConsultarAuditoria.
	 */
	public ConsultarAuditoriaResult execute(ConsultarAuditoriaQuery command, FunctionalContext context) {
		return new ConsultarAuditoriaResult(
				repository.findByTraceId(command.traceId()).stream().map(Object::toString).toList());
	}
}
