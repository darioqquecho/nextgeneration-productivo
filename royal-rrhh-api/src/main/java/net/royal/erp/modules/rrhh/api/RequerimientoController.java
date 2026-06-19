package net.royal.erp.modules.rrhh.api;

import jakarta.servlet.http.HttpServletRequest;
import net.royal.erp.modules.rrhh.application.requerimiento.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Implementa: - MOD-012 CU-003 AprobarRequerimientoPersonal.
 */
@RestController
@RequestMapping("/api/v1/hr/requerimiento/requerimientos")
public class RequerimientoController {
	private final AprobarRequerimientoPersonalUseCase useCase;
	private final FunctionalContextFactory contextFactory;

	public RequerimientoController(AprobarRequerimientoPersonalUseCase useCase,
			FunctionalContextFactory contextFactory) {
		this.useCase = useCase;
		this.contextFactory = contextFactory;
	}

	@PostMapping("/{codigo}/aprobaciones")
	public ResponseEntity<AprobarRequerimientoPersonalResult> aprobar(@PathVariable String codigo,
			@RequestBody AprobarRequerimientoPersonalCommand body, HttpServletRequest request) {
		return ResponseEntity.ok(useCase.execute(
				new AprobarRequerimientoPersonalCommand(codigo, body.accion(), body.comentario(),
						body.usuarioAprobador()),
				contextFactory.from(request, "Requerimiento", "Aprobar", "Aprobar Requerimiento Personal")));
	}
}
