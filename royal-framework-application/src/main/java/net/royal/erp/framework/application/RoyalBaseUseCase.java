package net.royal.erp.framework.application;

import net.royal.erp.framework.audit.AuditPort;
import net.royal.erp.framework.audit.FunctionalAuditRecord;
import net.royal.erp.framework.kernel.BusinessException;
import net.royal.erp.framework.kernel.FrameworkBusinessErrorCodes;
import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.security.UseCaseGuards;

/**
 * Clase maestra para casos de uso funcionales.
 */
public abstract class RoyalBaseUseCase {
	private final String module;
	private final String entity;
	private final String version;
	private final UseCaseGuards guards;
	private final AuditPort auditPort;

	protected RoyalBaseUseCase(String module, String entity, String version, UseCaseGuards guards,
			AuditPort auditPort) {
		this.module = module;
		this.entity = entity;
		this.version = version;
		this.guards = guards;
		this.auditPort = auditPort;
	}

	protected String module() {
		return module;
	}

	protected String entity() {
		return entity;
	}

	protected String functionalVersion() {
		return version;
	}

	protected void checkGuards(FunctionalContext context) {
		guards.check(context);
	}

	protected void deny(String code, String detail) {
		throw new BusinessException(code, detail);
	}

	protected void requireNonBlank(String value, String field) {
		if (value == null || value.isBlank()) {
			throw new BusinessException(FrameworkBusinessErrorCodes.VALIDATION_REQUIRED, field);
		}
	}

	protected void auditOk(FunctionalContext context, String entityId) {
		auditOk(context, entityId, null);
	}

	protected void auditOk(FunctionalContext context, String entityId, String message) {
		audit(context, "OK", entityId, message);
	}

	protected void audit(FunctionalContext context, String result, String entityId, String message) {
		auditPort.register(new FunctionalAuditRecord(context.tenantId(), context.clientId(), module, context.process(),
				context.useCase(), context.functionality(), version, context.userId(), result, entity, entityId,
				context.traceId(), context.requestId(), context.executedAt(), context.language(), message));
	}
}
