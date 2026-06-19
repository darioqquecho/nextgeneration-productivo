package net.royal.erp.modules.rrhh.infrastructure.parametro;

import java.util.List;

import net.royal.erp.modules.rrhh.application.parametro.port.ReporteParametrosRepository;
import net.royal.erp.modules.rrhh.domain.parametro.Parametro;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Adapter SQL Server del caso Reporte de Parametro V1.
 */
public class SqlServerReporteParametrosV1Adapter implements ReporteParametrosRepository {
	private final JdbcTemplate jdbc;
	private final String findAllSql;

	public SqlServerReporteParametrosV1Adapter(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
		this.findAllSql = SqlResourceLoader.load("reporteparametros", "v1", "find-all");
	}

	public List<Parametro> findAll() {
		return jdbc.query(findAllSql, (rs, rowNum) -> SqlServerParametroJdbcSupport.map(rs));
	}
}
