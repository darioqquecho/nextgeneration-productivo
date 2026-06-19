package net.royal.erp.modules.hr.war;

import net.royal.erp.modules.hr.bootstrap.RrhhApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Implementa: - ARCH-008 Tomcat. - MOD-014 Modo WAR/Tomcat.
 */
public class RrhhWarApplication extends SpringBootServletInitializer {
	/**
	 * Configura aplicación para Tomcat externo.
	 *
	 * Implementa: - ARCH-008 Tomcat.
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(RrhhApplication.class);
	}
}
