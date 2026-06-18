package net.royal.erp.modules.rrhh.api;

import jakarta.servlet.http.HttpServletRequest;
import net.royal.erp.framework.kernel.*;
import java.time.Instant;

/**
 * Implementa: - ARCH-015 Auditoría. - ARCH-016 Observabilidad. - ASIS-033
 * Catálogo funcional.
 */
public class FunctionalContextFactory {
	public FunctionalContext from(HttpServletRequest request, String process, String functionality, String useCase) {
		return new FunctionalContext(header(request, "X-Tenant-Id", "default"),
				header(request, "X-Client-Id", "demo-client"), header(request, "X-User-Id", "admin"), "HR", process,
				functionality, useCase, header(request, "X-Functional-Version", null),
				header(request, "X-Trace-Id", null), header(request, "X-Request-Id", null), Instant.now(),
				RequestLanguage.fromHeaders(header(request, "X-Language", null),
						header(request, "Accept-Language", null)));
	}

	/**
	 * Lee header HTTP con valor por defecto.
	 *
	 * Implementa: - ARCH-016 Observabilidad.
	 */
	private String header(HttpServletRequest request, String name, String defaultValue) {
		String value = request.getHeader(name);
		return value == null || value.isBlank() ? defaultValue : value;
	}
}
