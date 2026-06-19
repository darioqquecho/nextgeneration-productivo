package net.royal.erp.framework.database;

/**
 * Agrupa las sentencias SQL versionadas comunes de un CRUD.
 */
public final class VersionedCrudSqlStatements {
	private final String existsById;
	private final String findById;
	private final String findAll;
	private final String insert;
	private final String update;
	private final String deleteById;

	public VersionedCrudSqlStatements(String module, String process, String useCase, String resource, String version) {
		this.existsById = VersionedSqlResourceLoader.load(module, process, useCase, resource, version, "exists-by-id");
		this.findById = VersionedSqlResourceLoader.load(module, process, useCase, resource, version, "find-by-id");
		this.findAll = VersionedSqlResourceLoader.load(module, process, useCase, resource, version, "find-all");
		this.insert = VersionedSqlResourceLoader.load(module, process, useCase, resource, version, "insert");
		this.update = VersionedSqlResourceLoader.load(module, process, useCase, resource, version, "update");
		this.deleteById = VersionedSqlResourceLoader.load(module, process, useCase, resource, version, "delete-by-id");
	}

	public VersionedCrudSqlStatements(String module, String adapter, String process, String useCase, String resource,
			String version) {
		this.existsById = VersionedSqlResourceLoader.load(module, adapter, process, useCase, resource, version,
				"exists-by-id");
		this.findById = VersionedSqlResourceLoader.load(module, adapter, process, useCase, resource, version,
				"find-by-id");
		this.findAll = VersionedSqlResourceLoader.load(module, adapter, process, useCase, resource, version, "find-all");
		this.insert = VersionedSqlResourceLoader.load(module, adapter, process, useCase, resource, version, "insert");
		this.update = VersionedSqlResourceLoader.load(module, adapter, process, useCase, resource, version, "update");
		this.deleteById = VersionedSqlResourceLoader.load(module, adapter, process, useCase, resource, version,
				"delete-by-id");
	}

	public String existsById() {
		return existsById;
	}

	public String findById() {
		return findById;
	}

	public String findAll() {
		return findAll;
	}

	public String insert() {
		return insert;
	}

	public String update() {
		return update;
	}

	public String deleteById() {
		return deleteById;
	}
}
