package net.royal.erp.modules.alertas.domain.model;

import java.util.Map;

/**
 * Implementa: - ARCH-003 DDD. - MOD-012 Migración funcional de
 * royal-alertas-api.
 *
 * Corrige / Evoluciona: - ASIS-016 Reglas de negocio concentradas en Services.
 *
 * Propósito: Modelo funcional genérico para registros del módulo Alertas.
 * Permite migrar las 38 entidades legacy sin depender de clases JPA heredadas
 * del framework antiguo.
 */
public record AlertasRecord(String entityName, Map<String, Object> values) {
	/**
	 * Crea registro validando entidad.
	 *
	 * Implementa: - ARCH-003 DDD.
	 *
	 * @param entityName entidad funcional.
	 * @param values     valores recibidos.
	 */
	public AlertasRecord {
		if (entityName == null || entityName.isBlank()) {
			throw new IllegalArgumentException("entityName is required");
		}
		values = values == null ? Map.of() : Map.copyOf(values);
	}
}
