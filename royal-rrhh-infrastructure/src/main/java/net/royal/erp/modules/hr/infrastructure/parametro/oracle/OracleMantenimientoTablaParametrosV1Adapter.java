package net.royal.erp.modules.hr.infrastructure.parametro.oracle;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Adapter Oracle del caso Mantenimiento de Parametro V1.
 */
public class OracleMantenimientoTablaParametrosV1Adapter extends AbstractOracleMantenimientoTablaParametrosAdapter {
	public OracleMantenimientoTablaParametrosV1Adapter(JdbcTemplate jdbc) {
		super(jdbc, "v1");
	}
}
