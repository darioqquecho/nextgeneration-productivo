package net.royal.erp.modules.alertas.api;

import jakarta.servlet.http.HttpServletRequest;
import net.royal.erp.framework.kernel.*;
import java.time.Instant;

/**
 * Implementa: - ARCH-015 Auditoría. - ARCH-016 Observabilidad. - ASIS-033
 * Catálogo funcional estandarizado.
 *
 * Propósito: Construye FunctionalContext para endpoints nuevos y legacy del
 * módulo Alertas.
 */
public class AlertasContextFactory {
	/** Implementa ARCH-016. Construye contexto desde HTTP. */
	public FunctionalContext from(HttpServletRequest request, String process, String functionality, String useCase) {
		return new FunctionalContext(header(request, "X-Tenant-Id", "default"),
				header(request, "X-Client-Id", "demo-client"), header(request, "X-User-Id", "admin"), "ALERTAS",
				process, functionality, useCase, header(request, "X-Functional-Version", null),
				header(request, "X-Trace-Id", null), header(request, "X-Request-Id", null), Instant.now(),
				RequestLanguage.fromHeaders(header(request, "X-Language", null),
						header(request, "Accept-Language", null)));
	}

	private String header(HttpServletRequest request, String name, String defaultValue) {
		String value = request.getHeader(name);
		return value == null || value.isBlank() ? defaultValue : value;
	}
}
