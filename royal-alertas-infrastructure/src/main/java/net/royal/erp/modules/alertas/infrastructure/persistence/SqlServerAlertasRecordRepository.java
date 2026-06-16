package net.royal.erp.modules.alertas.infrastructure.persistence;

import net.royal.erp.framework.kernel.BusinessException;
import net.royal.erp.modules.alertas.domain.metadata.*;
import net.royal.erp.modules.alertas.domain.model.AlertasRecord;
import net.royal.erp.modules.alertas.domain.repository.AlertasRecordRepository;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Implementa: - ARCH-009 Bases de Datos. - MOD-013 Persistencia SQL Server
 * real.
 *
 * Corrige / Evoluciona: - ASIS-023 Soporte multibase con mantenimiento
 * paralelo.
 *
 * Propósito: Adapter SQL Server funcional para CRUD genérico de las 38 tablas
 * legacy de Alertas.
 */
public class SqlServerAlertasRecordRepository implements AlertasRecordRepository {
	private final NamedParameterJdbcTemplate jdbc;

	public SqlServerAlertasRecordRepository(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	/** Implementa ARCH-009. Busca registro por PK. */
	@Override
	public Optional<AlertasRecord> findById(String entityName, Map<String, Object> id) {
		AlertasEntityMetadata meta = metadata(entityName);
		String where = wherePk(meta);
		String sql = "SELECT * FROM " + meta.qualifiedTable() + " WITH (NOLOCK) WHERE " + where;
		List<Map<String, Object>> rows = jdbc.queryForList(sql, normalize(meta, id));
		return rows.stream().findFirst().map(row -> new AlertasRecord(entityName, row));
	}

	/** Implementa ARCH-009. Lista registros con TOP seguro. */
	@Override
	public List<Map<String, Object>> findAll(String entityName, int limit) {
		AlertasEntityMetadata meta = metadata(entityName);
		int safeLimit = Math.max(1, Math.min(limit <= 0 ? 200 : limit, 1000));
		String sql = "SELECT TOP " + safeLimit + " * FROM " + meta.qualifiedTable() + " WITH (NOLOCK)";
		return jdbc.queryForList(sql, Map.of());
	}

	/** Implementa MOD-012. Inserta registro legacy. */
	@Override
	public AlertasRecord insert(AlertasRecord record) {
		AlertasEntityMetadata meta = metadata(record.entityName());
		Map<String, Object> params = normalize(meta, record.values());
		List<AlertasFieldMetadata> fields = meta.fields().stream().filter(f -> params.containsKey(f.field())).toList();
		String columns = fields.stream().map(AlertasFieldMetadata::column).collect(Collectors.joining(", "));
		String values = fields.stream().map(f -> ":" + f.field()).collect(Collectors.joining(", "));
		jdbc.update("INSERT INTO " + meta.qualifiedTable() + " (" + columns + ") VALUES (" + values + ")", params);
		return record;
	}

	/** Implementa MOD-012. Actualiza registro legacy por PK. */
	@Override
	public AlertasRecord update(AlertasRecord record) {
		AlertasEntityMetadata meta = metadata(record.entityName());
		Map<String, Object> params = normalize(meta, record.values());
		String set = meta.nonPrimaryKeys().stream().filter(f -> params.containsKey(f.field()))
				.map(f -> f.column() + " = :" + f.field()).collect(Collectors.joining(", "));
		if (set.isBlank())
			throw new BusinessException("ALT-SQL-001", "No hay campos para actualizar");
		jdbc.update("UPDATE " + meta.qualifiedTable() + " SET " + set + " WHERE " + wherePk(meta), params);
		return record;
	}

	/** Implementa MOD-012. Anula registro si existe campo ESTADO. */
	@Override
	public AlertasRecord annul(String entityName, Map<String, Object> id) {
		AlertasEntityMetadata meta = metadata(entityName);
		Map<String, Object> params = normalize(meta, id);
		boolean hasEstado = meta.fields().stream().anyMatch(f -> f.column().equalsIgnoreCase("ESTADO"));
		if (!hasEstado)
			throw new BusinessException("ALT-SQL-002", "La tabla no tiene columna ESTADO para anular");
		params.put("estado", "INA");
		jdbc.update("UPDATE " + meta.qualifiedTable() + " SET ESTADO = :estado WHERE " + wherePk(meta), params);
		return new AlertasRecord(entityName, params);
	}

	/** Implementa MOD-012. Elimina registro por PK. */
	@Override
	public void delete(String entityName, Map<String, Object> id) {
		AlertasEntityMetadata meta = metadata(entityName);
		jdbc.update("DELETE FROM " + meta.qualifiedTable() + " WHERE " + wherePk(meta), normalize(meta, id));
	}

	private AlertasEntityMetadata metadata(String entityName) {
		return AlertasMetadataCatalog.byEntity(entityName)
				.orElseThrow(() -> new BusinessException("ALT-META-404", "Entidad no migrada: " + entityName));
	}

	private String wherePk(AlertasEntityMetadata meta) {
		if (meta.primaryKeys().isEmpty())
			throw new BusinessException("ALT-META-001", "Entidad sin PK: " + meta.entityName());
		return meta.primaryKeys().stream().map(f -> f.column() + " = :" + f.field())
				.collect(Collectors.joining(" AND "));
	}

	private Map<String, Object> normalize(AlertasEntityMetadata meta, Map<String, Object> input) {
		Map<String, Object> out = new LinkedHashMap<>();
		Map<String, AlertasFieldMetadata> byField = meta.fields().stream()
				.collect(Collectors.toMap(AlertasFieldMetadata::field, f -> f, (a, b) -> a, LinkedHashMap::new));
		Map<String, AlertasFieldMetadata> byColumn = meta.fields().stream()
				.collect(Collectors.toMap(f -> f.column().toLowerCase(), f -> f, (a, b) -> a, LinkedHashMap::new));
		if (input != null) {
			input.forEach((k, v) -> {
				AlertasFieldMetadata f = byField.get(k);
				if (f == null)
					f = byColumn.get(k.toLowerCase());
				if (f != null)
					out.put(f.field(), v);
				else
					out.put(k, v);
			});
		}
		return out;
	}
}
