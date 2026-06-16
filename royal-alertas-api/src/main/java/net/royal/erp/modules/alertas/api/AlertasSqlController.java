package net.royal.erp.modules.alertas.api;

import jakarta.servlet.http.HttpServletRequest;
import net.royal.erp.modules.alertas.application.dto.*;
import net.royal.erp.modules.alertas.application.usecase.EjecutarNamedQueryUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

/**
 * Implementa: - ASIS-024 SQL HBM XML. - MOD-012 Migración funcional de queries.
 *
 * Propósito: API controlada para ejecutar named queries legacy migradas.
 */
@RestController
@RequestMapping("/api/alertas/sql")
public class AlertasSqlController {
	private final EjecutarNamedQueryUseCase useCase;
	private final AlertasContextFactory contextFactory;

	public AlertasSqlController(EjecutarNamedQueryUseCase useCase, AlertasContextFactory contextFactory) {
		this.useCase = useCase;
		this.contextFactory = contextFactory;
	}

	/** Implementa ASIS-024. Ejecuta named query. */
	@PostMapping("/{queryName}")
	public ResponseEntity<AlertasListResult> query(@PathVariable String queryName,
			@RequestBody(required = false) Map<String, Object> parameters, HttpServletRequest request) {
		return ResponseEntity.ok(useCase.execute(
				new NamedQueryCommand(queryName, parameters == null ? Map.of() : parameters),
				contextFactory.from(request, "SQL Legacy", "Ejecutar Named Query", "EjecutarNamedQueryUseCase")));
	}
}
