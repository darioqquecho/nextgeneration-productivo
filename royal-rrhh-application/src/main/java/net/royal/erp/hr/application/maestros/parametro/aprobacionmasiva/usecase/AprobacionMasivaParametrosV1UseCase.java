package net.royal.erp.hr.application.maestros.parametro.aprobacionmasiva.usecase;

import java.util.ArrayList;
import java.util.List;

import net.royal.erp.framework.application.RoyalBaseUseCase;
import net.royal.erp.framework.audit.*;
import net.royal.erp.framework.kernel.*;
import net.royal.erp.framework.security.UseCaseGuards;
import net.royal.erp.hr.application.maestros.parametro.aprobacionmasiva.dto.*;
import net.royal.erp.hr.application.maestros.parametro.aprobacionmasiva.port.AprobacionMasivaParametrosRepository;
import net.royal.erp.hr.domain.parametro.*;

/**
 * Implementa: - MOD-012 CU-008 Aprobacion masiva de Parametros.
 */
public class AprobacionMasivaParametrosV1UseCase extends RoyalBaseUseCase {
	private static final String MODULE = "HR";
	private static final String ENTITY = "HR_Parametros";

	private final AprobacionMasivaParametrosRepository repository;

	public AprobacionMasivaParametrosV1UseCase(AprobacionMasivaParametrosRepository repository, UseCaseGuards guards,
			AuditPort auditPort) {
		super(MODULE, ENTITY, "V1", guards, auditPort);
		this.repository = repository;
	}

	public AprobarMasivamenteParametrosResult aprobar(AprobarMasivamenteParametrosCommand command,
			FunctionalContext context) {
		checkGuards(context);
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
		int omitidos = command.parametros().size() - aprobados;
		auditOk(context, "APROBACION_MASIVA",
				"solicitados=" + command.parametros().size() + "; aprobados=" + aprobados + "; omitidos=" + omitidos);
		return new AprobarMasivamenteParametrosResult(command.parametros().size(), aprobados, omitidos,
				List.copyOf(results), context.traceId());
	}

	private AprobarParametroItemResult aprobarItem(AprobarParametroItem item, FunctionalContext context) {
		ParametroId id = new ParametroId(item.compania(), item.codigo());
		return repository.findById(id).map(parametro -> aprobarEncontrado(parametro, context)).orElseGet(
				() -> new AprobarParametroItemResult(item.compania(), item.codigo(), null, null, "NO_ENCONTRADO"));
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
		auditOk(context, parametro.id().value());
		return new AprobarParametroItemResult(parametro.id().compania(), parametro.id().codigo(), estadoAnterior,
				ParametroEstado.AP.name(), "APROBADO");
	}

	private String estado(Parametro parametro) {
		return parametro.estado() == null ? null : parametro.estado().name();
	}
}
