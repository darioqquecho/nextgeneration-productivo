package net.royal.erp.modules.rrhh.infrastructure.audit;

import net.royal.erp.framework.audit.*;

/**
 * Implementa: - ARCH-015 Auditoría.
 *
 * Propósito: Adapter temporal de auditoría a consola.
 */
public class ConsoleAuditAdapter implements AuditPort {
	public void register(FunctionalAuditRecord record) {
		System.out.println("[AUDIT] " + record);
	}
}
