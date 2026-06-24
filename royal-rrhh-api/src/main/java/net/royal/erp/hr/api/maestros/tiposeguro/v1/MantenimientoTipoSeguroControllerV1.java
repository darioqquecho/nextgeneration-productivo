package net.royal.erp.hr.api.maestros.tiposeguro.v1;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import net.royal.erp.framework.web.FunctionalContextFactory;
import net.royal.erp.framework.web.RoyalBaseController;
import net.royal.erp.hr.application.maestros.tiposeguro.dto.*;
import net.royal.erp.hr.application.maestros.tiposeguro.usecase.MantenimientoTipoSeguroV1UseCase;
import net.royal.erp.hr.application.process.RrhhProcessCatalog;
import net.royal.erp.hr.application.process.RrhhUseCaseCatalog;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/api/v1/hr/maestros/tipos-seguro")
public class MantenimientoTipoSeguroControllerV1 extends RoyalBaseController {
	private final MantenimientoTipoSeguroV1UseCase mantenimiento;

	public MantenimientoTipoSeguroControllerV1(MantenimientoTipoSeguroV1UseCase mantenimiento,
			FunctionalContextFactory contextFactory) {
		super(contextFactory, RrhhProcessCatalog.MODULE, RrhhProcessCatalog.MAESTROS.code(),
				RrhhUseCaseCatalog.TIPOSEGURO_MANTENIMIENTO.code());
		this.mantenimiento = mantenimiento;
	}

	@PostMapping
	public ResponseEntity<CrearTipoSeguroResult> crear(@Valid @RequestBody CrearTipoSeguroCommand command,
			HttpServletRequest httpRequest) {
		return ResponseEntity.ok(mantenimiento.crear(command, context(httpRequest, "Registrar")));
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ActualizarTipoSeguroResult> actualizar(@Valid @RequestBody ActualizarTipoSeguroCommand command,
			HttpServletRequest httpRequest) {
		return ResponseEntity.ok(mantenimiento.actualizar(command, context(httpRequest, "Actualizar")));
	}

	@PostMapping(value = "/obtener", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TipoSeguroResult> obtener(@Valid @RequestBody ObtenerTipoSeguroQuery query,
			HttpServletRequest request) {
		return ResponseEntity.ok(mantenimiento.obtener(query, context(request, "Obtener")));
	}

	@PostMapping(value = "/listar", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ListarTiposSeguroResult> listar(
			@Valid @RequestBody(required = false) ListarTiposSeguroQuery query, HttpServletRequest httpRequest) {
		return ResponseEntity.ok(mantenimiento.listar(query, context(httpRequest, "Listar")));
	}

	@PatchMapping(value = "/estado", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TipoSeguroResult> cambiarEstado(@Valid @RequestBody CambiarEstadoTipoSeguroCommand command,
			HttpServletRequest httpRequest) {
		return ResponseEntity.ok(mantenimiento.cambiarEstado(command, context(httpRequest, "Cambiar de Estado")));
	}

	@PostMapping(value = "/eliminar", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EliminarTipoSeguroResult> eliminar(@Valid @RequestBody EliminarTipoSeguroCommand command,
			HttpServletRequest request) {
		return ResponseEntity.ok(mantenimiento.eliminar(command, context(request, "Eliminar")));
	}
}
