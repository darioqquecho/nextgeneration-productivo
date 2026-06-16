package net.royal.erp.modules.rrhh.api;

import net.royal.erp.framework.kernel.BusinessException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

/**
 * Implementa: - ARCH-006 Seguridad.
 *
 * Propósito: Manejo seguro de errores sin exponer stack traces.
 */
@RestControllerAdvice
public class ApiExceptionHandler {
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<Map<String, String>> business(BusinessException ex) {
		return ResponseEntity.badRequest().body(Map.of("code", ex.code(), "message", ex.getMessage()));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, String>> generic(Exception ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(Map.of("code", "INTERNAL_ERROR", "message", "Error interno controlado"));
	}
}
