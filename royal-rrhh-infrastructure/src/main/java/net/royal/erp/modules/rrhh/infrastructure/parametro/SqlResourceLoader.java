package net.royal.erp.modules.rrhh.infrastructure.parametro;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Carga sentencias SQL versionadas desde classpath.
 */
final class SqlResourceLoader {
	private static final String BASE_PATH = "sql/rrhh/parametro/";

	private SqlResourceLoader() {
	}

	static String load(String useCase, String version, String operation) {
		String resourcePath = BASE_PATH + useCase + "." + version.toLowerCase() + ".sql";
		String script = read(resourcePath);
		String sql = statements(script).get(operation);
		if (sql == null || sql.isBlank()) {
			throw new IllegalStateException("Sentencia SQL no encontrada: " + resourcePath + " :: " + operation);
		}
		return sql;
	}

	private static String read(String resourcePath) {
		try (InputStream stream = SqlResourceLoader.class.getClassLoader().getResourceAsStream(resourcePath)) {
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
		return result;
	}
}
