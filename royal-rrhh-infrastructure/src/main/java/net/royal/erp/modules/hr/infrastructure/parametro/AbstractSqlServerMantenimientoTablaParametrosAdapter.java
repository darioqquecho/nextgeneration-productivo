package net.royal.erp.modules.hr.infrastructure.parametro;

import java.util.*;

import net.royal.erp.modules.hr.application.parametro.port.MantenimientoTablaParametrosRepository;
import net.royal.erp.modules.hr.domain.parametro.*;
import org.springframework.jdbc.core.JdbcTemplate;

abstract class AbstractSqlServerMantenimientoTablaParametrosAdapter implements MantenimientoTablaParametrosRepository {
	private final JdbcTemplate jdbc;
	private final ParametroSqlStatements sql;

	AbstractSqlServerMantenimientoTablaParametrosAdapter(JdbcTemplate jdbc, String version) {
		this.jdbc = jdbc;
		this.sql = new ParametroSqlStatements("mantenimientoparametros", version);
	}

	public boolean existsById(ParametroId id) {
		Integer count = jdbc.queryForObject(sql.existsById(), Integer.class, id.compania(), id.codigo());
		return count != null && count > 0;
	}

	public Optional<Parametro> findById(ParametroId id) {
		return jdbc.query(sql.findById(), (rs, rowNum) -> SqlServerParametroJdbcSupport.map(rs), id.compania(),
				id.codigo()).stream().findFirst();
	}

	public List<Parametro> findAll() {
		return jdbc.query(sql.findAll(), (rs, rowNum) -> SqlServerParametroJdbcSupport.map(rs));
	}

	public void save(Parametro parametro) {
		if (existsById(parametro.id())) {
			jdbc.update(sql.update(), parametro.nombre(), SqlServerParametroJdbcSupport.estado(parametro),
					parametro.ultimoUsuario(), SqlServerParametroJdbcSupport.toTimestamp(parametro.ultimaFechaModif()),
					parametro.id().compania(), parametro.id().codigo());
			return;
		}
		jdbc.update(sql.insert(), parametro.id().compania(), parametro.id().codigo(), parametro.nombre(),
				SqlServerParametroJdbcSupport.estado(parametro), parametro.ultimoUsuario(),
				SqlServerParametroJdbcSupport.toTimestamp(parametro.ultimaFechaModif()));
	}

	public void deleteById(ParametroId id) {
		jdbc.update(sql.deleteById(), id.compania(), id.codigo());
	}
}
