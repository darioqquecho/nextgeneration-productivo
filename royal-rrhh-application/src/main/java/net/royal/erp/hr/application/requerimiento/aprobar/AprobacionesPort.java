package net.royal.erp.hr.application.requerimiento.aprobar;

/**
 * Implementa: - ASIS-028 Workflow y Aprobaciones. - ARCH-018 Reglas de
 * Dependencia.
 */
public interface AprobacionesPort {
	/**
	 * Solicita aprobación al módulo Aprobaciones.
	 *
	 * Implementa: - ASIS-028.
	 */
	String aprobar(String businessKey, String usuario, String accion, String comentario);
}
