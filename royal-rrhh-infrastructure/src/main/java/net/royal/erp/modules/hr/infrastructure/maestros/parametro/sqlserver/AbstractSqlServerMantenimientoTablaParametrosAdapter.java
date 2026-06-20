package net.royal.erp.modules.hr.infrastructure.maestros.parametro.sqlserver;

import java.util.*;

import net.royal.erp.framework.database.RoyalBaseSqlAdapter;
import net.royal.erp.framework.database.VersionedCrudSqlStatements;
import net.royal.erp.modules.hr.application.maestros.parametro.port.MantenimientoTablaParametrosRepository;
import net.royal.erp.modules.hr.domain.parametro.*;
import org.springframework.jdbc.core.JdbcTemplate;

abstract class AbstractSqlServerMantenimientoTablaParametrosAdapter extends RoyalBaseSqlAdapter
		implements MantenimientoTablaParametrosRepository {
	private final VersionedCrudSqlStatements sql;

	AbstractSqlServerMantenimientoTablaParametrosAdapter(JdbcTemplate jdbc, String version) {
		super(jdbc);
		this.sql = new VersionedCrudSqlStatements("hr", "maestros", "mantenimientoparametros", "parametros", version);
	}

	public boolean existsById(ParametroId id) {
		Integer count = jdbc().queryForObject(sql.existsById(), Integer.class, id.compania(), id.codigo());
		return count != null && count > 0;
	}

	public Optional<Parametro> findById(ParametroId id) {
		return jdbc().query(sql.findById(), (rs, rowNum) -> SqlServerParametroJdbcSupport.map(rs), id.compania(),
				id.codigo()).stream().findFirst();
	}

	public List<Parametro> findAll() {
		return jdbc().query(sql.findAll(), (rs, rowNum) -> SqlServerParametroJdbcSupport.map(rs));
	}

	public void save(Parametro parametro) {
		if (existsById(parametro.id())) {
			jdbc().update(sql.update(), parametro.nombre(), SqlServerParametroJdbcSupport.estado(parametro),
					parametro.precio(), parametro.cantidad(),
					SqlServerParametroJdbcSupport.toTimestamp(parametro.fechaProceso()), parametro.ultimoUsuario(),
					SqlServerParametroJdbcSupport.toTimestamp(parametro.ultimaFechaModif()), parametro.id().compania(),
					parametro.id().codigo());
			return;
		}
		jdbc().update(sql.insert(), parametro.id().compania(), parametro.id().codigo(), parametro.nombre(),
				SqlServerParametroJdbcSupport.estado(parametro), parametro.precio(), parametro.cantidad(),
				SqlServerParametroJdbcSupport.toTimestamp(parametro.fechaProceso()), parametro.ultimoUsuario(),
				SqlServerParametroJdbcSupport.toTimestamp(parametro.ultimaFechaModif()));
	}

	public void deleteById(ParametroId id) {
		jdbc().update(sql.deleteById(), id.compania(), id.codigo());
	}
}
