package net.royal.erp.modules.hr.infrastructure.parametro.sqlserver;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Adapter SQL Server del caso Mantenimiento de Parametro V1.
 */
public class SqlServerMantenimientoTablaParametrosV1Adapter extends AbstractSqlServerMantenimientoTablaParametrosAdapter {
	public SqlServerMantenimientoTablaParametrosV1Adapter(JdbcTemplate jdbc) {
		super(jdbc, "v1");
	}
}
