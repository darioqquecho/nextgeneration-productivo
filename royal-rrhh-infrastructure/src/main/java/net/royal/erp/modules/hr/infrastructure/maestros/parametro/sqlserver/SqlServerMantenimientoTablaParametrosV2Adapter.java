package net.royal.erp.modules.hr.infrastructure.maestros.parametro.sqlserver;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Adapter SQL Server del caso Mantenimiento de Parametro V2.
 */
public class SqlServerMantenimientoTablaParametrosV2Adapter extends AbstractSqlServerMantenimientoTablaParametrosAdapter {
	public SqlServerMantenimientoTablaParametrosV2Adapter(JdbcTemplate jdbc) {
		super(jdbc, "v2");
	}
}
