package net.royal.erp.modules.rrhh.api.v1.parametro;

import jakarta.servlet.http.HttpServletRequest;
import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.modules.rrhh.api.FunctionalContextFactory;
import net.royal.erp.modules.rrhh.api.v1.parametro.dto.*;
import net.royal.erp.modules.rrhh.application.parametro.dto.*;
import net.royal.erp.modules.rrhh.application.parametro.usecase.MantenimientoTablaParametrosUseCase;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 * API v1 para el caso de uso Mantenimiento de Parametro.
 */
@RestController
@RequestMapping("/api/v1/hr/maestros/parametros")
public class MantenimientoParametroControllerV1 {
	private static final String PROCESS = "Maestros";
	private static final String USE_CASE = "Mantenimiento de Parametro";

	private final MantenimientoTablaParametrosUseCase mantenimiento;
	private final FunctionalContextFactory contextFactory;

	public MantenimientoParametroControllerV1(MantenimientoTablaParametrosUseCase mantenimiento,
			FunctionalContextFactory contextFactory) {
		this.mantenimiento = mantenimiento;
		this.contextFactory = contextFactory;
	}

	@PostMapping
	public ResponseEntity<CrearParametroResponseV1> crear(@RequestBody CrearParametroRequestV1 request,
			HttpServletRequest httpRequest) {
		return ResponseEntity.ok(ParametroApiV1Mapper.toResponse(
				mantenimiento.crear(ParametroApiV1Mapper.toCommand(request), context(httpRequest, "Registrar"))));
	}

	@PutMapping("/{compania}/{codigo}")
	public ResponseEntity<ActualizarParametroResponseV1> actualizar(@PathVariable String compania,
			@PathVariable String codigo, @RequestBody ActualizarParametroRequestV1 request,
			HttpServletRequest httpRequest) {
		return ResponseEntity.ok(ParametroApiV1Mapper.toResponse(mantenimiento.actualizar(
				ParametroApiV1Mapper.toCommand(compania, codigo, request), context(httpRequest, "Actualizar"))));
	}

	@GetMapping("/{compania}/{codigo}")
	public ResponseEntity<ParametroResponseV1> obtener(@PathVariable String compania, @PathVariable String codigo,
			HttpServletRequest request) {
		return ResponseEntity.ok(ParametroApiV1Mapper
				.toResponse(mantenimiento.obtener(new ObtenerParametroQuery(compania, codigo), context(request, "Obtener"))));
	}

	@PostMapping(value = "/listar", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ListarParametrosResponseV1> listar(@RequestBody(required = false) ListarParametrosRequestV1 request,
			HttpServletRequest httpRequest) {
		return ResponseEntity.ok(ParametroApiV1Mapper.toResponse(
				mantenimiento.listar(ParametroApiV1Mapper.toQuery(request), context(httpRequest, "Listar"))));
	}

	@PatchMapping("/{compania}/{codigo}/estado")
	public ResponseEntity<ParametroResponseV1> cambiarEstado(@PathVariable String compania, @PathVariable String codigo,
			@RequestBody CambiarEstadoParametroRequestV1 request, HttpServletRequest httpRequest) {
		return ResponseEntity.ok(ParametroApiV1Mapper.toResponse(mantenimiento.cambiarEstado(
				ParametroApiV1Mapper.toCommand(compania, codigo, request), context(httpRequest, "Cambiar de Estado"))));
	}

	@DeleteMapping("/{compania}/{codigo}")
	public ResponseEntity<EliminarParametroResponseV1> eliminar(@PathVariable String compania,
			@PathVariable String codigo, HttpServletRequest request) {
		return ResponseEntity.ok(ParametroApiV1Mapper
				.toResponse(mantenimiento.eliminar(new EliminarParametroCommand(compania, codigo), context(request, "Eliminar"))));
	}

	private FunctionalContext context(HttpServletRequest request, String functionality) {
		return contextFactory.from(request, PROCESS, functionality, USE_CASE);
	}
}
