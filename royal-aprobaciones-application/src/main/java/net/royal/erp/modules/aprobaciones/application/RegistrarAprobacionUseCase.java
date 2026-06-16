package net.royal.erp.modules.aprobaciones.application;

import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.kernel.UseCase;

/**
 * Implementa: - ASIS-028 Workflow y Aprobaciones. - ARCH-018 Reglas de
 * Dependencia.
 */
public class RegistrarAprobacionUseCase implements UseCase<RegistrarAprobacionCommand, RegistrarAprobacionResult> {
	/**
	 * Ejecuta operación funcional mínima del módulo Aprobaciones.
	 *
	 * Implementa: - ASIS-028.
	 */
	public RegistrarAprobacionResult execute(RegistrarAprobacionCommand command, FunctionalContext context) {
		return new RegistrarAprobacionResult(command.businessKey(), "OK");
	}
}
