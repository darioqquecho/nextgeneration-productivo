package net.royal.erp.modules.alertas.domain.repository;

import java.util.List;
import java.util.Map;

/**
 * Implementa: - ARCH-009 Bases de Datos. - MOD-012 Migración SQL HBM XML
 * legacy.
 *
 * Corrige / Evoluciona: - ASIS-024 Gestión de SQL HBM XML.
 *
 * Propósito: Puerto para ejecutar queries legacy migradas desde archivos
 * *.sql.hbm.xml.
 */
public interface AlertasNamedQueryRepository {
	List<Map<String, Object>> query(String queryName, Map<String, Object> parameters);

	int update(String queryName, Map<String, Object> parameters);

	boolean exists(String queryName);
}
