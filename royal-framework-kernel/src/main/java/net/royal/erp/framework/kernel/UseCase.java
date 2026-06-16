package net.royal.erp.framework.kernel;

/**
 * Implementa: - ARCH-012 Estructura Lógica. - ARCH-013 CQRS. - ARCH-018 Reglas
 * de Dependencia.
 *
 * Corrige / Evoluciona: - ASIS-007 Acoplamiento por herencia del core.
 *
 * Propósito: Contrato base para casos de uso sin obligar herencia.
 */
public interface UseCase<C, R> {
	/**
	 * Ejecuta un caso de uso.
	 *
	 * Implementa: - ARCH-013 CQRS. - MOD-012 Casos de uso mínimos.
	 */
	R execute(C command, FunctionalContext context);
}
