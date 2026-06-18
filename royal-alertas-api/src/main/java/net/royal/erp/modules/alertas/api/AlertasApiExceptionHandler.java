package net.royal.erp.modules.alertas.api;

import jakarta.servlet.http.HttpServletRequest;
import net.royal.erp.framework.kernel.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

/**
 * Implementa: - ARCH-006 Seguridad. - ARCH-026 Mensajes multi idioma.
 */
@RestControllerAdvice
public class AlertasApiExceptionHandler {
	private final MessageResolver messages = new ResourceBundleMessageResolver();

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<Map<String, String>> business(BusinessException ex, HttpServletRequest request) {
		return ResponseEntity.badRequest().body(Map.of("code", ex.code(), "message",
				messages.resolve(ex.code(), language(request), ex.args())));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, String>> generic(Exception ex, HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(Map.of("code", "INTERNAL_ERROR", "message",
						messages.resolve("INTERNAL_ERROR", language(request))));
	}

	private String language(HttpServletRequest request) {
		return RequestLanguage.fromHeaders(request.getHeader("X-Language"), request.getHeader("Accept-Language"));
	}
}
