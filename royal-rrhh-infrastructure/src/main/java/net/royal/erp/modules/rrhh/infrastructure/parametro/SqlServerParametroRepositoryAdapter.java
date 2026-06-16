package net.royal.erp.modules.rrhh.infrastructure.parametro;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

import net.royal.erp.modules.rrhh.domain.parametro.*;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Implementa: - MOD-013 Persistencia SQL Server para HR_Parametros.
 */
public class SqlServerParametroRepositoryAdapter implements ParametroRepository {
	private final JdbcTemplate jdbc;

	public SqlServerParametroRepositoryAdapter(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	public boolean existsById(ParametroId id) {
		Integer count = jdbc.queryForObject(
				"select count(1) from HR_Parametros where compania = ? and codigo = ?", Integer.class, id.compania(),
				id.codigo());
		return count != null && count > 0;
	}

	public Optional<Parametro> findById(ParametroId id) {
		List<Parametro> rows = jdbc.query(
				"select compania, codigo, nombre, estado, UltimoUsuario, UltimaFechaModif "
						+ "from HR_Parametros where compania = ? and codigo = ?",
				(rs, rowNum) -> Parametro.cargar(rs.getString("compania"), rs.getString("codigo"),
						rs.getString("nombre"), rs.getString("estado"), rs.getString("UltimoUsuario"),
						toInstant(rs.getTimestamp("UltimaFechaModif"))),
				id.compania(), id.codigo());
		return rows.stream().findFirst();
	}

	public List<Parametro> findAll() {
		return jdbc.query("select compania, codigo, nombre, estado, UltimoUsuario, UltimaFechaModif from HR_Parametros",
				(rs, rowNum) -> Parametro.cargar(rs.getString("compania"), rs.getString("codigo"),
						rs.getString("nombre"), rs.getString("estado"), rs.getString("UltimoUsuario"),
						toInstant(rs.getTimestamp("UltimaFechaModif"))));
	}

	public void save(Parametro parametro) {
		if (existsById(parametro.id())) {
			jdbc.update(
					"update HR_Parametros set nombre = ?, estado = ?, UltimoUsuario = ?, UltimaFechaModif = ? "
							+ "where compania = ? and codigo = ?",
					parametro.nombre(), estado(parametro), parametro.ultimoUsuario(), toTimestamp(parametro.ultimaFechaModif()),
					parametro.id().compania(), parametro.id().codigo());
			return;
		}
		jdbc.update(
				"insert into HR_Parametros (compania, codigo, nombre, estado, UltimoUsuario, UltimaFechaModif) "
						+ "values (?, ?, ?, ?, ?, ?)",
				parametro.id().compania(), parametro.id().codigo(), parametro.nombre(), estado(parametro),
				parametro.ultimoUsuario(), toTimestamp(parametro.ultimaFechaModif()));
	}

	public void deleteById(ParametroId id) {
		jdbc.update("delete from HR_Parametros where compania = ? and codigo = ?", id.compania(), id.codigo());
	}

	private static String estado(Parametro parametro) {
		return parametro.estado() == null ? null : parametro.estado().name();
	}

	private static Instant toInstant(Timestamp timestamp) {
		return timestamp == null ? null : timestamp.toInstant();
	}

	private static Timestamp toTimestamp(Instant instant) {
		return instant == null ? null : Timestamp.from(instant);
	}
}
