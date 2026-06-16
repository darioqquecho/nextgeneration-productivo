package net.royal.erp.modules.autenticacion.bootstrap;

import net.royal.erp.modules.autenticacion.application.login.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Implementa: - ARCH-008 Bootstrap.
 */
@Configuration
public class AutenticacionConfiguration {
	/**
	 * Expone caso de uso Login.
	 *
	 * Implementa: - MOD-012 CU-004 Login.
	 */
	@Bean
	LoginUseCase loginUseCase() {
		return new LoginSimpleUseCase();
	}
}
