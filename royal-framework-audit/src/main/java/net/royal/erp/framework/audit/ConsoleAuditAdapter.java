package net.royal.erp.framework.audit;

/**
 * Adapter simple de auditoria a consola.
 */
public class ConsoleAuditAdapter implements AuditPort {
	@Override
	public void register(FunctionalAuditRecord record) {
		System.out.println("[AUDIT] " + record);
	}
}
