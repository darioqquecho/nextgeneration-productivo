package net.royal.erp.framework.versioning;

import net.royal.erp.framework.kernel.FunctionalContext;

/**
 * Implementa: - ARCH-010 Versionamiento Funcional.
 *
 * Corrige / Evoluciona: - ASIS-031 Falta de versionamiento funcional.
 *
 * Propósito: Resolver versión aplicable a un caso de uso.
 */
public interface FunctionalVersionResolver {
	/**
	 * Resuelve versión funcional.
	 *
	 * Implementa: - ARCH-010 Versionamiento Funcional.
	 */
	FunctionalVersion resolve(FunctionalContext context);
}
