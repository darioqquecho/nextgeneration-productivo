package net.royal.erp.framework.licensing;

import net.royal.erp.framework.kernel.BusinessException;
import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.kernel.client.ClientCatalog;

/**
 * Valida licencias usando el catalogo externo de clientes.
 */
public class ClientCatalogLicenseChecker implements LicenseChecker {
	private final ClientCatalog clientCatalog;

	public ClientCatalogLicenseChecker(ClientCatalog clientCatalog) {
		this.clientCatalog = clientCatalog;
	}

	public void checkModuleEnabled(FunctionalContext context, String moduleCode) {
		var client = clientCatalog.findByClientId(context.clientId())
				.orElseThrow(() -> new BusinessException("CLIENT-NOT-CONFIGURED", context.clientId()));
		if (!client.moduleEnabled(moduleCode)) {
			throw new BusinessException("MODULE-NOT-LICENSED", moduleCode);
		}
	}
}
