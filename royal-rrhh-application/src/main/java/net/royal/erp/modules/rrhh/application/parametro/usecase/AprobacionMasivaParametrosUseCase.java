package net.royal.erp.modules.rrhh.application.parametro.usecase;

import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.modules.rrhh.application.parametro.dto.*;

/**
 * Implementa: - MOD-012 CU-008 Aprobacion masiva de Parametros.
 */
public interface AprobacionMasivaParametrosUseCase {
	AprobarMasivamenteParametrosResult aprobar(AprobarMasivamenteParametrosCommand command, FunctionalContext context);
}
