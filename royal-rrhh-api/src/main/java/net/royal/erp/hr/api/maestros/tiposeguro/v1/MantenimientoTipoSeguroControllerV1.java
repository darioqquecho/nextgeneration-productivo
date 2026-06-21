package net.royal.erp.hr.api.maestros.tiposeguro.v1;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import net.royal.erp.framework.web.FunctionalContextFactory;
import net.royal.erp.framework.web.RoyalBaseController;
import net.royal.erp.hr.api.maestros.tiposeguro.v1.dto.*;
import net.royal.erp.hr.application.maestros.tiposeguro.dto.*;
import net.royal.erp.hr.application.maestros.tiposeguro.usecase.MantenimientoTipoSeguroV1UseCase;
import net.royal.erp.hr.application.process.RrhhProcessCatalog;
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
		super(contextFactory, RrhhProcessCatalog.MODULE, RrhhProcessCatalog.MANTENIMIENTO_TIPO_SEGURO.processName(),
				RrhhProcessCatalog.MANTENIMIENTO_TIPO_SEGURO.displayName());
		this.mantenimiento = mantenimiento;
	}

	@PostMapping
	public ResponseEntity<CrearTipoSeguroResponseV1> crear(@Valid @RequestBody CrearTipoSeguroRequestV1 request,
			HttpServletRequest httpRequest) {
		return ResponseEntity.ok(TipoSeguroApiV1Mapper.toResponse(
				mantenimiento.crear(TipoSeguroApiV1Mapper.toCommand(request), context(httpRequest, "Registrar"))));
	}

	@PutMapping("/{tipoSeguro}")
	public ResponseEntity<ActualizarTipoSeguroResponseV1> actualizar(@PathVariable @Min(1) Integer tipoSeguro,
			@Valid @RequestBody ActualizarTipoSeguroRequestV1 request, HttpServletRequest httpRequest) {
		return ResponseEntity.ok(TipoSeguroApiV1Mapper.toResponse(mantenimiento.actualizar(
				TipoSeguroApiV1Mapper.toCommand(tipoSeguro, request), context(httpRequest, "Actualizar"))));
	}

	@GetMapping("/{tipoSeguro}")
	public ResponseEntity<TipoSeguroResponseV1> obtener(@PathVariable @Min(1) Integer tipoSeguro,
			HttpServletRequest request) {
		return ResponseEntity.ok(TipoSeguroApiV1Mapper.toResponse(
				mantenimiento.obtener(new ObtenerTipoSeguroQuery(tipoSeguro), context(request, "Obtener"))));
	}

	@PostMapping(value = "/listar", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ListarTiposSeguroResponseV1> listar(
			@Valid @RequestBody(required = false) ListarTiposSeguroRequestV1 request, HttpServletRequest httpRequest) {
		return ResponseEntity.ok(TipoSeguroApiV1Mapper
				.toResponse(mantenimiento.listar(TipoSeguroApiV1Mapper.toQuery(request), context(httpRequest, "Listar"))));
	}

	@PatchMapping("/{tipoSeguro}/estado")
	public ResponseEntity<TipoSeguroResponseV1> cambiarEstado(@PathVariable @Min(1) Integer tipoSeguro,
			@Valid @RequestBody CambiarEstadoTipoSeguroRequestV1 request, HttpServletRequest httpRequest) {
		return ResponseEntity.ok(TipoSeguroApiV1Mapper.toResponse(mantenimiento.cambiarEstado(
				TipoSeguroApiV1Mapper.toCommand(tipoSeguro, request), context(httpRequest, "Cambiar de Estado"))));
	}

	@DeleteMapping("/{tipoSeguro}")
	public ResponseEntity<EliminarTipoSeguroResponseV1> eliminar(@PathVariable @Min(1) Integer tipoSeguro,
			HttpServletRequest request) {
		return ResponseEntity.ok(TipoSeguroApiV1Mapper.toResponse(
				mantenimiento.eliminar(new EliminarTipoSeguroCommand(tipoSeguro), context(request, "Eliminar"))));
	}
}
