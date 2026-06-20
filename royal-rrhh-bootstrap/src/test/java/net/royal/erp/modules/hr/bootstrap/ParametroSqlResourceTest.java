package net.royal.erp.modules.hr.bootstrap;

import net.royal.erp.modules.hr.infrastructure.maestros.parametro.sqlserver.*;
import net.royal.erp.modules.hr.infrastructure.maestros.parametro.oracle.*;
import net.royal.erp.modules.hr.infrastructure.maestros.tiposeguro.oracle.OracleMantenimientoTipoSeguroV1Adapter;
import net.royal.erp.modules.hr.infrastructure.maestros.tiposeguro.sqlserver.SqlServerMantenimientoTipoSeguroV1Adapter;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ParametroSqlResourceTest {
	@Test
	void cargaSqlVersionadoPorCasoDeUso() {
		JdbcTemplate jdbc = new JdbcTemplate();
		assertDoesNotThrow(() -> new SqlServerMantenimientoTablaParametrosV1Adapter(jdbc));
		assertDoesNotThrow(() -> new SqlServerMantenimientoTablaParametrosV2Adapter(jdbc));
		assertDoesNotThrow(() -> new SqlServerReporteParametrosV1Adapter(jdbc));
		assertDoesNotThrow(() -> new SqlServerAprobacionMasivaParametrosV1Adapter(jdbc));
		assertDoesNotThrow(() -> new OracleMantenimientoTablaParametrosV1Adapter(jdbc));
		assertDoesNotThrow(() -> new OracleMantenimientoTablaParametrosV2Adapter(jdbc));
		assertDoesNotThrow(() -> new OracleReporteParametrosV1Adapter(jdbc));
		assertDoesNotThrow(() -> new OracleAprobacionMasivaParametrosV1Adapter(jdbc));
		assertDoesNotThrow(() -> new SqlServerMantenimientoTipoSeguroV1Adapter(jdbc));
		assertDoesNotThrow(() -> new OracleMantenimientoTipoSeguroV1Adapter(jdbc));
	}
}
