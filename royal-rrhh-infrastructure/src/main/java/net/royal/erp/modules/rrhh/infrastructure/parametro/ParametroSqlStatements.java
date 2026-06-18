package net.royal.erp.modules.rrhh.infrastructure.parametro;

/**
 * Agrupa las sentencias SQL de un caso de uso y version especificos.
 */
final class ParametroSqlStatements {
	private final String existsById;
	private final String findById;
	private final String findAll;
	private final String insert;
	private final String update;
	private final String deleteById;

	ParametroSqlStatements(String useCase, String version) {
		this.existsById = SqlResourceLoader.load(useCase, version, "exists-by-id");
		this.findById = SqlResourceLoader.load(useCase, version, "find-by-id");
		this.findAll = SqlResourceLoader.load(useCase, version, "find-all");
		this.insert = SqlResourceLoader.load(useCase, version, "insert");
		this.update = SqlResourceLoader.load(useCase, version, "update");
		this.deleteById = SqlResourceLoader.load(useCase, version, "delete-by-id");
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
