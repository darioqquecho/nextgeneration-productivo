package net.royal.erp.modules.hr.bootstrap;

import net.royal.erp.modules.hr.infrastructure.parametro.*;
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
	}
}
