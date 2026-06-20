package net.royal.erp.modules.hr.bootstrap;

import java.util.*;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;

import net.royal.erp.framework.kernel.client.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.w3c.dom.Element;

/**
 * Carga clientes desde XML para evitar copias de la aplicacion por cliente.
 */
public class ClientCatalogXmlLoader {
	private final ResourceLoader resourceLoader;

	public ClientCatalogXmlLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	public InMemoryClientCatalog load(String catalogLocation) {
		InMemoryClientCatalog catalog = new InMemoryClientCatalog();
		Resource resource = resourceLoader.getResource(catalogLocation);
		if (!resource.exists()) {
			throw new IllegalStateException("Catalogo de clientes no encontrado: " + catalogLocation);
		}

		try (var input = resource.getInputStream()) {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
			factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
			factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
			var document = factory.newDocumentBuilder().parse(input);
			var clients = document.getDocumentElement().getElementsByTagName("client");
			for (int i = 0; i < clients.getLength(); i++) {
				Element client = (Element) clients.item(i);
				catalog.register(new ClientSettings(required(client, "tenantId"), required(client, "clientId"),
						modules(client), properties(client)));
			}
			return catalog;
		} catch (Exception e) {
			throw new IllegalStateException("No se pudo cargar catalogo de clientes: " + catalogLocation, e);
		}
	}

	private Set<String> modules(Element client) {
		Set<String> values = new LinkedHashSet<>();
		var modules = client.getElementsByTagName("module");
		for (int i = 0; i < modules.getLength(); i++) {
			Element module = (Element) modules.item(i);
			values.add(required(module, "code"));
		}
		return values;
	}

	private Map<String, String> properties(Element client) {
		Map<String, String> values = new LinkedHashMap<>();
		var properties = client.getElementsByTagName("property");
		for (int i = 0; i < properties.getLength(); i++) {
			Element property = (Element) properties.item(i);
			values.put(required(property, "name"), required(property, "value"));
		}
		return values;
	}

	private String required(Element element, String attribute) {
		String value = element.getAttribute(attribute);
		if (value == null || value.isBlank()) {
			throw new IllegalArgumentException("Atributo requerido en catalogo de clientes: " + attribute);
		}
		return value.trim();
	}
}
