package net.royal.erp.framework.web;

import org.springframework.core.MethodParameter;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Aplica el envoltorio RoyalResponse a respuestas JSON de controllers REST.
 */
@Component
@RestControllerAdvice
public class RoyalResponseBodyAdvice implements ResponseBodyAdvice<Object> {
	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return !ByteArrayHttpMessageConverter.class.isAssignableFrom(converterType);
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType,
			org.springframework.http.server.ServerHttpRequest request,
			org.springframework.http.server.ServerHttpResponse response) {
		if (body == null || body instanceof RoyalResponse<?> || body instanceof Resource || body instanceof byte[]
				|| body instanceof String || !isJson(selectedContentType)) {
			return body;
		}
		return RoyalResponse.ok(body);
	}

	private boolean isJson(MediaType mediaType) {
		return mediaType == null || MediaType.APPLICATION_JSON.includes(mediaType)
				|| mediaType.getSubtype().endsWith("+json");
	}
}
