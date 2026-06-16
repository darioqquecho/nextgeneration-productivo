package net.royal.erp.modules.alertas.infrastructure.persistence;

import net.royal.erp.framework.kernel.BusinessException;
import net.royal.erp.modules.alertas.domain.repository.AlertasNamedQueryRepository;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import java.util.List;
import java.util.Map;

/**
 * Implementa: - ASIS-024 SQL HBM XML. - ARCH-009 SQL Server.
 *
 * Propósito: Ejecuta named queries heredadas desde archivos *.sql.hbm.xml del
 * módulo Alertas.
 */
public class SqlServerNamedQueryRepository implements AlertasNamedQueryRepository {
	private final NamedParameterJdbcTemplate jdbc;
	private final SqlHbmCatalog catalog;

	public SqlServerNamedQueryRepository(NamedParameterJdbcTemplate jdbc, SqlHbmCatalog catalog) {
		this.jdbc = jdbc;
		this.catalog = catalog;
	}

	/** Implementa ASIS-024. Ejecuta SELECT legacy. */
	@Override
	public List<Map<String, Object>> query(String queryName, Map<String, Object> parameters) {
		String sql = catalog.get(queryName)
				.orElseThrow(() -> new BusinessException("ALT-SQL-404", "Query no encontrada: " + queryName));
		return jdbc.queryForList(sql, parameters == null ? Map.of() : parameters);
	}

	/** Implementa ASIS-024. Ejecuta UPDATE/DELETE legacy. */
	@Override
	public int update(String queryName, Map<String, Object> parameters) {
		String sql = catalog.get(queryName)
				.orElseThrow(() -> new BusinessException("ALT-SQL-404", "Query no encontrada: " + queryName));
		return jdbc.update(sql, parameters == null ? Map.of() : parameters);
	}

	/** Implementa MOD-012. Verifica si named query existe. */
	@Override
	public boolean exists(String queryName) {
		return catalog.exists(queryName);
	}
}
