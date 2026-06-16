package net.royal.erp.modules.alertas.infrastructure.audit;

import net.royal.erp.framework.audit.AuditPort;
import net.royal.erp.framework.audit.FunctionalAuditRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementa: - ARCH-015 Auditoría Técnica y Funcional.
 *
 * Propósito: Adapter inicial de auditoría para Alertas. Emite log estructurado
 * y queda listo para integrarse con royal-auditoria-*.
 */
public class SqlServerAuditAdapter implements AuditPort {
	private static final Logger log = LoggerFactory.getLogger(SqlServerAuditAdapter.class);

	/** Implementa ARCH-015. Registra auditoría funcional. */
	@Override
	public void register(FunctionalAuditRecord record) {
		log.info(
				"AUDIT_ALERTAS module={} process={} functionality={} useCase={} version={} user={} result={} traceId={}",
				record.module(), record.process(), record.functionality(), record.useCase(), record.version(),
				record.userId(), record.result(), record.traceId());
	}
}
