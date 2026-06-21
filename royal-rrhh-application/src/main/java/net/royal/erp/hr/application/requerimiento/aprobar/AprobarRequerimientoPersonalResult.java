package net.royal.erp.hr.application.requerimiento.aprobar;

/**
 * Implementa: - ARCH-013 CQRS.
 */
public record AprobarRequerimientoPersonalResult(String codigoRequerimiento, String estado, String version,
		String traceId) {
}
