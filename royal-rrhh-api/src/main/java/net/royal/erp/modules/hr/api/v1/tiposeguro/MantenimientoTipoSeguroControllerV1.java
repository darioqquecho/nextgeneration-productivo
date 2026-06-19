package net.royal.erp.modules.hr.api.v1.tiposeguro;

import jakarta.servlet.http.HttpServletRequest;
import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.web.FunctionalContextFactory;
import net.royal.erp.modules.hr.api.v1.tiposeguro.dto.*;
import net.royal.erp.modules.hr.application.tiposeguro.dto.*;
import net.royal.erp.modules.hr.application.tiposeguro.usecase.MantenimientoTipoSeguroUseCase;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hr/maestros/tipos-seguro")
public class MantenimientoTipoSeguroControllerV1 {
	private static final String MODULE = "HR";
	private static final String PROCESS = "Maestros";
	private static final String USE_CASE = "Mantenimiento de Tipo Seguro";

	private final MantenimientoTipoSeguroUseCase mantenimiento;
	private final FunctionalContextFactory contextFactory;

	public MantenimientoTipoSeguroControllerV1(MantenimientoTipoSeguroUseCase mantenimiento,
			FunctionalContextFactory contextFactory) {
		this.mantenimiento = mantenimiento;
		this.contextFactory = contextFactory;
	}

	@PostMapping
	public ResponseEntity<CrearTipoSeguroResponseV1> crear(@RequestBody CrearTipoSeguroRequestV1 request,
			HttpServletRequest httpRequest) {
		return ResponseEntity.ok(TipoSeguroApiV1Mapper.toResponse(
				mantenimiento.crear(TipoSeguroApiV1Mapper.toCommand(request), context(httpRequest, "Registrar"))));
	}

	@PutMapping("/{tipoSeguro}")
	public ResponseEntity<ActualizarTipoSeguroResponseV1> actualizar(@PathVariable Integer tipoSeguro,
			@RequestBody ActualizarTipoSeguroRequestV1 request, HttpServletRequest httpRequest) {
		return ResponseEntity.ok(TipoSeguroApiV1Mapper.toResponse(mantenimiento.actualizar(
				TipoSeguroApiV1Mapper.toCommand(tipoSeguro, request), context(httpRequest, "Actualizar"))));
	}

	@GetMapping("/{tipoSeguro}")
	public ResponseEntity<TipoSeguroResponseV1> obtener(@PathVariable Integer tipoSeguro, HttpServletRequest request) {
		return ResponseEntity.ok(TipoSeguroApiV1Mapper.toResponse(
				mantenimiento.obtener(new ObtenerTipoSeguroQuery(tipoSeguro), context(request, "Obtener"))));
	}

	@PostMapping(value = "/listar", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ListarTiposSeguroResponseV1> listar(
			@RequestBody(required = false) ListarTiposSeguroRequestV1 request, HttpServletRequest httpRequest) {
		return ResponseEntity.ok(TipoSeguroApiV1Mapper
				.toResponse(mantenimiento.listar(TipoSeguroApiV1Mapper.toQuery(request), context(httpRequest, "Listar"))));
	}

	@PatchMapping("/{tipoSeguro}/estado")
	public ResponseEntity<TipoSeguroResponseV1> cambiarEstado(@PathVariable Integer tipoSeguro,
			@RequestBody CambiarEstadoTipoSeguroRequestV1 request, HttpServletRequest httpRequest) {
		return ResponseEntity.ok(TipoSeguroApiV1Mapper.toResponse(mantenimiento.cambiarEstado(
				TipoSeguroApiV1Mapper.toCommand(tipoSeguro, request), context(httpRequest, "Cambiar de Estado"))));
	}

	@DeleteMapping("/{tipoSeguro}")
	public ResponseEntity<EliminarTipoSeguroResponseV1> eliminar(@PathVariable Integer tipoSeguro,
			HttpServletRequest request) {
		return ResponseEntity.ok(TipoSeguroApiV1Mapper.toResponse(
				mantenimiento.eliminar(new EliminarTipoSeguroCommand(tipoSeguro), context(request, "Eliminar"))));
	}

	private FunctionalContext context(HttpServletRequest request, String functionality) {
		return contextFactory.from(request, MODULE, PROCESS, functionality, USE_CASE);
	}
}
