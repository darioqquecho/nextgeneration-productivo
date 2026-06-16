package net.royal.erp.modules.rrhh.application.parametro.usecase;

import net.royal.erp.framework.versioning.FunctionalVersionResolver;
import net.royal.erp.modules.rrhh.application.parametro.dto.*;

/**
 * Implementa: - MOD-012 CU-001 EliminarParametro. - ARCH-010 Versionamiento Funcional.
 */
public class EliminarParametroVersionedUseCase
		extends VersionedParametroUseCase<EliminarParametroCommand, EliminarParametroResult>
		implements EliminarParametroUseCase {
	public EliminarParametroVersionedUseCase(FunctionalVersionResolver resolver, EliminarParametroUseCase v1,
			EliminarParametroUseCase v2) {
		super(resolver, v1, v2);
	}
}
