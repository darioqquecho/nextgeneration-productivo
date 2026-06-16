package net.royal.erp.framework.security;

import net.royal.erp.framework.kernel.BusinessException;
import net.royal.erp.framework.kernel.FunctionalContext;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Implementa: - ARCH-006 Seguridad. - MOD-013 Adapter configurable inicial.
 *
 * Propósito: Adapter in-memory para permisos.
 */
public class InMemoryPermissionChecker implements PermissionChecker {
	private final Map<String, Set<String>> permissionsByUser = new ConcurrentHashMap<>();

	/**
	 * Otorga permiso a un usuario.
	 *
	 * Implementa: - ASIS-027 Evolución a permiso por caso de uso.
	 */
	public void grant(String userId, String permission) {
		permissionsByUser.merge(userId, Set.of(permission), (a, b) -> {
			java.util.HashSet<String> merged = new java.util.HashSet<>(a);
			merged.addAll(b);
			return Set.copyOf(merged);
		});
	}

	/**
	 * Valida permiso solicitado.
	 *
	 * Implementa: - ARCH-006 Seguridad.
	 */
	public void check(FunctionalContext context, String permission) {
		if (!permissionsByUser.getOrDefault(context.userId(), Set.of()).contains(permission)) {
			throw new BusinessException("SECURITY-DENIED", "Usuario sin permiso: " + permission);
		}
	}
}
