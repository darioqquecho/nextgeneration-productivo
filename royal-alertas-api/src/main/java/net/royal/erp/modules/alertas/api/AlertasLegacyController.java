package net.royal.erp.modules.alertas.api;

import jakarta.servlet.http.HttpServletRequest;
import net.royal.erp.modules.alertas.application.dto.LegacyEndpointCommand;
import net.royal.erp.modules.alertas.application.usecase.EjecutarLegacyEndpointUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

/**
 * Implementa: - MOD-012 Migración funcional de endpoints legacy. - ASIS-020
 * Compatibilidad con frontend actual.
 *
 * Propósito: Controlador de compatibilidad para rutas /spring/alertas/** del
 * módulo original.
 */
@RestController
@RequestMapping("/spring/alertas")
public class AlertasLegacyController {
	private final EjecutarLegacyEndpointUseCase useCase;
	private final AlertasContextFactory contextFactory;

	public AlertasLegacyController(EjecutarLegacyEndpointUseCase useCase, AlertasContextFactory contextFactory) {
		this.useCase = useCase;
		this.contextFactory = contextFactory;
	}

	/** Implementa MOD-012. Ejecuta cualquier endpoint legacy migrado. */
	@RequestMapping(value = "/{resource}/**", method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
			RequestMethod.DELETE, RequestMethod.PATCH })
	public ResponseEntity<Object> legacy(@PathVariable String resource,
			@RequestBody(required = false) Map<String, Object> body, HttpServletRequest request) {
		String uri = request.getRequestURI();
		String suffix = uri.substring((request.getContextPath() + "/spring/alertas/" + resource).length());
		String action = suffix.replaceFirst("^/", "");
		if (action.contains("/"))
			action = action.substring(0, action.indexOf('/'));
		Object result = useCase.execute(
				new LegacyEndpointCommand(request.getMethod(), resource, action, action,
						body == null ? Map.of() : body),
				contextFactory.from(request, "Legacy", "Endpoint Legacy", "EjecutarLegacyEndpointUseCase"));
		return ResponseEntity.ok(result);
	}
}
