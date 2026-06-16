package net.royal.erp.modules.rrhh.application.parametro.usecase;

import net.royal.erp.framework.versioning.FunctionalVersionResolver;
import net.royal.erp.modules.rrhh.application.parametro.dto.*;

/**
 * Implementa: - MOD-012 CU-001 CrearParametro. - ARCH-010 Versionamiento Funcional.
 */
public class CrearParametroVersionedUseCase extends VersionedParametroUseCase<CrearParametroCommand, CrearParametroResult>
		implements CrearParametroUseCase {
	public CrearParametroVersionedUseCase(FunctionalVersionResolver resolver, CrearParametroUseCase v1,
			CrearParametroUseCase v2) {
		super(resolver, v1, v2);
	}
}
