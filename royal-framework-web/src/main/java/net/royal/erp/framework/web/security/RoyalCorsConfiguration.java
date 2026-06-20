package net.royal.erp.framework.web.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * CORS cerrado por defecto. Se habilita con allowlist explicita.
 */
@Configuration
public class RoyalCorsConfiguration implements WebMvcConfigurer {
	private final List<String> allowedOrigins;

	public RoyalCorsConfiguration(@Value("${royal.security.cors.allowed-origins:}") String allowedOrigins) {
		this.allowedOrigins = allowedOrigins == null || allowedOrigins.isBlank() ? List.of()
				: java.util.Arrays.stream(allowedOrigins.split(",")).map(String::trim).filter(v -> !v.isBlank()).toList();
	}

	public void addCorsMappings(CorsRegistry registry) {
		if (allowedOrigins.isEmpty()) {
			return;
		}
		registry.addMapping("/api/**").allowedOrigins(allowedOrigins.toArray(String[]::new))
				.allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
				.allowedHeaders("Content-Type", "Authorization", "X-Api-Key", "X-Tenant-Id", "X-Client-Id", "X-User-Id",
						"X-Trace-Id", "X-Request-Id", "X-Language", "X-Functional-Version")
				.exposedHeaders("X-Trace-Id", "X-Request-Id").allowCredentials(false).maxAge(1800);
	}
}
