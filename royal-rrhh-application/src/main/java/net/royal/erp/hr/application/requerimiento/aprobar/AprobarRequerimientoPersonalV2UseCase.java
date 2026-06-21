package net.royal.erp.hr.application.requerimiento.aprobar;

import net.royal.erp.framework.audit.AuditPort;
import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.security.UseCaseGuards;

/**
 * Implementa: - ARCH-010 Versionamiento Funcional. - ASIS-028 Integración con
 * Aprobaciones.
 */
public class AprobarRequerimientoPersonalV2UseCase extends AprobarRequerimientoPersonalV1UseCase {
	private final AprobacionesPort aprobacionesPort;

	public AprobarRequerimientoPersonalV2UseCase(UseCaseGuards guards, AuditPort auditPort,
			AprobacionesPort aprobacionesPort) {
		super(guards, auditPort);
		this.aprobacionesPort = aprobacionesPort;
	}

	@Override
	public AprobarRequerimientoPersonalResult execute(AprobarRequerimientoPersonalCommand command,
			FunctionalContext context) {
		aprobacionesPort.aprobar(command.codigoRequerimiento(), command.usuarioAprobador(), command.accion(),
				command.comentario());
		AprobarRequerimientoPersonalResult r = super.execute(command, context);
		return new AprobarRequerimientoPersonalResult(r.codigoRequerimiento(), r.estado(), "V2", r.traceId());
	}
}
