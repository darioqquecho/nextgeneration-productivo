package net.royal.erp.framework.observability;

/**
 * Implementa: - ARCH-016 Observabilidad.
 *
 * Corrige / Evoluciona: - ASIS-031 Falta de métricas.
 *
 * Propósito: Medición simple de tiempos por caso de uso.
 */
public class ExecutionTimer {
	private final long startNanos;

	private ExecutionTimer() {
		this.startNanos = System.nanoTime();
	}

	/**
	 * Inicia medición.
	 *
	 * Implementa: - ARCH-016 Observabilidad.
	 */
	public static ExecutionTimer start() {
		return new ExecutionTimer();
	}

	/**
	 * Finaliza medición.
	 *
	 * Implementa: - ARCH-016 Observabilidad.
	 */
	public long stopMillis() {
		return (System.nanoTime() - startNanos) / 1_000_000;
	}
}
