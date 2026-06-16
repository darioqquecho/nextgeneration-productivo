package net.royal.erp.modules.rrhh.infrastructure.aprobaciones;

import net.royal.erp.modules.rrhh.application.requerimiento.AprobacionesPort;

/**
 * Implementa: - ASIS-028 Workflow y Aprobaciones. - ARCH-018 Reglas de
 * Dependencia.
 */
public class InMemoryAprobacionesAdapter implements AprobacionesPort {
	public String aprobar(String businessKey, String usuario, String accion, String comentario) {
		return "APROBACION_OK";
	}
}
