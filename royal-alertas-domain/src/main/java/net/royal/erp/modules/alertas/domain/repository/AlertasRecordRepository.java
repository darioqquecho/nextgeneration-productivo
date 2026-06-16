package net.royal.erp.modules.alertas.domain.repository;

import net.royal.erp.modules.alertas.domain.model.AlertasRecord;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementa: - ARCH-018 Reglas de Dependencia. - MOD-012 Migración funcional
 * de royal-alertas-api.
 *
 * Propósito: Puerto de persistencia para operaciones CRUD genéricas del módulo
 * Alertas.
 */
public interface AlertasRecordRepository {
	Optional<AlertasRecord> findById(String entityName, Map<String, Object> id);

	List<Map<String, Object>> findAll(String entityName, int limit);

	AlertasRecord insert(AlertasRecord record);

	AlertasRecord update(AlertasRecord record);

	AlertasRecord annul(String entityName, Map<String, Object> id);

	void delete(String entityName, Map<String, Object> id);
}
