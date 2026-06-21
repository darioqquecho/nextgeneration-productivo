package net.royal.erp.hr.infrastructure.maestros.parametro.oracle;

import java.time.Instant;
import java.util.Optional;

import net.royal.erp.framework.database.VersionedSqlResourceLoader;
import net.royal.erp.hr.application.maestros.parametro.aprobacionmasiva.port.AprobacionMasivaParametrosRepository;
import net.royal.erp.hr.domain.parametro.*;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Adapter Oracle del caso Aprobacion masiva de Parametros V1.
 */
public class OracleAprobacionMasivaParametrosV1Adapter implements AprobacionMasivaParametrosRepository {
	private final JdbcTemplate jdbc;
	private final String findByIdSql;
	private final String approveIfPendingSql;

	public OracleAprobacionMasivaParametrosV1Adapter(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
		this.findByIdSql = sql("find-by-id");
		this.approveIfPendingSql = sql("approve-if-pending");
	}

	private String sql(String statementName) {
		return VersionedSqlResourceLoader.load("hr", "oracle", "maestros", "aprobacionmasivaparametros", "parametros",
				"v1", statementName);
	}

	public Optional<Parametro> findById(ParametroId id) {
		return jdbc.query(findByIdSql, (rs, rowNum) -> OracleParametroJdbcSupport.map(rs), id.compania(),
				id.codigo()).stream().findFirst();
	}

	public boolean approveIfPending(ParametroId id, String usuario, Instant fechaModif) {
		return jdbc.update(approveIfPendingSql, usuario, OracleParametroJdbcSupport.toTimestamp(fechaModif),
				id.compania(), id.codigo()) > 0;
	}
}
