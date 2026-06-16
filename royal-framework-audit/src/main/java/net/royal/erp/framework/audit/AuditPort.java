package net.royal.erp.framework.audit;

/**
 * Implementa: - ARCH-015 Auditoría Técnica y Funcional. - ARCH-018 Reglas de
 * Dependencia.
 *
 * Propósito: Puerto técnico de auditoría.
 */
public interface AuditPort {
	/**
	 * Registra auditoría funcional.
	 *
	 * Implementa: - ARCH-015 Auditoría.
	 */
	void register(FunctionalAuditRecord record);
}
