package net.royal.erp.modules.alertas.infrastructure.persistence;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilderFactory;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Implementa: - ASIS-024 Gestión de SQL HBM XML. - MOD-012 Migración funcional
 * de SQL legacy.
 *
 * Propósito: Carga los 40 archivos *.sql.hbm.xml heredados para ejecutar named
 * queries en SQL Server.
 */
public class SqlHbmCatalog {
	private final Map<String, String> queries = new LinkedHashMap<>();

	public SqlHbmCatalog() {
		load();
	}

	/**
	 * Carga queries desde classpath.
	 *
	 * Implementa: - ASIS-024 SQL HBM XML.
	 */
	public final void load() {
		try {
			PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
			Resource[] resources = resolver.getResources("classpath*:spring_alertas/hibernate/sql/*.xml");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
			factory.setFeature("http://xml.org/sax/features/validation", false);
			for (Resource resource : resources) {
				Document doc = factory.newDocumentBuilder().parse(resource.getInputStream());
				NodeList nodes = doc.getElementsByTagName("sql-query");
				for (int i = 0; i < nodes.getLength(); i++) {
					var node = nodes.item(i);
					String name = node.getAttributes().getNamedItem("name").getNodeValue();
					String sql = node.getTextContent().trim();
					queries.put(name, sql);
				}
			}
		} catch (Exception ex) {
			throw new IllegalStateException("No se pudo cargar SQL HBM legacy", ex);
		}
	}

	/**
	 * Obtiene SQL por nombre.
	 *
	 * Implementa: - ASIS-024 SQL HBM XML.
	 */
	public Optional<String> get(String name) {
		return Optional.ofNullable(queries.get(name));
	}

	/**
	 * Verifica existencia de named query.
	 *
	 * Implementa: - MOD-012 Migración funcional.
	 */
	public boolean exists(String name) {
		return queries.containsKey(name);
	}

	/**
	 * Lista nombres cargados.
	 *
	 * Implementa: - MOD-012 Migración funcional.
	 */
	public Set<String> names() {
		return Collections.unmodifiableSet(queries.keySet());
	}
}
