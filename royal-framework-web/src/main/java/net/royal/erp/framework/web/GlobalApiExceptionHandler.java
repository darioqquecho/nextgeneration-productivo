package net.royal.erp.framework.web;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import net.royal.erp.framework.kernel.*;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

/**
 * Handler REST global para errores funcionales y tecnicos de las APIs.
 */
@RestControllerAdvice
public class GlobalApiExceptionHandler {
	private static final String INTERNAL_ERROR = "INTERNAL_ERROR";

	private final MessageResolver messages;

	public GlobalApiExceptionHandler() {
		this(new ResourceBundleMessageResolver());
	}

	public GlobalApiExceptionHandler(MessageResolver messages) {
		this.messages = messages;
	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<RoyalResponse<Void>> business(BusinessException ex, HttpServletRequest request) {
		String message = messages.resolve(ex.code(), language(request), ex.args());
		return ResponseEntity.badRequest()
				.body(RoyalResponse.error(traceId(request), ex.code(), message));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<RoyalResponse<Void>> validation(MethodArgumentNotValidException ex, HttpServletRequest request) {
		var fields = ex.getBindingResult().getFieldErrors().stream()
				.map(error -> new RoyalApiError("VALIDATION-ERROR", error.getField(), error.getDefaultMessage())).toList();
		return ResponseEntity.badRequest().body(RoyalResponse.error(traceId(request),
				messages.resolve("VALIDATION-ERROR", language(request)), fields));
	}

	@ExceptionHandler(HandlerMethodValidationException.class)
	public ResponseEntity<RoyalResponse<Void>> methodValidation(HandlerMethodValidationException ex,
			HttpServletRequest request) {
		String message = messages.resolve("VALIDATION-ERROR", language(request));
		return ResponseEntity.badRequest().body(RoyalResponse.error(traceId(request), "VALIDATION-ERROR", message));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<RoyalResponse<Void>> generic(Exception ex, HttpServletRequest request) {
		String message = messages.resolve(INTERNAL_ERROR, language(request));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(RoyalResponse.error(traceId(request), INTERNAL_ERROR, message));
	}

	private String language(HttpServletRequest request) {
		return RequestLanguage.fromHeaders(request.getHeader("X-Language"), request.getHeader("Accept-Language"));
	}

	private String traceId(HttpServletRequest request) {
		String traceId = request.getHeader("X-Trace-Id");
		return traceId == null || traceId.isBlank() ? null : traceId;
	}
}
