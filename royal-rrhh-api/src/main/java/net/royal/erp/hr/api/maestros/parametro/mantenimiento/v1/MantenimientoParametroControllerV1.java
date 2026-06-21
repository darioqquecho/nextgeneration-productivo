package net.royal.erp.hr.api.maestros.parametro.mantenimiento.v1;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import net.royal.erp.framework.web.RoyalBaseController;
import net.royal.erp.framework.web.FunctionalContextFactory;
import net.royal.erp.hr.api.maestros.parametro.mantenimiento.v1.dto.*;
import net.royal.erp.hr.application.maestros.parametro.mantenimiento.dto.*;
import net.royal.erp.hr.application.maestros.parametro.mantenimiento.usecase.MantenimientoTablaParametrosV1UseCase;
import net.royal.erp.hr.application.process.RrhhProcessCatalog;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * API v1 para el caso de uso Mantenimiento de Parametro.
 */
@RestController
@Validated
@RequestMapping("/api/v1/hr/maestros/parametros")
public class MantenimientoParametroControllerV1 extends RoyalBaseController {
	private final MantenimientoTablaParametrosV1UseCase mantenimiento;

	public MantenimientoParametroControllerV1(MantenimientoTablaParametrosV1UseCase mantenimiento,
			FunctionalContextFactory contextFactory) {
		super(contextFactory, RrhhProcessCatalog.MODULE, RrhhProcessCatalog.MANTENIMIENTO_PARAMETRO.processName(),
				RrhhProcessCatalog.MANTENIMIENTO_PARAMETRO.displayName());
		this.mantenimiento = mantenimiento;
	}

	@PostMapping
	public ResponseEntity<CrearParametroResponseV1> crear(@Valid @RequestBody CrearParametroRequestV1 request,
			HttpServletRequest httpRequest) {
		return ResponseEntity.ok(ParametroMantenimientoApiV1Mapper.toResponse(
				mantenimiento.crear(ParametroMantenimientoApiV1Mapper.toCommand(request), context(httpRequest, "Registrar"))));
	}

	@PutMapping("/{compania}/{codigo}")
	public ResponseEntity<ActualizarParametroResponseV1> actualizar(
			@PathVariable @NotBlank @Size(max = 10) @Pattern(regexp = "^[A-Za-z0-9_-]+$") String compania,
			@PathVariable @NotBlank @Size(max = 20) @Pattern(regexp = "^[A-Za-z0-9_.-]+$") String codigo,
			@Valid @RequestBody ActualizarParametroRequestV1 request,
			HttpServletRequest httpRequest) {
		return ResponseEntity.ok(ParametroMantenimientoApiV1Mapper.toResponse(mantenimiento.actualizar(
				ParametroMantenimientoApiV1Mapper.toCommand(compania, codigo, request), context(httpRequest, "Actualizar"))));
	}

	@GetMapping("/{compania}/{codigo}")
	public ResponseEntity<ParametroResponseV1> obtener(
			@PathVariable @NotBlank @Size(max = 10) @Pattern(regexp = "^[A-Za-z0-9_-]+$") String compania,
			@PathVariable @NotBlank @Size(max = 20) @Pattern(regexp = "^[A-Za-z0-9_.-]+$") String codigo,
			HttpServletRequest request) {
		return ResponseEntity.ok(ParametroMantenimientoApiV1Mapper
				.toResponse(mantenimiento.obtener(new ObtenerParametroQuery(compania, codigo), context(request, "Obtener"))));
	}

	@PostMapping(value = "/listar", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ListarParametrosResponseV1> listar(
			@Valid @RequestBody(required = false) ListarParametrosRequestV1 request, HttpServletRequest httpRequest) {
		return ResponseEntity.ok(ParametroMantenimientoApiV1Mapper.toResponse(
				mantenimiento.listar(ParametroMantenimientoApiV1Mapper.toQuery(request), context(httpRequest, "Listar"))));
	}

	@PatchMapping("/{compania}/{codigo}/estado")
	public ResponseEntity<ParametroResponseV1> cambiarEstado(
			@PathVariable @NotBlank @Size(max = 10) @Pattern(regexp = "^[A-Za-z0-9_-]+$") String compania,
			@PathVariable @NotBlank @Size(max = 20) @Pattern(regexp = "^[A-Za-z0-9_.-]+$") String codigo,
			@Valid @RequestBody CambiarEstadoParametroRequestV1 request, HttpServletRequest httpRequest) {
		return ResponseEntity.ok(ParametroMantenimientoApiV1Mapper.toResponse(mantenimiento.cambiarEstado(
				ParametroMantenimientoApiV1Mapper.toCommand(compania, codigo, request), context(httpRequest, "Cambiar de Estado"))));
	}

	@DeleteMapping("/{compania}/{codigo}")
	public ResponseEntity<EliminarParametroResponseV1> eliminar(
			@PathVariable @NotBlank @Size(max = 10) @Pattern(regexp = "^[A-Za-z0-9_-]+$") String compania,
			@PathVariable @NotBlank @Size(max = 20) @Pattern(regexp = "^[A-Za-z0-9_.-]+$") String codigo,
			HttpServletRequest request) {
		return ResponseEntity.ok(ParametroMantenimientoApiV1Mapper
				.toResponse(mantenimiento.eliminar(new EliminarParametroCommand(compania, codigo), context(request, "Eliminar"))));
	}

}
