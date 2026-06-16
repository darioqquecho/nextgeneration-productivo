package net.royal.erp.modules.rrhh.domain.parametro;

import java.util.List;
import java.util.Optional;

/**
 * Implementa: - ARCH-018 Reglas de Dependencia. - MOD-012 CU-001 CRUD
 * HR_Parametros.
 *
 * Propósito: Puerto de repositorio para HR_Parametros.
 */
public interface ParametroRepository {
	boolean existsById(ParametroId id);

	Optional<Parametro> findById(ParametroId id);

	List<Parametro> findAll();

	void save(Parametro parametro);

	void deleteById(ParametroId id);
}
