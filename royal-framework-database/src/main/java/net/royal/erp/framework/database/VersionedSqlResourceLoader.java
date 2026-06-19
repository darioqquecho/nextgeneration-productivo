package net.royal.erp.framework.database;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Carga sentencias SQL versionadas desde recursos del classpath.
 *
 * Convencion: sql/{module}/{process}/{useCase}/{resource}.{version}.sql
 */
public final class VersionedSqlResourceLoader {
	private static final String ROOT = "sql/";

	private VersionedSqlResourceLoader() {
	}

	public static String load(String module, String process, String useCase, String resource, String version,
			String statementName) {
		String resourcePath = path(module, process, useCase, resource, version);
		String sql = statements(read(resourcePath)).get(statementName);
		if (sql == null || sql.isBlank()) {
			throw new IllegalStateException("Sentencia SQL no encontrada: " + resourcePath + " :: " + statementName);
		}
		return sql;
	}

	public static Map<String, String> loadAll(String module, String process, String useCase, String resource,
			String version) {
		return statements(read(path(module, process, useCase, resource, version)));
	}

	private static String path(String module, String process, String useCase, String resource, String version) {
		return ROOT + segment(module) + "/" + segment(process) + "/" + segment(useCase) + "/" + segment(resource)
				+ "." + segment(version) + ".sql";
	}

	private static String segment(String value) {
		if (value == null || value.isBlank()) {
			throw new IllegalArgumentException("SQL resource path segment is required");
		}
		return value.trim().toLowerCase(Locale.ROOT);
	}

	private static String read(String resourcePath) {
		try (InputStream stream = VersionedSqlResourceLoader.class.getClassLoader().getResourceAsStream(resourcePath)) {
			if (stream == null) {
				throw new IllegalStateException("Recurso SQL no encontrado: " + resourcePath);
			}
			return new String(stream.readAllBytes(), StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new IllegalStateException("No se pudo leer recurso SQL: " + resourcePath, e);
		}
	}

	private static Map<String, String> statements(String script) {
		Map<String, StringBuilder> builders = new LinkedHashMap<>();
		String current = null;
		for (String line : script.split("\\R")) {
			if (line.startsWith("-- name:")) {
				current = line.substring("-- name:".length()).trim();
				builders.putIfAbsent(current, new StringBuilder());
				continue;
			}
			if (current != null) {
				builders.get(current).append(line).append(System.lineSeparator());
			}
		}
		Map<String, String> result = new LinkedHashMap<>();
		builders.forEach((name, sql) -> result.put(name, sql.toString().trim()));
		return Map.copyOf(result);
	}
}
