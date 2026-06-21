package net.royal.erp.hr.infrastructure.maestros.tiposeguro.oracle;

import java.util.*;

import net.royal.erp.framework.database.VersionedCrudSqlStatements;
import net.royal.erp.hr.application.maestros.tiposeguro.port.MantenimientoTipoSeguroRepository;
import net.royal.erp.hr.domain.tiposeguro.*;
import org.springframework.jdbc.core.JdbcTemplate;

public class OracleMantenimientoTipoSeguroV1Adapter implements MantenimientoTipoSeguroRepository {
	private final JdbcTemplate jdbc;
	private final VersionedCrudSqlStatements sql = new VersionedCrudSqlStatements("hr", "oracle", "maestros",
			"mantenimientotiposeguro", "tiposeguro", "v1");

	public OracleMantenimientoTipoSeguroV1Adapter(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	public boolean existsById(TipoSeguroId id) {
		Number count = jdbc.queryForObject(sql.existsById(), Number.class, id.value());
		return count != null && count.intValue() > 0;
	}

	public Optional<TipoSeguro> findById(TipoSeguroId id) {
		return jdbc.query(sql.findById(), (rs, rowNum) -> OracleTipoSeguroJdbcSupport.map(rs), id.value()).stream()
				.findFirst();
	}

	public List<TipoSeguro> findAll() {
		return jdbc.query(sql.findAll(), (rs, rowNum) -> OracleTipoSeguroJdbcSupport.map(rs));
	}

	public void save(TipoSeguro tipoSeguro) {
		if (existsById(tipoSeguro.id())) {
			jdbc.update(sql.update(), tipoSeguro.descripcion(), tipoSeguro.estado(), tipoSeguro.ultimoUsuario(),
					OracleTipoSeguroJdbcSupport.toTimestamp(tipoSeguro.ultimaFechaModif()), tipoSeguro.id().value());
			return;
		}
		jdbc.update(sql.insert(), tipoSeguro.id().value(), tipoSeguro.descripcion(), tipoSeguro.estado(),
				tipoSeguro.ultimoUsuario(), OracleTipoSeguroJdbcSupport.toTimestamp(tipoSeguro.ultimaFechaModif()));
	}

	public void deleteById(TipoSeguroId id) {
		jdbc.update(sql.deleteById(), id.value());
	}
}
