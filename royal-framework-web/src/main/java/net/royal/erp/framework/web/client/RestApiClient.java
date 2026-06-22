package net.royal.erp.framework.web.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.royal.erp.framework.kernel.BusinessException;
import net.royal.erp.framework.kernel.FunctionalContext;

/**
 * Cliente REST tecnico para comunicacion entre APIs del ERP.
 */
public class RestApiClient {
	private final HttpClient httpClient;
	private final ObjectMapper objectMapper;
	private final String baseUrl;
	private final Duration requestTimeout;
	private final String errorCode;
	private final String apiKey;

	public RestApiClient(String baseUrl) {
		this(baseUrl, "REST-API-ERROR");
	}

	public RestApiClient(String baseUrl, String errorCode) {
		this(HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(5)).build(), new ObjectMapper(), baseUrl,
				Duration.ofSeconds(10), errorCode, null);
	}

	public RestApiClient(String baseUrl, String errorCode, String apiKey) {
		this(HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(5)).build(), new ObjectMapper(), baseUrl,
				Duration.ofSeconds(10), errorCode, apiKey);
	}

	RestApiClient(HttpClient httpClient, ObjectMapper objectMapper, String baseUrl, Duration requestTimeout,
			String errorCode, String apiKey) {
		this.httpClient = httpClient;
		this.objectMapper = objectMapper;
		this.baseUrl = trimTrailingSlash(baseUrl);
		this.requestTimeout = requestTimeout;
		this.errorCode = errorCode == null || errorCode.isBlank() ? "REST-API-ERROR" : errorCode;
		this.apiKey = apiKey;
	}

	public <T> T post(String path, Object requestBody, Class<T> responseType, FunctionalContext context) {
		try {
			String body = objectMapper.writeValueAsString(requestBody);
			HttpRequest request = baseRequest(path, context).header("Content-Type", "application/json")
					.POST(HttpRequest.BodyPublishers.ofString(body)).build();
			return send(request, responseType);
		} catch (IOException ex) {
			throw new BusinessException(errorCode, ex.getMessage());
		}
	}

	public <T> T get(String path, Class<T> responseType, FunctionalContext context) {
		HttpRequest request = baseRequest(path, context).GET().build();
		return send(request, responseType);
	}

	private HttpRequest.Builder baseRequest(String path, FunctionalContext context) {
		HttpRequest.Builder builder = HttpRequest.newBuilder(resolve(path)).timeout(requestTimeout).header("Accept",
				"application/json");
		header(builder, "X-Api-Key", apiKey);
		if (context != null) {
			header(builder, "X-Tenant-Id", context.tenantId());
			header(builder, "X-Client-Id", context.clientId());
			header(builder, "X-User-Id", context.userId());
			header(builder, "X-Trace-Id", context.traceId());
			header(builder, "X-Request-Id", context.requestId());
			header(builder, "X-Language", context.language());
		}
		return builder;
	}

	private <T> T send(HttpRequest request, Class<T> responseType) {
		try {
			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			if (response.statusCode() < 200 || response.statusCode() >= 300) {
				throw new BusinessException(errorCode, String.valueOf(response.statusCode()));
			}
			if (responseType == Void.class) {
				return null;
			}
			return objectMapper.readValue(response.body(), responseType);
		} catch (IOException ex) {
			throw new BusinessException(errorCode, ex.getMessage());
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
			throw new BusinessException(errorCode, ex.getMessage());
		}
	}

	private URI resolve(String path) {
		String normalizedPath = path == null || path.isBlank() ? "" : path.trim();
		if (!normalizedPath.startsWith("/")) {
			normalizedPath = "/" + normalizedPath;
		}
		return URI.create(baseUrl + normalizedPath);
	}

	private void header(HttpRequest.Builder builder, String name, String value) {
		if (value != null && !value.isBlank()) {
			builder.header(name, value);
		}
	}

	private String trimTrailingSlash(String value) {
		if (value == null || value.isBlank()) {
			throw new IllegalArgumentException("baseUrl requerido para RestApiClient");
		}
		return value.endsWith("/") ? value.substring(0, value.length() - 1) : value;
	}
}
