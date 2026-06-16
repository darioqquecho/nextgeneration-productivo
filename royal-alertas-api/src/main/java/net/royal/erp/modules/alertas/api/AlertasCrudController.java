package net.royal.erp.modules.alertas.api;

import jakarta.servlet.http.HttpServletRequest;
import net.royal.erp.modules.alertas.application.dto.*;
import net.royal.erp.modules.alertas.application.usecase.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

/**
 * Implementa: - MOD-012 Migración funcional de royal-alertas-api. - ARCH-012
 * API.
 *
 * Propósito: API nueva para CRUD funcional de las 38 entidades legacy de
 * Alertas.
 */
@RestController
@RequestMapping("/api/alertas/{entityName}")
public class AlertasCrudController {
	private final CrearRegistroUseCase crear;
	private final ActualizarRegistroUseCase actualizar;
	private final ObtenerRegistroUseCase obtener;
	private final ListarRegistrosUseCase listar;
	private final AnularRegistroUseCase anular;
	private final EliminarRegistroUseCase eliminar;
	private final AlertasContextFactory contextFactory;

	public AlertasCrudController(CrearRegistroUseCase crear, ActualizarRegistroUseCase actualizar,
			ObtenerRegistroUseCase obtener, ListarRegistrosUseCase listar, AnularRegistroUseCase anular,
			EliminarRegistroUseCase eliminar, AlertasContextFactory contextFactory) {
		this.crear = crear;
		this.actualizar = actualizar;
		this.obtener = obtener;
		this.listar = listar;
		this.anular = anular;
		this.eliminar = eliminar;
		this.contextFactory = contextFactory;
	}

	/** Implementa MOD-012. Inserta registro. */
	@PostMapping
	public ResponseEntity<AlertasOperationResult> crear(@PathVariable String entityName,
			@RequestBody Map<String, Object> body, HttpServletRequest request) {
		return ResponseEntity.ok(crear.execute(new AlertasCrudCommand(entityName, body),
				contextFactory.from(request, "CRUD", "Crear Registro", "CrearRegistroUseCase")));
	}

	/** Implementa MOD-012. Actualiza registro. */
	@PutMapping
	public ResponseEntity<AlertasOperationResult> actualizar(@PathVariable String entityName,
			@RequestBody Map<String, Object> body, HttpServletRequest request) {
		return ResponseEntity.ok(actualizar.execute(new AlertasCrudCommand(entityName, body),
				contextFactory.from(request, "CRUD", "Actualizar Registro", "ActualizarRegistroUseCase")));
	}

	/** Implementa MOD-012. Obtiene por PK. */
	@PostMapping("/obtener")
	public ResponseEntity<AlertasOperationResult> obtener(@PathVariable String entityName,
			@RequestBody Map<String, Object> id, HttpServletRequest request) {
		return ResponseEntity.ok(obtener.execute(new AlertasIdCommand(entityName, id),
				contextFactory.from(request, "CRUD", "Obtener Registro", "ObtenerRegistroUseCase")));
	}

	/** Implementa MOD-012. Lista registros. */
	@GetMapping
	public ResponseEntity<AlertasListResult> listar(@PathVariable String entityName, HttpServletRequest request) {
		return ResponseEntity.ok(listar.execute(new AlertasIdCommand(entityName, Map.of()),
				contextFactory.from(request, "CRUD", "Listar Registros", "ListarRegistrosUseCase")));
	}

	/** Implementa MOD-012. Anula registro por PK. */
	@PutMapping("/anular")
	public ResponseEntity<AlertasOperationResult> anular(@PathVariable String entityName,
			@RequestBody Map<String, Object> id, HttpServletRequest request) {
		return ResponseEntity.ok(anular.execute(new AlertasIdCommand(entityName, id),
				contextFactory.from(request, "CRUD", "Anular Registro", "AnularRegistroUseCase")));
	}

	/** Implementa MOD-012. Elimina registro por PK. */
	@DeleteMapping
	public ResponseEntity<Void> eliminar(@PathVariable String entityName, @RequestBody Map<String, Object> id,
			HttpServletRequest request) {
		eliminar.execute(new AlertasIdCommand(entityName, id),
				contextFactory.from(request, "CRUD", "Eliminar Registro", "EliminarRegistroUseCase"));
		return ResponseEntity.noContent().build();
	}
}
