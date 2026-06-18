package net.royal.erp.framework.kernel;

/**
 * Implementa: - ARCH-026 Mensajes multi idioma.
 */
public interface MessageResolver {
	String resolve(String code, String language, Object... args);
}
