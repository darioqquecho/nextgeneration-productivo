package net.royal.erp.hr.application.capacitacion.registrar;

import net.royal.erp.framework.application.RoyalBaseUseCase;
import net.royal.erp.framework.audit.*;
import net.royal.erp.framework.kernel.*;
import net.royal.erp.framework.security.UseCaseGuards;
import net.royal.erp.hr.application.capacitacion.registrar.port.CapacitacionRepository;
import net.royal.erp.hr.application.process.RrhhProcessCatalog;
import net.royal.erp.hr.domain.RrhhBusinessErrorCodes;
import net.royal.erp.hr.domain.capacitacion.*;

/**
 * Implementa: - ARCH-010 Versionamiento Funcional. - MOD-012 CU-002
 * RegistrarCapacitacion.
 */
public class RegistrarCapacitacionV1UseCase extends RoyalBaseUseCase {
	private final CapacitacionRepository repository;

	public RegistrarCapacitacionV1UseCase(CapacitacionRepository repository, UseCaseGuards guards,
			AuditPort auditPort) {
		super(RrhhProcessCatalog.MODULE, "Capacitacion", "V1", guards, auditPort);
		this.repository = repository;
	}

	public RegistrarCapacitacionResult execute(RegistrarCapacitacionCommand command, FunctionalContext context) {
		checkGuards(context);
		if (repository.existsByCodigo(command.codigo()))
			throw new BusinessException(RrhhBusinessErrorCodes.CAPACITACION_DUPLICADA);
		Capacitacion c = Capacitacion.registrar(command.codigo(), command.nombre(), command.fechaInicio(),
				command.fechaFin(), command.instructor());
		repository.save(c);
		auditOk(context, command.codigo());
		return new RegistrarCapacitacionResult(command.codigo(), "REGISTRADA", "V1", context.traceId());
	}
}
