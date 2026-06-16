package net.royal.erp.modules.autenticacion.api;

import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.modules.autenticacion.application.login.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;

/**
 * Implementa: - ARCH-006 Seguridad e Identidad. - MOD-012 CU-004 Login.
 */
@RestController
@RequestMapping("/api/autenticacion")
public class LoginController {
	private final LoginUseCase useCase;

	/**
	 * Crea controller de login.
	 *
	 * Implementa: - ARCH-012 API.
	 */
	public LoginController(LoginUseCase useCase) {
		this.useCase = useCase;
	}

	/**
	 * Endpoint de autenticación local.
	 *
	 * Implementa: - ARCH-006 Autenticación.
	 */
	@PostMapping("/login")
	public ResponseEntity<LoginResult> login(@RequestBody LoginCommand command) {
		FunctionalContext context = new FunctionalContext("default", "demo-client", command.username(), "AUTENTICACION",
				"Login", "Login", "LoginUseCase", null, null, null, Instant.now());
		return ResponseEntity.ok(useCase.execute(command, context));
	}
}
