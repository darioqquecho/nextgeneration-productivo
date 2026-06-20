package net.royal.erp.framework.kernel.client;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Catalogo simple para cargar clientes desde archivos externos o tests.
 */
public class InMemoryClientCatalog implements ClientCatalog {
	private final Map<String, ClientSettings> clients = new ConcurrentHashMap<>();

	public InMemoryClientCatalog register(ClientSettings settings) {
		clients.put(settings.clientId(), settings);
		return this;
	}

	public Optional<ClientSettings> findByClientId(String clientId) {
		return Optional.ofNullable(clients.get(clientId));
	}

	public Collection<ClientSettings> clients() {
		return List.copyOf(clients.values());
	}
}
