package net.royal.erp.framework.database;

/**
 * Implementa: - ARCH-009 Bases de Datos. - MOD-013 Persistencia configurable.
 *
 * Propósito: Resolver adapter de persistencia desde configuración externa.
 */
public class PersistenceAdapterResolver {
	/**
	 * Convierte texto de configuración en tipo de adapter.
	 *
	 * Implementa: - ARCH-009 Bases de Datos.
	 */
	public PersistenceAdapterType resolve(String value) {
		if (value == null || value.isBlank()) {
			return PersistenceAdapterType.IN_MEMORY;
		}
		return PersistenceAdapterType.valueOf(value.trim().toUpperCase());
	}
}
