package net.royal.erp.hr.bootstrap;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;

import net.royal.erp.framework.versioning.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.w3c.dom.Element;

/**
 * Implementa: - ARCH-010 Versionamiento Funcional por catalogo externo.
 */
public class FunctionalVersionXmlCatalogLoader {
	private final ResourceLoader resourceLoader;

	public FunctionalVersionXmlCatalogLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	public InMemoryFunctionalVersionResolver load(String catalogLocation, FunctionalVersion defaultVersion) {
		InMemoryFunctionalVersionResolver resolver = new InMemoryFunctionalVersionResolver(defaultVersion);
		if (catalogLocation == null || catalogLocation.isBlank()) {
			return resolver;
		}

		Resource resource = resourceLoader.getResource(catalogLocation);
		if (!resource.exists()) {
			throw new IllegalStateException("Catalogo de versiones funcionales no encontrado: " + catalogLocation);
		}

		try (var input = resource.getInputStream()) {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
			factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
			factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
			var document = factory.newDocumentBuilder().parse(input);
			var mappings = document.getDocumentElement().getElementsByTagName("mapping");
			for (int i = 0; i < mappings.getLength(); i++) {
				Element mapping = (Element) mappings.item(i);
				resolver.register(required(mapping, "clientId"), required(mapping, "module"), required(mapping, "useCase"),
						FunctionalVersion.valueOf(required(mapping, "version").toUpperCase()));
			}
			return resolver;
		} catch (Exception e) {
			throw new IllegalStateException("No se pudo cargar catalogo de versiones funcionales: " + catalogLocation, e);
		}
	}

	private String required(Element element, String attribute) {
		String value = element.getAttribute(attribute);
		if (value == null || value.isBlank()) {
			throw new IllegalArgumentException("Atributo requerido en mapping de version funcional: " + attribute);
		}
		return value.trim();
	}
}
