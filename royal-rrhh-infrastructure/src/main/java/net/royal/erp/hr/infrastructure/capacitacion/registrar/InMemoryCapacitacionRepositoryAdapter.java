package net.royal.erp.hr.infrastructure.capacitacion.registrar;

import net.royal.erp.hr.application.capacitacion.registrar.port.CapacitacionRepository;
import net.royal.erp.hr.domain.capacitacion.*;
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
