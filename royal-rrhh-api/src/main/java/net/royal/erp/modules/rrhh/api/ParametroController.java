package net.royal.erp.modules.rrhh.api;

import jakarta.servlet.http.HttpServletRequest;
import net.royal.erp.modules.rrhh.application.parametro.dto.*;
import net.royal.erp.modules.rrhh.application.parametro.usecase.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Implementa: - MOD-012 CU-001 Mantenimiento de Tabla Parametros. - ARCH-012
 * API.
 */
@RestController
@RequestMapping("/api/rrhh/parametros")
public class ParametroController {
	private static final String PROCESS = "Maestros";
	private static final String MAINTENANCE_USE_CASE = "Mantenimiento de Parametro";
	private static final String REPORT_USE_CASE = "Reporte de Parametro";
	private static final String MASS_APPROVAL_USE_CASE = "Aprobacion masiva de Parametros";

	private final MantenimientoTablaParametrosUseCase mantenimiento;
	private final ReporteParametrosUseCase reporte;
	private final AprobacionMasivaParametrosUseCase aprobacionMasiva;
	private final FunctionalContextFactory contextFactory;

	public ParametroController(MantenimientoTablaParametrosUseCase mantenimiento, ReporteParametrosUseCase reporte,
			AprobacionMasivaParametrosUseCase aprobacionMasiva, FunctionalContextFactory contextFactory) {
		this.mantenimiento = mantenimiento;
		this.reporte = reporte;
		this.aprobacionMasiva = aprobacionMasiva;
		this.contextFactory = contextFactory;
	}

	@PostMapping
	public ResponseEntity<CrearParametroResult> crear(@RequestBody CrearParametroCommand command,
			HttpServletRequest request) {
		return ResponseEntity.ok(mantenimiento.crear(command, maintenanceContext(request, "Registrar")));
	}

	@PutMapping("/{compania}/{codigo}")
	public ResponseEntity<ActualizarParametroResult> actualizar(@PathVariable String compania,
			@PathVariable String codigo, @RequestBody ActualizarParametroCommand body, HttpServletRequest request) {
		return ResponseEntity.ok(mantenimiento.actualizar(new ActualizarParametroCommand(compania, codigo, body.nombre()),
				maintenanceContext(request, "Actualizar")));
	}

	@GetMapping("/{compania}/{codigo}")
	public ResponseEntity<ParametroResult> obtener(@PathVariable String compania, @PathVariable String codigo,
			HttpServletRequest request) {
		return ResponseEntity
				.ok(mantenimiento.obtener(new ObtenerParametroQuery(compania, codigo), maintenanceContext(request, "Obtener")));
	}

	@PostMapping(value = "/listar", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ListarParametrosResult> listar(@RequestBody(required = false) ListarParametrosQuery query,
			HttpServletRequest request) {
		return ResponseEntity.ok(mantenimiento.listar(query, maintenanceContext(request, "Listar")));
	}

	@PostMapping(value = "/reporte/pdf", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> reportePdf(@RequestBody ReporteParametrosQuery query, HttpServletRequest request) {
		ReporteParametrosResult result = reporte.ejecutar(query, reportContext(request, "Reporte"));
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + result.fileName() + "\"")
				.contentType(MediaType.APPLICATION_PDF).body(result.pdf());
	}

	@PostMapping(value = "/aprobacion-masiva", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AprobarMasivamenteParametrosResult> aprobacionMasiva(
			@RequestBody AprobarMasivamenteParametrosCommand command, HttpServletRequest request) {
		return ResponseEntity.ok(aprobacionMasiva.aprobar(command, massApprovalContext(request, "Aprobar")));
	}

	@PatchMapping("/{compania}/{codigo}/estado")
	public ResponseEntity<ParametroResult> cambiarEstado(@PathVariable String compania, @PathVariable String codigo,
			@RequestBody CambiarEstadoParametroCommand body, HttpServletRequest request) {
		return ResponseEntity.ok(mantenimiento.cambiarEstado(new CambiarEstadoParametroCommand(compania, codigo, body.estado()),
				maintenanceContext(request, "Cambiar de Estado")));
	}

	@DeleteMapping("/{compania}/{codigo}")
	public ResponseEntity<EliminarParametroResult> eliminar(@PathVariable String compania, @PathVariable String codigo,
			HttpServletRequest request) {
		return ResponseEntity
				.ok(mantenimiento.eliminar(new EliminarParametroCommand(compania, codigo), maintenanceContext(request, "Eliminar")));
	}

	private net.royal.erp.framework.kernel.FunctionalContext maintenanceContext(HttpServletRequest request,
			String functionality) {
		return contextFactory.from(request, PROCESS, functionality, MAINTENANCE_USE_CASE);
	}

	private net.royal.erp.framework.kernel.FunctionalContext reportContext(HttpServletRequest request,
			String functionality) {
		return contextFactory.from(request, PROCESS, functionality, REPORT_USE_CASE);
	}

	private net.royal.erp.framework.kernel.FunctionalContext massApprovalContext(HttpServletRequest request,
			String functionality) {
		return contextFactory.from(request, PROCESS, functionality, MASS_APPROVAL_USE_CASE);
	}
}
