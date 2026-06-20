package net.royal.erp.modules.hr.infrastructure.maestros.tiposeguro.sqlserver;

import java.util.*;

import net.royal.erp.framework.database.VersionedCrudSqlStatements;
import net.royal.erp.modules.hr.application.maestros.tiposeguro.port.MantenimientoTipoSeguroRepository;
import net.royal.erp.modules.hr.domain.tiposeguro.*;
import org.springframework.jdbc.core.JdbcTemplate;

public class SqlServerMantenimientoTipoSeguroV1Adapter implements MantenimientoTipoSeguroRepository {
	private final JdbcTemplate jdbc;
	private final VersionedCrudSqlStatements sql = new VersionedCrudSqlStatements("hr", "maestros",
			"mantenimientotiposeguro", "tiposeguro", "v1");

	public SqlServerMantenimientoTipoSeguroV1Adapter(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	public boolean existsById(TipoSeguroId id) {
		Integer count = jdbc.queryForObject(sql.existsById(), Integer.class, id.value());
		return count != null && count > 0;
	}

	public Optional<TipoSeguro> findById(TipoSeguroId id) {
		return jdbc.query(sql.findById(), (rs, rowNum) -> SqlServerTipoSeguroJdbcSupport.map(rs), id.value()).stream()
				.findFirst();
	}

	public List<TipoSeguro> findAll() {
		return jdbc.query(sql.findAll(), (rs, rowNum) -> SqlServerTipoSeguroJdbcSupport.map(rs));
	}

	public void save(TipoSeguro tipoSeguro) {
		if (existsById(tipoSeguro.id())) {
			jdbc.update(sql.update(), tipoSeguro.descripcion(), tipoSeguro.estado(), tipoSeguro.ultimoUsuario(),
					SqlServerTipoSeguroJdbcSupport.toTimestamp(tipoSeguro.ultimaFechaModif()), tipoSeguro.id().value());
			return;
		}
		jdbc.update(sql.insert(), tipoSeguro.id().value(), tipoSeguro.descripcion(), tipoSeguro.estado(),
				tipoSeguro.ultimoUsuario(), SqlServerTipoSeguroJdbcSupport.toTimestamp(tipoSeguro.ultimaFechaModif()));
	}

	public void deleteById(TipoSeguroId id) {
		jdbc.update(sql.deleteById(), id.value());
	}
}
