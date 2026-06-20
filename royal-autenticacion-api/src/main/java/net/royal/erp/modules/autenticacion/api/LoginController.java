package net.royal.erp.modules.autenticacion.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import net.royal.erp.framework.kernel.*;
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
	public ResponseEntity<LoginResult> login(@Valid @RequestBody LoginRequest body, HttpServletRequest request) {
		LoginCommand command = new LoginCommand(body.username(), body.password());
		FunctionalContext context = new FunctionalContext("default", "demo-client", body.username(), "AUTENTICACION",
				"Login", "Login", "LoginUseCase", null, null, null, Instant.now(),
				RequestLanguage.fromHeaders(request.getHeader("X-Language"), request.getHeader("Accept-Language")));
		return ResponseEntity.ok(useCase.execute(command, context));
	}

	private record LoginRequest(@NotBlank @Size(max = 60) String username, @NotBlank @Size(max = 200) String password) {
	}
}
