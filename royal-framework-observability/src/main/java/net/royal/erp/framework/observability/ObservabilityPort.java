package net.royal.erp.framework.observability;

import net.royal.erp.framework.kernel.FunctionalContext;

/**
 * Implementa: - ARCH-016 Observabilidad.
 *
 * Proposito: Puerto para registrar logs estructurados y metricas de ejecucion
 * por caso de uso.
 */
public interface ObservabilityPort {
	void succeeded(FunctionalContext context, long durationMillis);

	void failed(FunctionalContext context, long durationMillis, Throwable error);
}
