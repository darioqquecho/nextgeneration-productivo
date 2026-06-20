package net.royal.erp.modules.hr.infrastructure.maestros.parametro.oracle;

import java.sql.*;
import java.time.Instant;

import net.royal.erp.modules.hr.domain.parametro.*;

final class OracleParametroJdbcSupport {
	private OracleParametroJdbcSupport() {
	}

	static Parametro map(ResultSet rs) throws SQLException {
		return Parametro.cargar(rs.getString("compania"), rs.getString("codigo"), rs.getString("nombre"),
				rs.getBigDecimal("Precio"), toInteger(rs, "cantidad"), toInstant(rs.getTimestamp("FechaProceso")),
				rs.getString("estado"), rs.getString("UltimoUsuario"), toInstant(rs.getTimestamp("UltimaFechaModif")));
	}

	static String estado(Parametro parametro) {
		return parametro.estado() == null ? null : parametro.estado().name();
	}

	static Timestamp toTimestamp(Instant instant) {
		return instant == null ? null : Timestamp.from(instant);
	}

	private static Instant toInstant(Timestamp timestamp) {
		return timestamp == null ? null : timestamp.toInstant();
	}

	private static Integer toInteger(ResultSet rs, String column) throws SQLException {
		Object value = rs.getObject(column);
		return value == null ? null : ((Number) value).intValue();
	}
}
