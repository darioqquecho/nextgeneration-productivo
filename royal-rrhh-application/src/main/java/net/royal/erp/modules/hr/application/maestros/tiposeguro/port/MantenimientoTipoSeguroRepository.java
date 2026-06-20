package net.royal.erp.modules.hr.application.maestros.tiposeguro.port;

import java.util.List;
import java.util.Optional;

import net.royal.erp.modules.hr.domain.tiposeguro.*;

public interface MantenimientoTipoSeguroRepository {
	boolean existsById(TipoSeguroId id);

	Optional<TipoSeguro> findById(TipoSeguroId id);

	List<TipoSeguro> findAll();

	void save(TipoSeguro tipoSeguro);

	void deleteById(TipoSeguroId id);
}
