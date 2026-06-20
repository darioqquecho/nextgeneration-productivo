package net.royal.erp.modules.hr.infrastructure.maestros.tiposeguro.inmemory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import net.royal.erp.modules.hr.application.maestros.tiposeguro.port.MantenimientoTipoSeguroRepository;
import net.royal.erp.modules.hr.domain.tiposeguro.*;

public class InMemoryTipoSeguroRepositoryAdapter implements MantenimientoTipoSeguroRepository {
	private final Map<Integer, TipoSeguro> data = new ConcurrentHashMap<>();

	public boolean existsById(TipoSeguroId id) {
		return data.containsKey(id.value());
	}

	public Optional<TipoSeguro> findById(TipoSeguroId id) {
		return Optional.ofNullable(data.get(id.value()));
	}

	public List<TipoSeguro> findAll() {
		return List.copyOf(data.values());
	}

	public void save(TipoSeguro tipoSeguro) {
		data.put(tipoSeguro.id().value(), tipoSeguro);
	}

	public void deleteById(TipoSeguroId id) {
		data.remove(id.value());
	}
}
