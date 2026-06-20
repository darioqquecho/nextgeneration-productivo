package net.royal.erp.modules.hr.infrastructure.maestros.tiposeguro.sqlserver;

import java.sql.*;
import java.time.Instant;

import net.royal.erp.modules.hr.domain.tiposeguro.TipoSeguro;

final class SqlServerTipoSeguroJdbcSupport {
	private SqlServerTipoSeguroJdbcSupport() {
	}

	static TipoSeguro map(ResultSet rs) throws SQLException {
		return TipoSeguro.cargar(rs.getInt("TipoSeguro"), trimRight(rs.getString("Descripcion")),
				trimRight(rs.getString("Estado")), rs.getString("UltimoUsuario"),
				toInstant(rs.getTimestamp("UltimaFechaModif")));
	}

	static Timestamp toTimestamp(Instant instant) {
		return instant == null ? null : Timestamp.from(instant);
	}

	private static Instant toInstant(Timestamp timestamp) {
		return timestamp == null ? null : timestamp.toInstant();
	}

	private static String trimRight(String value) {
		return value == null ? null : value.stripTrailing();
	}
}
