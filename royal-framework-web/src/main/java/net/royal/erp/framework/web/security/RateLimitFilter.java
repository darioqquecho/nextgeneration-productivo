package net.royal.erp.framework.web.security;

import java.io.IOException;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Rate limit liviano por IP/cliente para reducir abuso basico.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 20)
public class RateLimitFilter implements Filter {
	private final boolean enabled;
	private final int requestsPerMinute;
	private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

	public RateLimitFilter(@Value("${royal.security.rate-limit.enabled:true}") boolean enabled,
			@Value("${royal.security.rate-limit.requests-per-minute:120}") int requestsPerMinute) {
		this.enabled = enabled;
		this.requestsPerMinute = Math.max(1, requestsPerMinute);
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (!enabled) {
			chain.doFilter(request, response);
			return;
		}
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String key = key(httpRequest);
		Bucket bucket = buckets.compute(key, (ignored, current) -> next(current));
		if (bucket.count() > requestsPerMinute) {
			httpResponse.setStatus(429);
			httpResponse.setContentType("application/json");
			httpResponse.getWriter().write("{\"code\":\"RATE-LIMIT\",\"message\":\"Demasiadas solicitudes\"}");
			return;
		}
		chain.doFilter(request, response);
	}

	private Bucket next(Bucket current) {
		long minute = Instant.now().getEpochSecond() / 60;
		if (current == null || current.minute() != minute) {
			return new Bucket(minute, 1);
		}
		return new Bucket(minute, current.count() + 1);
	}

	private String key(HttpServletRequest request) {
		String client = request.getHeader("X-Client-Id");
		String forwarded = request.getHeader("X-Forwarded-For");
		String ip = forwarded == null || forwarded.isBlank() ? request.getRemoteAddr() : forwarded.split(",")[0].trim();
		return (client == null || client.isBlank() ? "default" : client) + "|" + ip;
	}

	private record Bucket(long minute, int count) {
	}
}
