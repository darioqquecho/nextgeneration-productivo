package net.royal.erp.framework.kernel.client;

import java.util.Optional;

/**
 * Catalogo de clientes/tenants soportados por una misma instalacion.
 */
public interface ClientCatalog {
	Optional<ClientSettings> findByClientId(String clientId);

	default ClientSettings requireByClientId(String clientId) {
		return findByClientId(clientId)
				.orElseThrow(() -> new IllegalArgumentException("Cliente no configurado: " + clientId));
	}
}
