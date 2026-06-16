package net.royal.erp.modules.rrhh.domain.capacitacion;

/**
 * Implementa: - ARCH-018 Reglas de Dependencia.
 */
public interface CapacitacionRepository {
	boolean existsByCodigo(String codigo);

	void save(Capacitacion capacitacion);
}
