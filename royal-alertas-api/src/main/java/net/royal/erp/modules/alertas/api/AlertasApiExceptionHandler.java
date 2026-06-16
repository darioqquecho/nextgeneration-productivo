package net.royal.erp.modules.alertas.api;

import net.royal.erp.framework.kernel.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.Map;

/**
 * Implementa: - ARCH-006 Seguridad por diseño.
 *
 * Propósito: Manejo seguro de errores para no exponer stack traces.
 */
@RestControllerAdvice
public class AlertasApiExceptionHandler {
	/** Implementa ARCH-006. Maneja errores funcionales. */
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<Map<String, String>> business(BusinessException ex) {
		return ResponseEntity.badRequest().body(Map.of("code", ex.code(), "message", ex.getMessage()));
	}

	/** Implementa ARCH-006. Maneja errores técnicos controlados. */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, String>> generic(Exception ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(Map.of("code", "INTERNAL_ERROR", "message", "Error interno controlado"));
	}
}
