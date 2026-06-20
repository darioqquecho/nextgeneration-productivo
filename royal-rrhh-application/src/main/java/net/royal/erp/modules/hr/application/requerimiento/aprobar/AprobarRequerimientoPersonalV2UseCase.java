package net.royal.erp.modules.hr.application.requerimiento.aprobar;

import net.royal.erp.framework.application.RoyalDelegatingUseCase;
import net.royal.erp.framework.kernel.FunctionalContext;

/**
 * Implementa: - ARCH-010 Versionamiento Funcional. - ASIS-028 Integración con
 * Aprobaciones.
 */
public class AprobarRequerimientoPersonalV2UseCase extends RoyalDelegatingUseCase
		implements AprobarRequerimientoPersonalUseCase {
	private final AprobarRequerimientoPersonalV1UseCase base;
	private final AprobacionesPort aprobacionesPort;

	public AprobarRequerimientoPersonalV2UseCase(AprobarRequerimientoPersonalV1UseCase base,
			AprobacionesPort aprobacionesPort) {
		this.base = base;
		this.aprobacionesPort = aprobacionesPort;
	}

	public AprobarRequerimientoPersonalResult execute(AprobarRequerimientoPersonalCommand command,
			FunctionalContext context) {
		aprobacionesPort.aprobar(command.codigoRequerimiento(), command.usuarioAprobador(), command.accion(),
				command.comentario());
		AprobarRequerimientoPersonalResult r = base.execute(command, context);
		return new AprobarRequerimientoPersonalResult(r.codigoRequerimiento(), r.estado(), "V2", r.traceId());
	}
}
