package net.royal.erp.modules.rrhh.infrastructure.parametro;

import java.time.Instant;
import java.util.Optional;

import net.royal.erp.modules.rrhh.application.parametro.port.AprobacionMasivaParametrosRepository;
import net.royal.erp.modules.rrhh.domain.parametro.*;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Adapter SQL Server del caso Aprobacion masiva de Parametros V1.
 */
public class SqlServerAprobacionMasivaParametrosV1Adapter implements AprobacionMasivaParametrosRepository {
	private final JdbcTemplate jdbc;
	private final String findByIdSql;
	private final String approveIfPendingSql;

	public SqlServerAprobacionMasivaParametrosV1Adapter(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
		this.findByIdSql = SqlResourceLoader.load("aprobacionmasivaparametros", "v1", "find-by-id");
		this.approveIfPendingSql = SqlResourceLoader.load("aprobacionmasivaparametros", "v1",
				"approve-if-pending");
	}

	public Optional<Parametro> findById(ParametroId id) {
		return jdbc.query(findByIdSql, (rs, rowNum) -> SqlServerParametroJdbcSupport.map(rs), id.compania(),
				id.codigo()).stream().findFirst();
	}

	public boolean approveIfPending(ParametroId id, String usuario, Instant fechaModif) {
		return jdbc.update(approveIfPendingSql, usuario, SqlServerParametroJdbcSupport.toTimestamp(fechaModif),
				id.compania(), id.codigo()) > 0;
	}
}
