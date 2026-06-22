package net.royal.erp.hr.infrastructure.maestros.parametro.oracle;

import java.util.List;

import net.royal.erp.framework.database.VersionedSqlResourceLoader;
import net.royal.erp.hr.application.maestros.parametro.reporte.port.ReporteParametrosRepository;
import net.royal.erp.hr.domain.parametro.Parametro;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Adapter Oracle del caso Reporte de Parametro V1.
 */
public class OracleReporteParametrosV1Adapter implements ReporteParametrosRepository {
	private final JdbcTemplate jdbc;
	private final String findAllSql;

	public OracleReporteParametrosV1Adapter(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
		this.findAllSql = VersionedSqlResourceLoader.load("hr", "oracle", "maestros", "reporteparametros", "parametros",
				"v1", "find-all");
	}

	public List<Parametro> findAll() {
		return jdbc.query(findAllSql, (rs, rowNum) -> OracleParametroJdbcSupport.map(rs));
	}
}
