package net.royal.erp.framework.web;

import jakarta.servlet.http.HttpServletRequest;
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
	public ResponseEntity<ApiErrorResponse> business(BusinessException ex, HttpServletRequest request) {
		return ResponseEntity.badRequest()
				.body(new ApiErrorResponse(ex.code(), messages.resolve(ex.code(), language(request), ex.args())));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiErrorResponse> validation(MethodArgumentNotValidException ex, HttpServletRequest request) {
		var fields = ex.getBindingResult().getFieldErrors().stream()
				.map(error -> new ApiErrorResponse.ApiFieldError(error.getField(), error.getDefaultMessage())).toList();
		return ResponseEntity.badRequest()
				.body(new ApiErrorResponse("VALIDATION-ERROR", messages.resolve("VALIDATION-ERROR", language(request)), fields));
	}

	@ExceptionHandler(HandlerMethodValidationException.class)
	public ResponseEntity<ApiErrorResponse> methodValidation(HandlerMethodValidationException ex,
			HttpServletRequest request) {
		return ResponseEntity.badRequest()
				.body(new ApiErrorResponse("VALIDATION-ERROR", messages.resolve("VALIDATION-ERROR", language(request))));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiErrorResponse> generic(Exception ex, HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ApiErrorResponse(INTERNAL_ERROR, messages.resolve(INTERNAL_ERROR, language(request))));
	}

	private String language(HttpServletRequest request) {
		return RequestLanguage.fromHeaders(request.getHeader("X-Language"), request.getHeader("Accept-Language"));
	}
}
