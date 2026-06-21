package net.royal.erp.modules.autenticacion.bootstrap;

import net.royal.erp.modules.autenticacion.application.login.*;
import net.royal.erp.modules.autenticacion.application.permiso.*;
import net.royal.erp.framework.security.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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
	LoginSimpleUseCase loginUseCase() {
		return new LoginSimpleUseCase();
	}

	@Bean
	ObtenerPermisoSimpleUseCase obtenerPermisoUseCase(PermissionChecker permissionChecker) {
		return new ObtenerPermisoSimpleUseCase(permissionChecker);
	}

	@Bean
	@ConditionalOnMissingBean(PermissionChecker.class)
	PermissionChecker permissionChecker() {
		InMemoryPermissionChecker checker = new InMemoryPermissionChecker();
		for (String permiso : new String[] { "HR_MANTENIMIENTO_DE_PARAMETRO", "HR_REPORTE_DE_PARAMETRO",
				"HR_APROBACION_MASIVA_DE_PARAMETROS", "HR_MANTENIMIENTO_DE_TIPO_SEGURO", "HR_REGISTRAR_CAPACITACION",
				"HR_APROBAR_REQUERIMIENTO_PERSONAL" }) {
			checker.grant("admin", permiso);
		}
		return checker;
	}
}
