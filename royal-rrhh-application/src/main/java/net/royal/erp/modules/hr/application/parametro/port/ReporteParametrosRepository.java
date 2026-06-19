package net.royal.erp.modules.hr.application.parametro.port;

import java.util.List;

import net.royal.erp.modules.hr.domain.parametro.Parametro;

/**
 * Puerto de persistencia del caso de uso Reporte de Parametro.
 */
public interface ReporteParametrosRepository {
	List<Parametro> findAll();
}
