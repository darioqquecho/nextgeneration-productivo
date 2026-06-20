package net.royal.erp.framework.web.security;

import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Headers de seguridad por defecto para reducir configuraciones inseguras.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SecurityHeadersFilter implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse http = (HttpServletResponse) response;
		http.setHeader("X-Content-Type-Options", "nosniff");
		http.setHeader("X-Frame-Options", "DENY");
		http.setHeader("Referrer-Policy", "no-referrer");
		http.setHeader("Permissions-Policy", "geolocation=(), microphone=(), camera=()");
		http.setHeader("Content-Security-Policy", "default-src 'none'; frame-ancestors 'none'; base-uri 'none'");
		http.setHeader("Cache-Control", "no-store");
		chain.doFilter(request, response);
	}
}
