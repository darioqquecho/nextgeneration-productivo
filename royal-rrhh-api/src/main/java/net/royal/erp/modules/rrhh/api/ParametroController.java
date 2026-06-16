package net.royal.erp.modules.rrhh.api;

import jakarta.servlet.http.HttpServletRequest;
import net.royal.erp.modules.rrhh.application.parametro.dto.*;
import net.royal.erp.modules.rrhh.application.parametro.usecase.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Implementa: - MOD-012 CU-001 CRUD HR_Parametros. - ARCH-012 API.
 */
@RestController
@RequestMapping("/api/rrhh/parametros")
public class ParametroController {
	private final CrearParametroUseCase crear;
	private final ActualizarParametroUseCase actualizar;
	private final ObtenerParametroUseCase obtener;
	private final ListarParametrosUseCase listar;
	private final CambiarEstadoParametroUseCase cambiarEstado;
	private final EliminarParametroUseCase eliminar;
	private final FunctionalContextFactory contextFactory;

	public ParametroController(CrearParametroUseCase crear, ActualizarParametroUseCase actualizar,
			ObtenerParametroUseCase obtener, ListarParametrosUseCase listar,
			CambiarEstadoParametroUseCase cambiarEstado, EliminarParametroUseCase eliminar,
			FunctionalContextFactory contextFactory) {
		this.crear = crear;
		this.actualizar = actualizar;
		this.obtener = obtener;
		this.listar = listar;
		this.cambiarEstado = cambiarEstado;
		this.eliminar = eliminar;
		this.contextFactory = contextFactory;
	}

	@PostMapping
	public ResponseEntity<CrearParametroResult> crear(@RequestBody CrearParametroCommand command,
			HttpServletRequest request) {
		return ResponseEntity.ok(crear.execute(command,
				contextFactory.from(request, "Parametros", "Crear Parametro", "CrearParametroUseCase")));
	}

	@PutMapping("/{compania}/{codigo}")
	public ResponseEntity<ActualizarParametroResult> actualizar(@PathVariable String compania,
			@PathVariable String codigo, @RequestBody ActualizarParametroCommand body, HttpServletRequest request) {
		return ResponseEntity.ok(actualizar.execute(new ActualizarParametroCommand(compania, codigo, body.nombre()),
				contextFactory.from(request, "Parametros", "Actualizar Parametro", "ActualizarParametroUseCase")));
	}

	@GetMapping("/{compania}/{codigo}")
	public ResponseEntity<ParametroResult> obtener(@PathVariable String compania, @PathVariable String codigo,
			HttpServletRequest request) {
		return ResponseEntity.ok(obtener.execute(new ObtenerParametroQuery(compania, codigo),
				contextFactory.from(request, "Parametros", "Obtener Parametro", "ObtenerParametroUseCase")));
	}

	@GetMapping
	public ResponseEntity<ListarParametrosResult> listar(HttpServletRequest request) {
		return ResponseEntity.ok(listar.execute(new ListarParametrosQuery(),
				contextFactory.from(request, "Parametros", "Listar Parametros", "ListarParametrosUseCase")));
	}

	@PatchMapping("/{compania}/{codigo}/estado")
	public ResponseEntity<ParametroResult> cambiarEstado(@PathVariable String compania, @PathVariable String codigo,
			@RequestBody CambiarEstadoParametroCommand body, HttpServletRequest request) {
		return ResponseEntity.ok(cambiarEstado.execute(new CambiarEstadoParametroCommand(compania, codigo, body.estado()),
				contextFactory.from(request, "Parametros", "Cambiar Estado Parametro",
						"CambiarEstadoParametroUseCase")));
	}

	@DeleteMapping("/{compania}/{codigo}")
	public ResponseEntity<EliminarParametroResult> eliminar(@PathVariable String compania, @PathVariable String codigo,
			HttpServletRequest request) {
		return ResponseEntity.ok(eliminar.execute(new EliminarParametroCommand(compania, codigo),
				contextFactory.from(request, "Parametros", "Eliminar Parametro", "EliminarParametroUseCase")));
	}
}
