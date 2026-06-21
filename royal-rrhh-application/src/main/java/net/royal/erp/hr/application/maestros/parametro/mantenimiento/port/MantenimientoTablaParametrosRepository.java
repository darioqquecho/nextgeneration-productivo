package net.royal.erp.hr.application.maestros.parametro.mantenimiento.port;

import java.util.List;
import java.util.Optional;

import net.royal.erp.hr.domain.parametro.*;

/**
 * Puerto de persistencia del caso de uso Mantenimiento de Parametro.
 */
public interface MantenimientoTablaParametrosRepository {
	boolean existsById(ParametroId id);

	Optional<Parametro> findById(ParametroId id);

	List<Parametro> findAll();

	void save(Parametro parametro);

	void deleteById(ParametroId id);
}
