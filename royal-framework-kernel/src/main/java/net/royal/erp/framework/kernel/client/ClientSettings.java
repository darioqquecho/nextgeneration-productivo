package net.royal.erp.framework.kernel.client;

import java.util.*;

/**
 * Configuracion funcional de un cliente sin duplicar codigo ni artefactos.
 */
public record ClientSettings(String tenantId, String clientId, Set<String> enabledModules,
		Map<String, String> properties) {
	public ClientSettings {
		Objects.requireNonNull(tenantId, "tenantId is required");
		Objects.requireNonNull(clientId, "clientId is required");
		enabledModules = Set.copyOf(enabledModules == null ? Set.of() : enabledModules);
		properties = Map.copyOf(properties == null ? Map.of() : properties);
	}

	public boolean moduleEnabled(String module) {
		return enabledModules.contains(module);
	}

	public Optional<String> property(String name) {
		return Optional.ofNullable(properties.get(name));
	}
}
