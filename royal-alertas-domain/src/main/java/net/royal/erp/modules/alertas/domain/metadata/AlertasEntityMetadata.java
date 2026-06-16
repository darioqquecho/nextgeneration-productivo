package net.royal.erp.modules.alertas.domain.metadata;

import java.util.List;

/**
 * Implementa: - ARCH-012 Estructura Lógica. - ARCH-009 Bases de Datos.
 *
 * Corrige / Evoluciona: - ASIS-023 Soporte multibase actual.
 *
 * Propósito: Metadato de tabla legacy Alertas usado por repositorios SQL
 * Server.
 */
public record AlertasEntityMetadata(String entityName, String legacyResource, String schema, String table,
		List<AlertasFieldMetadata> fields) {
	/**
	 * Retorna nombre calificado SQL Server.
	 *
	 * Implementa: - ARCH-009 Bases de Datos.
	 *
	 * @return schema.table.
	 */
	public String qualifiedTable() {
		return schema + "." + table;
	}

	/**
	 * Retorna campos de llave primaria.
	 *
	 * Implementa: - MOD-012 Migración funcional.
	 *
	 * @return lista de campos PK.
	 */
	public List<AlertasFieldMetadata> primaryKeys() {
		return fields.stream().filter(AlertasFieldMetadata::primaryKey).toList();
	}

	/**
	 * Retorna campos no PK.
	 *
	 * Implementa: - MOD-012 Migración funcional.
	 *
	 * @return lista de campos no PK.
	 */
	public List<AlertasFieldMetadata> nonPrimaryKeys() {
		return fields.stream().filter(f -> !f.primaryKey()).toList();
	}
}
