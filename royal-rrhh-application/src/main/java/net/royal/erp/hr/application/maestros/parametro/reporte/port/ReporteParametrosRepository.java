package net.royal.erp.hr.application.maestros.parametro.reporte.port;

import java.util.List;

import net.royal.erp.hr.domain.parametro.Parametro;

/**
 * Puerto de persistencia del caso de uso Reporte de Parametro.
 */
public interface ReporteParametrosRepository {
	List<Parametro> findAll();
}
