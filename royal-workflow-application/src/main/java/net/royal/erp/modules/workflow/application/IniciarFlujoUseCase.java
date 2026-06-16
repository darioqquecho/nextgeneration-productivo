package net.royal.erp.modules.workflow.application;

import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.kernel.UseCase;

/**
 * Implementa: - ASIS-028 Workflow y Aprobaciones. - ARCH-018 Reglas de
 * Dependencia.
 */
public class IniciarFlujoUseCase implements UseCase<IniciarFlujoCommand, IniciarFlujoResult> {
	/**
	 * Ejecuta operación funcional mínima del módulo Workflow.
	 *
	 * Implementa: - ASIS-028.
	 */
	public IniciarFlujoResult execute(IniciarFlujoCommand command, FunctionalContext context) {
		return new IniciarFlujoResult(command.businessKey(), "OK");
	}
}
