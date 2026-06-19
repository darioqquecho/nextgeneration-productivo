package net.royal.erp.modules.hr.application.requerimiento;

/**
 * Implementa: - ARCH-013 CQRS.
 */
public record AprobarRequerimientoPersonalResult(String codigoRequerimiento, String estado, String version,
		String traceId) {
}
