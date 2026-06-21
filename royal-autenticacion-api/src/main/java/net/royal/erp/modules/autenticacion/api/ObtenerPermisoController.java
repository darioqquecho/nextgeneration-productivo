package net.royal.erp.modules.autenticacion.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.web.FunctionalContextFactory;
import net.royal.erp.modules.autenticacion.application.permiso.ObtenerPermisoCommand;
import net.royal.erp.modules.autenticacion.application.permiso.ObtenerPermisoResult;
import net.royal.erp.modules.autenticacion.application.permiso.ObtenerPermisoSimpleUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Servicio REST para consulta simple de permisos.
 */
@RestController
@RequestMapping("/api/autenticacion")
public class ObtenerPermisoController {
	private final ObtenerPermisoSimpleUseCase useCase;
	private final FunctionalContextFactory contextFactory;

	public ObtenerPermisoController(ObtenerPermisoSimpleUseCase useCase, FunctionalContextFactory contextFactory) {
		this.useCase = useCase;
		this.contextFactory = contextFactory;
	}

	@PostMapping("/obtenerpermiso")
	public ResponseEntity<ObtenerPermisoResult> obtenerPermiso(@Valid @RequestBody ObtenerPermisoRequest body,
			HttpServletRequest request) {
		ObtenerPermisoCommand command = new ObtenerPermisoCommand(body.usuario(), body.concepto(), body.funcionalidad(),
				body.permiso());
		FunctionalContext context = contextFactory.from(request, "AUTENTICACION", "Seguridad", "Permisos",
				"ObtenerPermiso");
		return ResponseEntity.ok(useCase.execute(command, context));
	}

	private record ObtenerPermisoRequest(@NotBlank @Size(max = 20) String usuario, @NotBlank @Size(max = 60) String concepto,
			@NotBlank @Size(max = 80) String funcionalidad,
			@NotBlank @Size(max = 120) @Pattern(regexp = "^[A-Z0-9_]+$") String permiso) {
	}
}
