package net.royal.erp.framework.kernel;

import java.text.MessageFormat;
import java.util.*;

/**
 * Implementa: - ARCH-026 Mensajes multi idioma con ResourceBundle.
 */
public class ResourceBundleMessageResolver implements MessageResolver {
	private static final String BASE_NAME = "messages";
	private static final Locale DEFAULT_LOCALE = Locale.forLanguageTag("es");

	@Override
	public String resolve(String code, String language, Object... args) {
		Locale locale = language == null || language.isBlank() ? DEFAULT_LOCALE : Locale.forLanguageTag(language);
		String pattern = pattern(code, locale);
		return new MessageFormat(pattern, locale).format(args == null ? new Object[0] : args);
	}

	private String pattern(String code, Locale locale) {
		try {
			ResourceBundle bundle = ResourceBundle.getBundle(BASE_NAME, locale,
					Thread.currentThread().getContextClassLoader());
			return bundle.containsKey(code) ? bundle.getString(code) : code;
		} catch (MissingResourceException e) {
			return code;
		}
	}
}
