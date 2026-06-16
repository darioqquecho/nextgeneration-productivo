package net.royal.erp.modules.aprobaciones.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Implementa: - ARCH-008 Executable Jar.
 *
 * Propósito: Arranque del módulo Core Aprobaciones.
 */
@SpringBootApplication(scanBasePackages = "net.royal.erp")
public class AprobacionesApplication {
	/**
	 * Ejecuta aplicación.
	 *
	 * Implementa: - ARCH-008 JAR.
	 */
	public static void main(String[] args) {
		SpringApplication.run(AprobacionesApplication.class, args);
	}
}
