package net.royal.erp.modules.rrhh.infrastructure.capacitacion;

import net.royal.erp.modules.rrhh.domain.capacitacion.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

/**
 * Implementa: - MOD-013 Persistencia in-memory como adapter.
 */
public class InMemoryCapacitacionRepositoryAdapter implements CapacitacionRepository {
	private final Map<String, Capacitacion> data = new ConcurrentHashMap<>();

	public boolean existsByCodigo(String codigo) {
		return data.containsKey(codigo);
	}

	public void save(Capacitacion capacitacion) {
		data.put(capacitacion.codigo(), capacitacion);
	}
}
