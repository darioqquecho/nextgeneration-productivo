package net.royal.erp.modules.hr.api.v1.capacitacion.registrar;

import java.time.LocalDate;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import net.royal.erp.framework.web.FunctionalContextFactory;
import net.royal.erp.framework.web.RoyalBaseController;
import net.royal.erp.modules.hr.application.capacitacion.registrar.*;
import net.royal.erp.modules.hr.application.process.RrhhProcessCatalog;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Implementa: - MOD-012 CU-002 RegistrarCapacitacion.
 */
@RestController
@RequestMapping("/api/v1/hr/capacitacion/capacitaciones")
public class RegistrarCapacitacionControllerV1 extends RoyalBaseController {
	private final RegistrarCapacitacionUseCase useCase;

	public RegistrarCapacitacionControllerV1(RegistrarCapacitacionUseCase useCase,
			FunctionalContextFactory contextFactory) {
		super(contextFactory, RrhhProcessCatalog.MODULE, RrhhProcessCatalog.REGISTRAR_CAPACITACION.processName(),
				RrhhProcessCatalog.REGISTRAR_CAPACITACION.displayName());
		this.useCase = useCase;
	}

	@PostMapping
	public ResponseEntity<RegistrarCapacitacionResult> registrar(@Valid @RequestBody RegistrarCapacitacionRequestV1 body,
			HttpServletRequest request) {
		return ResponseEntity.ok(useCase.execute(
				new RegistrarCapacitacionCommand(body.codigo(), body.nombre(), body.fechaInicio(), body.fechaFin(),
						body.instructor()),
				context(request, "Registrar")));
	}

	private record RegistrarCapacitacionRequestV1(
			@NotBlank @Size(max = 20) @Pattern(regexp = "^[A-Za-z0-9_.-]+$") String codigo,
			@NotBlank @Size(max = 100) String nombre, @NotNull LocalDate fechaInicio, @NotNull LocalDate fechaFin,
			@Size(max = 100) String instructor) {
	}
}
