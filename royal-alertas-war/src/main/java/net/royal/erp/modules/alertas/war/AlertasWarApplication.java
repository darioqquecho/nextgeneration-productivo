package net.royal.erp.modules.alertas.war;

import net.royal.erp.modules.alertas.bootstrap.AlertasApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Implementa: - ARCH-008 Tomcat. - MOD-014 WAR/Tomcat.
 *
 * Propósito: Inicializador para desplegar royal-alertas-api.war en Tomcat
 * externo.
 */
public class AlertasWarApplication extends SpringBootServletInitializer {
	/** Implementa ARCH-008. Configura fuentes Spring para WAR. */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AlertasApplication.class);
	}
}
