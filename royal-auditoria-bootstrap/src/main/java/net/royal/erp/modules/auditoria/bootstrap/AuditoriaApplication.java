package net.royal.erp.modules.auditoria.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Implementa: - ARCH-008 Executable Jar.
 *
 * Propósito: Arranque del módulo Core Auditoria.
 */
@SpringBootApplication(scanBasePackages = "net.royal.erp")
public class AuditoriaApplication {
	/**
	 * Ejecuta aplicación.
	 *
	 * Implementa: - ARCH-008 JAR.
	 */
	public static void main(String[] args) {
		SpringApplication.run(AuditoriaApplication.class, args);
	}
}
