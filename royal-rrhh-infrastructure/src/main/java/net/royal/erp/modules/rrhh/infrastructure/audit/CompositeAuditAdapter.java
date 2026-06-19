package net.royal.erp.modules.rrhh.infrastructure.audit;

import java.util.List;

import net.royal.erp.framework.audit.*;

/**
 * Envia la auditoria funcional a varios destinos.
 */
public class CompositeAuditAdapter implements AuditPort {
	private final List<AuditPort> delegates;

	public CompositeAuditAdapter(AuditPort... delegates) {
		this.delegates = List.of(delegates);
	}

	@Override
	public void register(FunctionalAuditRecord record) {
		for (AuditPort delegate : delegates) {
			delegate.register(record);
		}
	}
}
