package net.royal.erp.modules.hr.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Implementa: - ARCH-008 Executable Jar. - MOD-014 Modo JAR.
 */
@SpringBootApplication(scanBasePackages = "net.royal.erp")
public class RrhhApplication {
	/**
	 * Arranca RRHH como JAR ejecutable.
	 *
	 * Implementa: - ARCH-008 JAR.
	 */
	public static void main(String[] args) {
		SpringApplication.run(RrhhApplication.class, args);
	}
}
