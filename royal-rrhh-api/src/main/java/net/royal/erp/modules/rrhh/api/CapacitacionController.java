package net.royal.erp.modules.rrhh.api;

import jakarta.servlet.http.HttpServletRequest;
import net.royal.erp.modules.rrhh.application.capacitacion.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Implementa: - MOD-012 CU-002 RegistrarCapacitacion.
 */
@RestController
@RequestMapping("/api/rrhh/capacitaciones")
public class CapacitacionController {
	private final RegistrarCapacitacionUseCase useCase;
	private final FunctionalContextFactory contextFactory;

	public CapacitacionController(RegistrarCapacitacionUseCase useCase, FunctionalContextFactory contextFactory) {
		this.useCase = useCase;
		this.contextFactory = contextFactory;
	}

	@PostMapping
	public ResponseEntity<RegistrarCapacitacionResult> registrar(@RequestBody RegistrarCapacitacionCommand command,
			HttpServletRequest request) {
		return ResponseEntity.ok(useCase.execute(command, contextFactory.from(request, "Capacitación",
				"Registrar Capacitación", "RegistrarCapacitacionUseCase")));
	}
}
