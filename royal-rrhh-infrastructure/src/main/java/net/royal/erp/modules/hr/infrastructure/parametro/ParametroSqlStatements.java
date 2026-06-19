package net.royal.erp.modules.hr.infrastructure.parametro;

import net.royal.erp.framework.database.VersionedSqlResourceLoader;

/**
 * Agrupa las sentencias SQL de un caso de uso y version especificos.
 */
final class ParametroSqlStatements {
	private static final String MODULE = "hr";
	private static final String PROCESS = "maestros";
	private static final String RESOURCE = "parametros";

	private final String existsById;
	private final String findById;
	private final String findAll;
	private final String insert;
	private final String update;
	private final String deleteById;

	ParametroSqlStatements(String useCase, String version) {
		this.existsById = load(useCase, version, "exists-by-id");
		this.findById = load(useCase, version, "find-by-id");
		this.findAll = load(useCase, version, "find-all");
		this.insert = load(useCase, version, "insert");
		this.update = load(useCase, version, "update");
		this.deleteById = load(useCase, version, "delete-by-id");
	}

	private String load(String useCase, String version, String statementName) {
		return VersionedSqlResourceLoader.load(MODULE, PROCESS, useCase, RESOURCE, version, statementName);
	}

	String existsById() {
		return existsById;
	}

	String findById() {
		return findById;
	}

	String findAll() {
		return findAll;
	}

	String insert() {
		return insert;
	}

	String update() {
		return update;
	}

	String deleteById() {
		return deleteById;
	}
}
