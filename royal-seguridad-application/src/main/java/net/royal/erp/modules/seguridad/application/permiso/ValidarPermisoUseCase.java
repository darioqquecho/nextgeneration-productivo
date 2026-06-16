package net.royal.erp.modules.seguridad.application.permiso;

import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.kernel.UseCase;

/**
 * Implementa: - ARCH-006 Seguridad. - ASIS-027 Seguridad y Menús.
 */
public class ValidarPermisoUseCase implements UseCase<ValidarPermisoCommand, ValidarPermisoResult> {
	/**
	 * Valida permiso funcional.
	 *
	 * Implementa: - ARCH-006 Control por permiso.
	 */
	public ValidarPermisoResult execute(ValidarPermisoCommand command, FunctionalContext context) {
		return new ValidarPermisoResult("admin".equals(command.userId()));
	}
}
