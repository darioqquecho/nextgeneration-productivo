package net.royal.erp.framework.application;

/**
 * Clase maestra para repositorios/ports de application.
 */
public abstract class RoyalBaseRepository {
	protected void requireId(Object id) {
		if (id == null) {
			throw new IllegalArgumentException("id requerido");
		}
	}
}
