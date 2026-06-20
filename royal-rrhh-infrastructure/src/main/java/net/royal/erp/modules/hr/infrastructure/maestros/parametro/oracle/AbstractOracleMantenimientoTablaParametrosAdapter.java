package net.royal.erp.modules.hr.infrastructure.maestros.parametro.oracle;

import java.util.*;

import net.royal.erp.framework.database.VersionedCrudSqlStatements;
import net.royal.erp.modules.hr.application.maestros.parametro.port.MantenimientoTablaParametrosRepository;
import net.royal.erp.modules.hr.domain.parametro.*;
import org.springframework.jdbc.core.JdbcTemplate;

abstract class AbstractOracleMantenimientoTablaParametrosAdapter implements MantenimientoTablaParametrosRepository {
	private final JdbcTemplate jdbc;
	private final VersionedCrudSqlStatements sql;

	AbstractOracleMantenimientoTablaParametrosAdapter(JdbcTemplate jdbc, String version) {
		this.jdbc = jdbc;
		this.sql = new VersionedCrudSqlStatements("hr", "oracle", "maestros", "mantenimientoparametros",
				"parametros", version);
	}

	public boolean existsById(ParametroId id) {
		Number count = jdbc.queryForObject(sql.existsById(), Number.class, id.compania(), id.codigo());
		return count != null && count.intValue() > 0;
	}

	public Optional<Parametro> findById(ParametroId id) {
		return jdbc.query(sql.findById(), (rs, rowNum) -> OracleParametroJdbcSupport.map(rs), id.compania(),
				id.codigo()).stream().findFirst();
	}

	public List<Parametro> findAll() {
		return jdbc.query(sql.findAll(), (rs, rowNum) -> OracleParametroJdbcSupport.map(rs));
	}

	public void save(Parametro parametro) {
		if (existsById(parametro.id())) {
			jdbc.update(sql.update(), parametro.nombre(), OracleParametroJdbcSupport.estado(parametro),
					parametro.precio(), parametro.cantidad(),
					OracleParametroJdbcSupport.toTimestamp(parametro.fechaProceso()), parametro.ultimoUsuario(),
					OracleParametroJdbcSupport.toTimestamp(parametro.ultimaFechaModif()), parametro.id().compania(),
					parametro.id().codigo());
			return;
		}
		jdbc.update(sql.insert(), parametro.id().compania(), parametro.id().codigo(), parametro.nombre(),
				OracleParametroJdbcSupport.estado(parametro), parametro.precio(), parametro.cantidad(),
				OracleParametroJdbcSupport.toTimestamp(parametro.fechaProceso()), parametro.ultimoUsuario(),
				OracleParametroJdbcSupport.toTimestamp(parametro.ultimaFechaModif()));
	}

	public void deleteById(ParametroId id) {
		jdbc.update(sql.deleteById(), id.compania(), id.codigo());
	}
}
