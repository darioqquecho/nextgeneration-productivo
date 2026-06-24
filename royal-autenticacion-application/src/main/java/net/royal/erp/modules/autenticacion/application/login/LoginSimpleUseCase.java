package net.royal.erp.modules.autenticacion.application.login;

import net.royal.erp.framework.kernel.BusinessException;
import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.modules.autenticacion.domain.AutenticacionBusinessErrorCodes;

/**
 * Implementa: - ARCH-006 Seguridad e Identidad. - MOD-012 CU-004 Login.
 *
 * Propósito: Implementación mínima productiva inicial para Login local.
 */
public class LoginSimpleUseCase {
	/**
	 * Valida credenciales y emite token simple.
	 *
	 * Implementa: - ARCH-006 Autenticación local.
	 */
	public LoginResult execute(LoginCommand command, FunctionalContext context) {
		if (!"admin".equals(command.username()) || !"admin".equals(command.password())) {
			throw new BusinessException(AutenticacionBusinessErrorCodes.CREDENCIALES_INVALIDAS);
		}
		return new LoginResult("admin", "demo-jwt-token", "Bearer");
	}
}
