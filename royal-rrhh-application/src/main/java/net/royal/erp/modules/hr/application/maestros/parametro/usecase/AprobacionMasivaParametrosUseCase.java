package net.royal.erp.modules.hr.application.maestros.parametro.usecase;

import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.modules.hr.application.maestros.parametro.dto.*;

/**
 * Implementa: - MOD-012 CU-008 Aprobacion masiva de Parametros.
 */
public interface AprobacionMasivaParametrosUseCase {
	AprobarMasivamenteParametrosResult aprobar(AprobarMasivamenteParametrosCommand command, FunctionalContext context);
}
