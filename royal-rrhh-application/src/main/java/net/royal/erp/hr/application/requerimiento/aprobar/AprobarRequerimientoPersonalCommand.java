package net.royal.erp.hr.application.requerimiento.aprobar;

/**
 * Implementa: - ARCH-013 CQRS.
 */
public record AprobarRequerimientoPersonalCommand(String codigoRequerimiento, String accion, String comentario,
		String usuarioAprobador) {
}
