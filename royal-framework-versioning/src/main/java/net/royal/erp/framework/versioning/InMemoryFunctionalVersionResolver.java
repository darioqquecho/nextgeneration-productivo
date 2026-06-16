package net.royal.erp.framework.versioning;

import net.royal.erp.framework.kernel.FunctionalContext;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Implementa: - ARCH-010 Versionamiento Funcional. - MOD-013 Adapter
 * configurable inicial.
 *
 * Propósito: Resolver versiones en memoria para baseline productivo inicial.
 */
public class InMemoryFunctionalVersionResolver implements FunctionalVersionResolver {
	private final Map<String, FunctionalVersion> versions = new ConcurrentHashMap<>();
	private final FunctionalVersion defaultVersion;

	/**
	 * Crea resolver con versión por defecto.
	 *
	 * Implementa: - ARCH-010 Versionamiento.
	 */
	public InMemoryFunctionalVersionResolver(FunctionalVersion defaultVersion) {
		this.defaultVersion = defaultVersion;
	}

	/**
	 * Registra versión por cliente, módulo y caso de uso.
	 *
	 * Implementa: - ARCH-010 Resolución por configuración.
	 */
	public void register(String clientId, String module, String useCase, FunctionalVersion version) {
		versions.put(clientId + "|" + module + "|" + useCase, version);
	}

	/**
	 * Resuelve versión por header o configuración.
	 *
	 * Implementa: - ARCH-010 Versionamiento Funcional.
	 */
	public FunctionalVersion resolve(FunctionalContext context) {
		if (context.requestedVersion() != null && !context.requestedVersion().isBlank()) {
			return FunctionalVersion.valueOf(context.requestedVersion().toUpperCase());
		}
		return versions.getOrDefault(context.clientId() + "|" + context.module() + "|" + context.useCase(),
				defaultVersion);
	}
}
