package net.royal.erp.modules.rrhh.application.parametro.usecase;

import net.royal.erp.framework.versioning.FunctionalVersionResolver;
import net.royal.erp.modules.rrhh.application.parametro.dto.*;

/**
 * Implementa: - MOD-012 CU-001 ObtenerParametro. - ARCH-010 Versionamiento Funcional.
 */
public class ObtenerParametroVersionedUseCase extends VersionedParametroUseCase<ObtenerParametroQuery, ParametroResult>
		implements ObtenerParametroUseCase {
	public ObtenerParametroVersionedUseCase(FunctionalVersionResolver resolver, ObtenerParametroUseCase v1,
			ObtenerParametroUseCase v2) {
		super(resolver, v1, v2);
	}
}
