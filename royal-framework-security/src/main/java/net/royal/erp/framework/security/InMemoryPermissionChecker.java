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
	private final Map<String, Set<String>> permissionsByClientAndUser = new ConcurrentHashMap<>();

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

	public void grant(String clientId, String userId, String permission) {
		permissionsByClientAndUser.merge(key(clientId, userId), Set.of(permission), (a, b) -> {
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
		Set<String> clientPermissions = permissionsByClientAndUser
				.getOrDefault(key(context.clientId(), context.userId()), Set.of());
		Set<String> globalPermissions = permissionsByUser.getOrDefault(context.userId(), Set.of());
		if (!clientPermissions.contains(permission) && !globalPermissions.contains(permission)) {
			throw new BusinessException("SECURITY-DENIED", permission);
		}
	}

	private String key(String clientId, String userId) {
		return clientId + "|" + userId;
	}
}
