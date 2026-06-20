package net.royal.erp.framework.web.security;

import java.io.IOException;
import java.util.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Control simple de API key para APIs internas/ERP. Puede convivir luego con
 * OAuth2/JWT.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 10)
public class ApiKeyAuthenticationFilter implements Filter {
	private final boolean enabled;
	private final String headerName;
	private final Set<String> validKeys;
	private final List<String> publicPaths;

	public ApiKeyAuthenticationFilter(@Value("${royal.security.api-key.enabled:false}") boolean enabled,
			@Value("${royal.security.api-key.header:X-Api-Key}") String headerName,
			@Value("${royal.security.api-key.value:}") String apiKey,
			@Value("${royal.security.public-paths:/actuator/health,/actuator/info,/api/autenticacion/login}") String publicPaths) {
		this.enabled = enabled;
		this.headerName = blank(headerName) ? "X-Api-Key" : headerName;
		this.validKeys = parse(apiKey);
		this.publicPaths = parseList(publicPaths);
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		if (!enabled || "OPTIONS".equalsIgnoreCase(httpRequest.getMethod()) || publicPath(httpRequest.getRequestURI())) {
			chain.doFilter(request, response);
			return;
		}
		String received = httpRequest.getHeader(headerName);
		if (blank(received) || validKeys.stream().noneMatch(key -> constantTimeEquals(key, received))) {
			httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			httpResponse.setContentType("application/json");
			httpResponse.getWriter().write("{\"code\":\"AUTH-API-KEY-REQUIRED\",\"message\":\"API key requerida\"}");
			return;
		}
		chain.doFilter(request, response);
	}

	private boolean publicPath(String uri) {
		return publicPaths.stream().anyMatch(uri::startsWith);
	}

	private Set<String> parse(String value) {
		Set<String> values = new LinkedHashSet<>();
		for (String item : parseList(value)) {
			if (!blank(item)) {
				values.add(item);
			}
		}
		return values;
	}

	private List<String> parseList(String value) {
		if (blank(value)) {
			return List.of();
		}
		return Arrays.stream(value.split(",")).map(String::trim).filter(v -> !v.isBlank()).toList();
	}

	private boolean constantTimeEquals(String expected, String actual) {
		byte[] a = expected.getBytes(java.nio.charset.StandardCharsets.UTF_8);
		byte[] b = actual.getBytes(java.nio.charset.StandardCharsets.UTF_8);
		return java.security.MessageDigest.isEqual(a, b);
	}

	private boolean blank(String value) {
		return value == null || value.isBlank();
	}
}
