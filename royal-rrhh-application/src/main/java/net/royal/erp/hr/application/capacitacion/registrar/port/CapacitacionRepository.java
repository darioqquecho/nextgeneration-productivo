package net.royal.erp.hr.application.capacitacion.registrar.port;

import net.royal.erp.hr.domain.capacitacion.Capacitacion;

/**
 * Puerto de persistencia del caso de uso Registrar Capacitacion.
 */
public interface CapacitacionRepository {
	boolean existsByCodigo(String codigo);

	void save(Capacitacion capacitacion);
}
