package net.royal.erp.framework.web;

import java.time.Instant;

import jakarta.servlet.http.HttpServletRequest;
import net.royal.erp.framework.kernel.*;
import org.springframework.stereotype.Component;

/**
 * Crea el contexto funcional estandar a partir de headers HTTP.
 */
@Component
public class FunctionalContextFactory {
	public FunctionalContext from(HttpServletRequest request, String module, String process, String functionality,
			String useCase) {
		return new FunctionalContext(header(request, "X-Tenant-Id", "default"),
				header(request, "X-Client-Id", "demo-client"), header(request, "X-User-Id", "admin"), module, process,
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
