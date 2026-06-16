package net.royal.erp.modules.seguridad.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Implementa: - ARCH-008 Executable Jar.
 *
 * Propósito: Arranque del módulo Core Seguridad.
 */
@SpringBootApplication(scanBasePackages = "net.royal.erp")
public class SeguridadApplication {
	/**
	 * Ejecuta aplicación.
	 *
	 * Implementa: - ARCH-008 JAR.
	 */
	public static void main(String[] args) {
		SpringApplication.run(SeguridadApplication.class, args);
	}
}
