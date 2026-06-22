package net.royal.erp.framework.web;

import java.util.List;

/**
 * Envoltorio estandar de salida para APIs REST Royal.
 */
public record RoyalResponse<T>(String estado, String traceId, String mensaje, T data, List<RoyalApiError> errores) {
	private static final String OK = "OK";
	private static final String ERROR = "ERROR";

	public RoyalResponse {
		errores = errores == null ? List.of() : List.copyOf(errores);
	}

	public static <T> RoyalResponse<T> ok(T data) {
		return new RoyalResponse<>(OK, traceIdOf(data), null, data, List.of());
	}

	public static RoyalResponse<Void> error(String traceId, String message, List<RoyalApiError> errors) {
		return new RoyalResponse<>(ERROR, traceId, message, null, errors);
	}

	public static RoyalResponse<Void> error(String traceId, String code, String message) {
		return error(traceId, message, List.of(new RoyalApiError(code, message)));
	}

	private static String traceIdOf(Object data) {
		if (data == null) {
			return null;
		}
		try {
			Object value = data.getClass().getMethod("traceId").invoke(data);
			return value == null ? null : value.toString();
		} catch (ReflectiveOperationException ignored) {
			return null;
		}
	}
}
