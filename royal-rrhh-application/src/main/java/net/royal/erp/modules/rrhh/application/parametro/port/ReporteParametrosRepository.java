package net.royal.erp.modules.rrhh.application.parametro.port;

import java.util.List;

import net.royal.erp.modules.rrhh.domain.parametro.Parametro;

/**
 * Puerto de persistencia del caso de uso Reporte de Parametro.
 */
public interface ReporteParametrosRepository {
	List<Parametro> findAll();
}
