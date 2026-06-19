package net.royal.erp.modules.hr.infrastructure.parametro;

import java.sql.*;
import java.time.Instant;

import net.royal.erp.modules.hr.domain.parametro.*;

final class SqlServerParametroJdbcSupport {
	private SqlServerParametroJdbcSupport() {
	}

	static Parametro map(ResultSet rs) throws SQLException {
		return Parametro.cargar(rs.getString("compania"), rs.getString("codigo"), rs.getString("nombre"),
				rs.getString("estado"), rs.getString("UltimoUsuario"), toInstant(rs.getTimestamp("UltimaFechaModif")));
	}

	static String estado(Parametro parametro) {
		return parametro.estado() == null ? null : parametro.estado().name();
	}

	static Instant toInstant(Timestamp timestamp) {
		return timestamp == null ? null : timestamp.toInstant();
	}

	static Timestamp toTimestamp(Instant instant) {
		return instant == null ? null : Timestamp.from(instant);
	}
}
