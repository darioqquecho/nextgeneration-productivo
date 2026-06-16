package net.royal.erp.framework.kernel;

import java.util.List;

/**
 * Implementa: - ARCH-013 CQRS.
 *
 * Propósito: Resultado paginado reutilizable para Queries.
 */
public record PageResult<T>(List<T> items, int page, int size, long total) {
	/**
	 * Crea respuesta paginada.
	 *
	 * Implementa: - ARCH-013 CQRS.
	 */
	public PageResult {
		items = items == null ? List.of() : List.copyOf(items);
	}
}
