package net.royal.erp.modules.rrhh.application.parametro.usecase;

import net.royal.erp.framework.versioning.FunctionalVersionResolver;
import net.royal.erp.modules.rrhh.application.parametro.dto.*;

/**
 * Implementa: - MOD-012 CU-001 ActualizarParametro. - ARCH-010 Versionamiento Funcional.
 */
public class ActualizarParametroVersionedUseCase
		extends VersionedParametroUseCase<ActualizarParametroCommand, ActualizarParametroResult>
		implements ActualizarParametroUseCase {
	public ActualizarParametroVersionedUseCase(FunctionalVersionResolver resolver, ActualizarParametroUseCase v1,
			ActualizarParametroUseCase v2) {
		super(resolver, v1, v2);
	}
}
