package net.royal.erp.modules.autenticacion.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Implementa: - ARCH-008 Executable Jar.
 */
@SpringBootApplication(scanBasePackages = "net.royal.erp")
public class AutenticacionApplication {
	/**
	 * Arranca módulo Autenticación.
	 *
	 * Implementa: - ARCH-008 JAR.
	 */
	public static void main(String[] args) {
		SpringApplication.run(AutenticacionApplication.class, args);
	}
}
