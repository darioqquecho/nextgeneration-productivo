package net.royal.erp.hr.api.requerimiento.aprobar.v1;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import net.royal.erp.framework.web.FunctionalContextFactory;
import net.royal.erp.framework.web.RoyalBaseController;
import net.royal.erp.hr.application.process.RrhhProcessCatalog;
import net.royal.erp.hr.application.process.RrhhUseCaseCatalog;
import net.royal.erp.hr.application.requerimiento.aprobar.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Implementa: - MOD-012 CU-003 AprobarRequerimientoPersonal.
 */
@RestController
@Validated
@RequestMapping("/api/v1/hr/requerimiento/requerimientos")
public class AprobarRequerimientoPersonalControllerV1 extends RoyalBaseController {
	private final AprobarRequerimientoPersonalV1UseCase useCase;

	public AprobarRequerimientoPersonalControllerV1(AprobarRequerimientoPersonalV1UseCase useCase,
			FunctionalContextFactory contextFactory) {
		super(contextFactory, RrhhProcessCatalog.MODULE,
				RrhhProcessCatalog.REQUERIMIENTO.code() ,
				RrhhUseCaseCatalog.REQUERIMIENTO_APROBAR.code());
		this.useCase = useCase;
	}

	@PostMapping("/{codigo}/aprobaciones")
	public ResponseEntity<AprobarRequerimientoPersonalResult> aprobar(
			@PathVariable @NotBlank @Size(max = 20) @Pattern(regexp = "^[A-Za-z0-9_.-]+$") String codigo,
			@Valid @RequestBody AprobarRequerimientoPersonalRequestV1 body, HttpServletRequest request) {
		return ResponseEntity.ok(useCase.execute(new AprobarRequerimientoPersonalCommand(codigo, body.accion(),
				body.comentario(), body.usuarioAprobador()), context(request, "Aprobar")));
	}

	private record AprobarRequerimientoPersonalRequestV1(
			@NotBlank @Size(max = 20) @Pattern(regexp = "^[A-Za-z_]+$") String accion,
			@Size(max = 500) String comentario,
			@NotBlank @Size(max = 20) @Pattern(regexp = "^[A-Za-z0-9_.-]+$") String usuarioAprobador) {
	}
}
