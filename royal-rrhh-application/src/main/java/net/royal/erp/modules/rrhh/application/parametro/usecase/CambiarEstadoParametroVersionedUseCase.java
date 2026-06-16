package net.royal.erp.modules.rrhh.application.parametro.usecase;

import net.royal.erp.framework.versioning.FunctionalVersionResolver;
import net.royal.erp.modules.rrhh.application.parametro.dto.*;

/**
 * Implementa: - MOD-012 CU-001 CambiarEstadoParametro. - ARCH-010 Versionamiento Funcional.
 */
public class CambiarEstadoParametroVersionedUseCase
		extends VersionedParametroUseCase<CambiarEstadoParametroCommand, ParametroResult>
		implements CambiarEstadoParametroUseCase {
	public CambiarEstadoParametroVersionedUseCase(FunctionalVersionResolver resolver, CambiarEstadoParametroUseCase v1,
			CambiarEstadoParametroUseCase v2) {
		super(resolver, v1, v2);
	}
}
