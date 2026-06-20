package net.royal.erp.framework.web;

import java.time.Instant;

import jakarta.servlet.http.HttpServletRequest;
import net.royal.erp.framework.kernel.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Crea el contexto funcional estandar a partir de headers HTTP.
 */
@Component
public class FunctionalContextFactory {
	private final String defaultTenantId;
	private final String defaultClientId;
	private final String defaultUserId;

	public FunctionalContextFactory(@Value("${royal.context.default-tenant:default}") String defaultTenantId,
			@Value("${royal.context.default-client:demo-client}") String defaultClientId,
			@Value("${royal.context.default-user:admin}") String defaultUserId) {
		this.defaultTenantId = defaultTenantId;
		this.defaultClientId = defaultClientId;
		this.defaultUserId = defaultUserId;
	}

	public FunctionalContext from(HttpServletRequest request, String module, String process, String functionality,
			String useCase) {
		return new FunctionalContext(header(request, "X-Tenant-Id", defaultTenantId),
				header(request, "X-Client-Id", defaultClientId), header(request, "X-User-Id", defaultUserId), module, process,
				functionality, useCase, header(request, "X-Functional-Version", null),
				header(request, "X-Trace-Id", null), header(request, "X-Request-Id", null), Instant.now(),
				RequestLanguage.fromHeaders(header(request, "X-Language", null),
						header(request, "Accept-Language", null)));
	}

	private String header(HttpServletRequest request, String name, String defaultValue) {
		String value = request.getHeader(name);
		return value == null || value.isBlank() ? defaultValue : value;
	}
}
