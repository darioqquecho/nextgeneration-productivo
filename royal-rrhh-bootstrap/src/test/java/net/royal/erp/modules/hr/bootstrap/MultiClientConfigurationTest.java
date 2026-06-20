package net.royal.erp.modules.hr.bootstrap;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;

import net.royal.erp.framework.kernel.BusinessException;
import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.licensing.ClientCatalogLicenseChecker;
import net.royal.erp.framework.security.InMemoryPermissionChecker;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.DefaultResourceLoader;

class MultiClientConfigurationTest {
	@Test
	void cargaClientesDesdeCatalogoSinDuplicarAplicacion() {
		var catalog = new ClientCatalogXmlLoader(new DefaultResourceLoader()).load("classpath:client-catalog.xml");

		assertTrue(catalog.requireByClientId("demo-client").moduleEnabled("HR"));
		assertEquals("SQL_SERVER", catalog.requireByClientId("acme").property("persistence.adapter").orElseThrow());
		assertEquals("ORACLE", catalog.requireByClientId("contoso").property("persistence.adapter").orElseThrow());
	}

	@Test
	void licenciaSeResuelvePorCliente() {
		var catalog = new ClientCatalogXmlLoader(new DefaultResourceLoader()).load("classpath:client-catalog.xml");
		var checker = new ClientCatalogLicenseChecker(catalog);

		checker.checkModuleEnabled(context("acme", "admin"), "HR");

		assertThrows(BusinessException.class, () -> checker.checkModuleEnabled(context("cliente-no-configurado", "admin"), "HR"));
	}

	@Test
	void permisosPuedenVariarPorCliente() {
		InMemoryPermissionChecker checker = new InMemoryPermissionChecker();
		checker.grant("acme", "admin", "HR_MANTENIMIENTO_DE_PARAMETRO");

		checker.check(context("acme", "admin"), "HR_MANTENIMIENTO_DE_PARAMETRO");

		assertThrows(BusinessException.class,
				() -> checker.check(context("contoso", "admin"), "HR_MANTENIMIENTO_DE_PARAMETRO"));
	}

	private FunctionalContext context(String clientId, String userId) {
		return new FunctionalContext("tenant-" + clientId, clientId, userId, "HR", "Maestros", "Registrar",
				"Mantenimiento de Parametro", null, null, null, Instant.now());
	}
}
