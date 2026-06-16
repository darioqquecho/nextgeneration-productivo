package net.royal.erp.modules.autenticacion.application.login;

import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.kernel.UseCase;

/**
 * Implementa: - ARCH-006 Seguridad e Identidad. - MOD-012 CU-004 Login.
 */
public interface LoginUseCase extends UseCase<LoginCommand, LoginResult> {
	/**
	 * Ejecuta autenticación.
	 *
	 * Implementa: - ARCH-006 Seguridad.
	 */
	LoginResult execute(LoginCommand command, FunctionalContext context);
}
