package net.royal.erp.modules.hr.application.maestros.parametro.usecase;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

import net.royal.erp.framework.application.RoyalBaseUseCase;
import net.royal.erp.framework.audit.*;
import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.security.UseCaseGuards;
import net.royal.erp.modules.hr.application.maestros.parametro.dto.*;
import net.royal.erp.modules.hr.application.maestros.parametro.port.*;
import net.royal.erp.modules.hr.domain.parametro.Parametro;

/**
 * Implementa el caso de uso Reporte de Parametros sin acoplarse al motor de
 * renderizado del documento.
 */
public class ReporteParametrosV1UseCase extends RoyalBaseUseCase implements ReporteParametrosUseCase {
	private static final String MODULE = "HR";
	private static final String ENTITY = "HR_Parametros";
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
			.withZone(ZoneId.systemDefault());

	private final ReporteParametrosRepository repository;
	private final ReporteParametrosDocumentGenerator documentGenerator;

	public ReporteParametrosV1UseCase(ReporteParametrosRepository repository,
			ReporteParametrosDocumentGenerator documentGenerator, UseCaseGuards guards, AuditPort auditPort) {
		super(MODULE, ENTITY, "V1", guards, auditPort);
		this.repository = repository;
		this.documentGenerator = documentGenerator;
	}

	@Override
	public ReporteParametrosResult ejecutar(ReporteParametrosQuery query, FunctionalContext context) {
		checkGuards(context);
		ReporteParametrosQuery filters = query == null ? new ReporteParametrosQuery(null, null, null) : query;
		ReporteParametrosDocumentData data = new ReporteParametrosDocumentData("Reporte de Parametros", context.userId(),
				context.traceId(), filtros(filters), rows(filters));
		byte[] pdf = documentGenerator.generate(data);
		auditOk(context, "REPORTE");
		return new ReporteParametrosResult(pdf, "reporte-parametros.pdf", context.traceId());
	}

	private List<ReporteParametrosDocumentRow> rows(ReporteParametrosQuery query) {
		return repository.findAll().stream().filter(p -> matches(p.id().compania(), query.compania()))
				.filter(p -> matches(p.id().codigo(), query.codigo()))
				.filter(p -> matches(p.estado() == null ? null : p.estado().name(), query.estado()))
				.map(this::toRow).toList();
	}

	private ReporteParametrosDocumentRow toRow(Parametro parametro) {
		return new ReporteParametrosDocumentRow(parametro.id().compania(), parametro.id().codigo(), parametro.nombre(),
				parametro.precio(), parametro.cantidad(),
				parametro.fechaProceso() == null ? "" : FORMATTER.format(parametro.fechaProceso()),
				parametro.estado() == null ? "" : parametro.estado().name(),
				parametro.ultimoUsuario(),
				parametro.ultimaFechaModif() == null ? "" : FORMATTER.format(parametro.ultimaFechaModif()));
	}

	private boolean matches(String value, String filter) {
		return filter == null || filter.isBlank() || Objects.equals(normalize(value), normalize(filter));
	}

	private String normalize(String value) {
		return value == null ? "" : value.trim().toUpperCase();
	}

	private String filtros(ReporteParametrosQuery query) {
		List<String> filters = new ArrayList<>();
		addFilter(filters, "Compania", query.compania());
		addFilter(filters, "Codigo", query.codigo());
		addFilter(filters, "Estado", query.estado());
		return filters.isEmpty() ? "Todos" : String.join(" | ", filters);
	}

	private void addFilter(List<String> filters, String label, String value) {
		if (value != null && !value.isBlank()) {
			filters.add(label + ": " + value.trim());
		}
	}
}
