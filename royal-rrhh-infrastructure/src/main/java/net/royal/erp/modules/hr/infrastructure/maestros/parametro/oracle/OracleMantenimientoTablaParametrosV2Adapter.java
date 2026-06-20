package net.royal.erp.modules.hr.infrastructure.maestros.parametro.oracle;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Adapter Oracle del caso Mantenimiento de Parametro V2.
 */
public class OracleMantenimientoTablaParametrosV2Adapter extends AbstractOracleMantenimientoTablaParametrosAdapter {
	public OracleMantenimientoTablaParametrosV2Adapter(JdbcTemplate jdbc) {
		super(jdbc, "v2");
	}
}
