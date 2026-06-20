package net.royal.erp.modules.hr.application.requerimiento.aprobar;

/**
 * Implementa: - ARCH-013 CQRS.
 */
public record AprobarRequerimientoPersonalCommand(String codigoRequerimiento, String accion, String comentario,
		String usuarioAprobador) {
}
