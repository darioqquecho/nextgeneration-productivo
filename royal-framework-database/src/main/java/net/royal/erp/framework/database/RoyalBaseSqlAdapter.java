package net.royal.erp.framework.database;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Clase maestra para adapters SQL.
 */
public abstract class RoyalBaseSqlAdapter {
	private final JdbcTemplate jdbc;

	protected RoyalBaseSqlAdapter(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	protected JdbcTemplate jdbc() {
		return jdbc;
	}
}
