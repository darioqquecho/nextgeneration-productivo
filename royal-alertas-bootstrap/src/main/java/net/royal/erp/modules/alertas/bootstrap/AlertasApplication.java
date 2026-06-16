package net.royal.erp.modules.alertas.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Implementa: - ARCH-008 Executable Jar. - MOD-014 Modos de ejecución
 * obligatorios.
 *
 * Propósito: Arranque del módulo Alertas migrado.
 */
@EnableScheduling
@SpringBootApplication(scanBasePackages = "net.royal.erp")
public class AlertasApplication {
	/** Implementa ARCH-008. Arranca como JAR. */
	public static void main(String[] args) {
		SpringApplication.run(AlertasApplication.class, args);
	}
}
