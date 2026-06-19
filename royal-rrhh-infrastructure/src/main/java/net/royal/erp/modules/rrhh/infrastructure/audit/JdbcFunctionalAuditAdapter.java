package net.royal.erp.modules.rrhh.infrastructure.audit;

import java.sql.Timestamp;

import org.springframework.jdbc.core.JdbcTemplate;

import net.royal.erp.framework.audit.*;

/**
 * Persiste eventos de auditoria funcional en la base transaccional.
 */
public class JdbcFunctionalAuditAdapter implements AuditPort {
	private final JdbcTemplate jdbc;

	public JdbcFunctionalAuditAdapter(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
		ensureSchema();
	}

	@Override
	public void register(FunctionalAuditRecord record) {
		jdbc.update("""
				INSERT INTO AUDITORIA_FUNCIONAL (
					FECHA_EJECUCION, TENANT_ID, CLIENT_ID, USER_ID, MODULO, PROCESO,
					FUNCIONALIDAD, CASO_USO, VERSION, TRACE_ID, REQUEST_ID, RESULTADO,
					ENTIDAD, ENTIDAD_ID, LENGUAJE, MENSAJE
				) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
				""", Timestamp.from(record.executedAt()), record.tenantId(), record.clientId(), record.userId(),
				record.module(), record.process(), record.functionality(), record.useCase(), record.version(),
				record.traceId(), record.requestId(), record.result(), record.entity(), record.entityId(),
				record.language(), record.message());
	}

	private void ensureSchema() {
		jdbc.execute("""
				IF OBJECT_ID('dbo.AUDITORIA_FUNCIONAL', 'U') IS NULL
				CREATE TABLE dbo.AUDITORIA_FUNCIONAL (
					ID BIGINT IDENTITY(1,1) NOT NULL PRIMARY KEY,
					FECHA_EJECUCION DATETIME2 NOT NULL,
					TENANT_ID VARCHAR(100) NULL,
					CLIENT_ID VARCHAR(100) NULL,
					USER_ID VARCHAR(100) NOT NULL,
					MODULO VARCHAR(50) NOT NULL,
					PROCESO VARCHAR(100) NULL,
					FUNCIONALIDAD VARCHAR(100) NULL,
					CASO_USO VARCHAR(150) NOT NULL,
					VERSION VARCHAR(20) NULL,
					TRACE_ID VARCHAR(100) NULL,
					REQUEST_ID VARCHAR(100) NULL,
					RESULTADO VARCHAR(30) NOT NULL,
					ENTIDAD VARCHAR(100) NULL,
					ENTIDAD_ID VARCHAR(100) NULL,
					LENGUAJE VARCHAR(20) NULL,
					MENSAJE VARCHAR(500) NULL
				)
				""");
	}
}
