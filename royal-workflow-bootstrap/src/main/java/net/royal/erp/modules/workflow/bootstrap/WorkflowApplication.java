package net.royal.erp.modules.workflow.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Implementa: - ARCH-008 Executable Jar.
 *
 * Propósito: Arranque del módulo Core Workflow.
 */
@SpringBootApplication(scanBasePackages = "net.royal.erp")
public class WorkflowApplication {
	/**
	 * Ejecuta aplicación.
	 *
	 * Implementa: - ARCH-008 JAR.
	 */
	public static void main(String[] args) {
		SpringApplication.run(WorkflowApplication.class, args);
	}
}
