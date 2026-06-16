package net.royal.erp.modules.rrhh.infrastructure.parametro;

import net.royal.erp.modules.rrhh.domain.parametro.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Implementa: - MOD-013 Persistencia in-memory como adapter. - ARCH-009 Bases
 * de Datos.
 */
public class InMemoryParametroRepositoryAdapter implements ParametroRepository {
	private final Map<String, Parametro> data = new ConcurrentHashMap<>();

	public boolean existsById(ParametroId id) {
		return data.containsKey(id.value());
	}

	public Optional<Parametro> findById(ParametroId id) {
		return Optional.ofNullable(data.get(id.value()));
	}

	public List<Parametro> findAll() {
		return List.copyOf(data.values());
	}

	public void save(Parametro parametro) {
		data.put(parametro.id().value(), parametro);
	}

	public void deleteById(ParametroId id) {
		data.remove(id.value());
	}
}
