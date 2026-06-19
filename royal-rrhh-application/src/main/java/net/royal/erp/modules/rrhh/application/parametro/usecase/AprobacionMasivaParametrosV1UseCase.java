package net.royal.erp.modules.rrhh.application.parametro.usecase;

import java.util.ArrayList;
import java.util.List;

import net.royal.erp.framework.audit.*;
import net.royal.erp.framework.kernel.*;
import net.royal.erp.modules.rrhh.application.common.UseCaseGuards;
import net.royal.erp.modules.rrhh.application.parametro.dto.*;
import net.royal.erp.modules.rrhh.application.parametro.port.AprobacionMasivaParametrosRepository;
import net.royal.erp.modules.rrhh.domain.parametro.*;

/**
 * Implementa: - MOD-012 CU-008 Aprobacion masiva de Parametros.
 */
public class AprobacionMasivaParametrosV1UseCase implements AprobacionMasivaParametrosUseCase {
	private static final String MODULE = "HR";
	private static final String ENTITY = "HR_Parametros";

	private final AprobacionMasivaParametrosRepository repository;
	private final UseCaseGuards guards;
	private final AuditPort auditPort;

	public AprobacionMasivaParametrosV1UseCase(AprobacionMasivaParametrosRepository repository, UseCaseGuards guards,
			AuditPort auditPort) {
		this.repository = repository;
		this.guards = guards;
		this.auditPort = auditPort;
	}

	@Override
	public AprobarMasivamenteParametrosResult aprobar(AprobarMasivamenteParametrosCommand command,
			FunctionalContext context) {
		guards.check(context);
		if (command == null || command.parametros() == null || command.parametros().isEmpty()) {
			throw new BusinessException("HR-PAR-APR-001");
		}
		List<AprobarParametroItemResult> results = new ArrayList<>();
		int aprobados = 0;
		for (AprobarParametroItem item : command.parametros()) {
			AprobarParametroItemResult result = aprobarItem(item, context);
			if ("APROBADO".equals(result.resultado())) {
				aprobados++;
			}
			results.add(result);
		}
		return new AprobarMasivamenteParametrosResult(command.parametros().size(), aprobados,
				command.parametros().size() - aprobados, List.copyOf(results), context.traceId());
	}

	private AprobarParametroItemResult aprobarItem(AprobarParametroItem item, FunctionalContext context) {
		ParametroId id = new ParametroId(item.compania(), item.codigo());
		return repository.findById(id).map(parametro -> aprobarEncontrado(parametro, context))
				.orElseGet(() -> new AprobarParametroItemResult(item.compania(), item.codigo(), null, null,
						"NO_ENCONTRADO"));
	}

	private AprobarParametroItemResult aprobarEncontrado(Parametro parametro, FunctionalContext context) {
		String estadoAnterior = estado(parametro);
		if (parametro.estado() != ParametroEstado.A) {
			return new AprobarParametroItemResult(parametro.id().compania(), parametro.id().codigo(), estadoAnterior,
					estadoAnterior, "OMITIDO");
		}
		if (!repository.approveIfPending(parametro.id(), context.userId(), context.executedAt())) {
			return new AprobarParametroItemResult(parametro.id().compania(), parametro.id().codigo(), estadoAnterior,
					estadoAnterior, "OMITIDO");
		}
		auditPort.register(new FunctionalAuditRecord(context.tenantId(), context.clientId(), MODULE, context.process(),
				context.useCase(), context.functionality(), "V1", context.userId(), "OK", ENTITY,
				parametro.id().value(), context.traceId(), context.requestId(), context.executedAt(), context.language(),
				null));
		return new AprobarParametroItemResult(parametro.id().compania(), parametro.id().codigo(), estadoAnterior,
				ParametroEstado.AP.name(), "APROBADO");
	}

	private String estado(Parametro parametro) {
		return parametro.estado() == null ? null : parametro.estado().name();
	}
}
