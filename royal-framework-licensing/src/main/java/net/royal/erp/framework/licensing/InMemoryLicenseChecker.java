package net.royal.erp.framework.licensing;

import net.royal.erp.framework.kernel.BusinessException;
import net.royal.erp.framework.kernel.FunctionalContext;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Implementa: - ARCH-025 Licenciamiento. - MOD-013 Adapter configurable
 * inicial.
 *
 * Propósito: Adapter in-memory para licencias por cliente.
 */
public class InMemoryLicenseChecker implements LicenseChecker {
	private final Map<String, Set<String>> modules = new ConcurrentHashMap<>();

	/**
	 * Habilita módulo a cliente.
	 *
	 * Implementa: - ARCH-025 Licenciamiento.
	 */
	public void enable(String clientId, String moduleCode) {
		modules.merge(clientId, Set.of(moduleCode), (a, b) -> {
			java.util.HashSet<String> merged = new java.util.HashSet<>(a);
			merged.addAll(b);
			return Set.copyOf(merged);
		});
	}

	/**
	 * Valida si módulo está habilitado.
	 *
	 * Implementa: - ARCH-025 Licenciamiento.
	 */
	public void checkModuleEnabled(FunctionalContext context, String moduleCode) {
		if (!modules.getOrDefault(context.clientId(), Set.of()).contains(moduleCode)) {
			throw new BusinessException("MODULE-NOT-LICENSED", moduleCode);
		}
	}
}
