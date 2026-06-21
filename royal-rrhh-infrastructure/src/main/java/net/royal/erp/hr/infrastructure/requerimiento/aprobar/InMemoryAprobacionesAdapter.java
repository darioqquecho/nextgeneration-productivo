package net.royal.erp.hr.infrastructure.requerimiento.aprobar;

import net.royal.erp.hr.application.requerimiento.aprobar.AprobacionesPort;

/**
 * Implementa: - ASIS-028 Workflow y Aprobaciones. - ARCH-018 Reglas de
 * Dependencia.
 */
public class InMemoryAprobacionesAdapter implements AprobacionesPort {
	public String aprobar(String businessKey, String usuario, String accion, String comentario) {
		return "APROBACION_OK";
	}
}
