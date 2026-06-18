package net.royal.erp.modules.rrhh.api.v1.parametro;

import jakarta.servlet.http.HttpServletRequest;
import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.modules.rrhh.api.FunctionalContextFactory;
import net.royal.erp.modules.rrhh.api.v1.parametro.dto.*;
import net.royal.erp.modules.rrhh.application.parametro.dto.*;
import net.royal.erp.modules.rrhh.application.parametro.usecase.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 * API v1 de Parametros. Versiona el contrato REST externo.
 */
@RestController
@RequestMapping("/api/v1/rrhh/parametros")
public class ParametroControllerV1 {
	private static final String PROCESS = "Maestros";
	private static final String MAINTENANCE_USE_CASE = "Mantenimiento de Parametro";
	private static final String REPORT_USE_CASE = "Reporte de Parametro";
	private static final String MASS_APPROVAL_USE_CASE = "Aprobacion masiva de Parametros";

	private final MantenimientoTablaParametrosUseCase mantenimiento;
	private final ReporteParametrosUseCase reporte;
	private final AprobacionMasivaParametrosUseCase aprobacionMasiva;
	private final FunctionalContextFactory contextFactory;

	public ParametroControllerV1(MantenimientoTablaParametrosUseCase mantenimiento, ReporteParametrosUseCase reporte,
			AprobacionMasivaParametrosUseCase aprobacionMasiva, FunctionalContextFactory contextFactory) {
		this.mantenimiento = mantenimiento;
		this.reporte = reporte;
		this.aprobacionMasiva = aprobacionMasiva;
		this.contextFactory = contextFactory;
	}

	@PostMapping
	public ResponseEntity<CrearParametroResponseV1> crear(@RequestBody CrearParametroRequestV1 request,
			HttpServletRequest httpRequest) {
		return ResponseEntity.ok(ParametroApiV1Mapper.toResponse(
				mantenimiento.crear(ParametroApiV1Mapper.toCommand(request), maintenanceContext(httpRequest, "Registrar"))));
	}

	@PutMapping("/{compania}/{codigo}")
	public ResponseEntity<ActualizarParametroResponseV1> actualizar(@PathVariable String compania,
			@PathVariable String codigo, @RequestBody ActualizarParametroRequestV1 request,
			HttpServletRequest httpRequest) {
		return ResponseEntity.ok(ParametroApiV1Mapper.toResponse(mantenimiento.actualizar(
				ParametroApiV1Mapper.toCommand(compania, codigo, request), maintenanceContext(httpRequest, "Actualizar"))));
	}

	@GetMapping("/{compania}/{codigo}")
	public ResponseEntity<ParametroResponseV1> obtener(@PathVariable String compania, @PathVariable String codigo,
			HttpServletRequest request) {
		return ResponseEntity.ok(ParametroApiV1Mapper.toResponse(
				mantenimiento.obtener(new ObtenerParametroQuery(compania, codigo), maintenanceContext(request, "Obtener"))));
	}

	@PostMapping(value = "/listar", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ListarParametrosResponseV1> listar(@RequestBody(required = false) ListarParametrosRequestV1 request,
			HttpServletRequest httpRequest) {
		return ResponseEntity.ok(ParametroApiV1Mapper.toResponse(
				mantenimiento.listar(ParametroApiV1Mapper.toQuery(request), maintenanceContext(httpRequest, "Listar"))));
	}

	@PostMapping(value = "/reporte/pdf", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> reportePdf(@RequestBody(required = false) ReporteParametrosRequestV1 request,
			HttpServletRequest httpRequest) {
		ReporteParametrosResult result = reporte.ejecutar(ParametroApiV1Mapper.toQuery(request),
				reportContext(httpRequest, "Reporte"));
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + result.fileName() + "\"")
				.contentType(MediaType.APPLICATION_PDF).body(result.pdf());
	}

	@PostMapping(value = "/aprobacion-masiva", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AprobacionMasivaParametrosResponseV1> aprobacionMasiva(
			@RequestBody AprobacionMasivaParametrosRequestV1 request, HttpServletRequest httpRequest) {
		return ResponseEntity.ok(ParametroApiV1Mapper.toResponse(aprobacionMasiva.aprobar(
				ParametroApiV1Mapper.toCommand(request), massApprovalContext(httpRequest, "Aprobar"))));
	}

	@PatchMapping("/{compania}/{codigo}/estado")
	public ResponseEntity<ParametroResponseV1> cambiarEstado(@PathVariable String compania, @PathVariable String codigo,
			@RequestBody CambiarEstadoParametroRequestV1 request, HttpServletRequest httpRequest) {
		return ResponseEntity.ok(ParametroApiV1Mapper.toResponse(mantenimiento.cambiarEstado(
				ParametroApiV1Mapper.toCommand(compania, codigo, request),
				maintenanceContext(httpRequest, "Cambiar de Estado"))));
	}

	@DeleteMapping("/{compania}/{codigo}")
	public ResponseEntity<EliminarParametroResponseV1> eliminar(@PathVariable String compania,
			@PathVariable String codigo, HttpServletRequest request) {
		return ResponseEntity.ok(ParametroApiV1Mapper.toResponse(mantenimiento
				.eliminar(new EliminarParametroCommand(compania, codigo), maintenanceContext(request, "Eliminar"))));
	}

	private FunctionalContext maintenanceContext(HttpServletRequest request, String functionality) {
		return contextFactory.from(request, PROCESS, functionality, MAINTENANCE_USE_CASE);
	}

	private FunctionalContext reportContext(HttpServletRequest request, String functionality) {
		return contextFactory.from(request, PROCESS, functionality, REPORT_USE_CASE);
	}

	private FunctionalContext massApprovalContext(HttpServletRequest request, String functionality) {
		return contextFactory.from(request, PROCESS, functionality, MASS_APPROVAL_USE_CASE);
	}
}
