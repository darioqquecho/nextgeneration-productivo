package net.royal.erp.hr.api.maestros.parametro.mantenimiento.v1;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import net.royal.erp.framework.web.FunctionalContextFactory;
import net.royal.erp.framework.web.RoyalBaseController;
import net.royal.erp.hr.application.maestros.parametro.mantenimiento.dto.*;
import net.royal.erp.hr.application.maestros.parametro.mantenimiento.usecase.MantenimientoTablaParametrosV1UseCase;
import net.royal.erp.hr.application.process.RrhhProcessCatalog;
import net.royal.erp.hr.application.process.RrhhUseCaseCatalog;
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
		super(contextFactory, RrhhProcessCatalog.MODULE, RrhhProcessCatalog.MAESTROS.code(),
				RrhhUseCaseCatalog.PARAMETRO_MANTENIMIENTO.code());
		this.mantenimiento = mantenimiento;
	}

	@PostMapping
	public ResponseEntity<CrearParametroResult> crear(@Valid @RequestBody CrearParametroCommand command,
			HttpServletRequest httpRequest) {
		return ResponseEntity.ok(mantenimiento.crear(command, context(httpRequest, "Registrar")));
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ActualizarParametroResult> actualizar(@Valid @RequestBody ActualizarParametroCommand command,
			HttpServletRequest httpRequest) {
		return ResponseEntity.ok(mantenimiento.actualizar(command, context(httpRequest, "Actualizar")));
	}

	@PostMapping(value = "/obtener", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ParametroResult> obtener(@Valid @RequestBody ObtenerParametroQuery query,
			HttpServletRequest request) {
		return ResponseEntity.ok(mantenimiento.obtener(query, context(request, "Obtener")));
	}

	@PostMapping(value = "/listar", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ListarParametrosResult> listar(
			@Valid @RequestBody(required = false) ListarParametrosQuery query, HttpServletRequest httpRequest) {
		return ResponseEntity.ok(mantenimiento.listar(query, context(httpRequest, "Listar")));
	}

	@PatchMapping(value = "/estado", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ParametroResult> cambiarEstado(@Valid @RequestBody CambiarEstadoParametroCommand command,
			HttpServletRequest httpRequest) {
		return ResponseEntity.ok(mantenimiento.cambiarEstado(command, context(httpRequest, "Cambiar de Estado")));
	}

	@PostMapping(value = "/eliminar", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EliminarParametroResult> eliminar(@Valid @RequestBody EliminarParametroCommand command,
			HttpServletRequest request) {
		return ResponseEntity.ok(mantenimiento.eliminar(command, context(request, "Eliminar")));
	}
}
