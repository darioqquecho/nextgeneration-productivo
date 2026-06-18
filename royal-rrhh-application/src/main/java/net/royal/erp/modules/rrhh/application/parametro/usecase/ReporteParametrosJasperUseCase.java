package net.royal.erp.modules.rrhh.application.parametro.usecase;

import java.io.InputStream;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

import net.royal.erp.framework.audit.*;
import net.royal.erp.framework.kernel.*;
import net.royal.erp.modules.rrhh.application.common.UseCaseGuards;
import net.royal.erp.modules.rrhh.application.parametro.dto.*;
import net.royal.erp.modules.rrhh.application.parametro.port.ReporteParametrosRepository;
import net.royal.erp.modules.rrhh.domain.parametro.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Implementa: - MOD-012 CU-007 Reporte de Parametros. - ARCH-015 Auditoria
 * funcional.
 */
public class ReporteParametrosJasperUseCase implements ReporteParametrosUseCase {
	private static final String REPORT_PATH = "reports/rrhh/parametros.jrxml";
	private static final String MODULE = "HR";

	private final ReporteParametrosRepository repository;
	private final UseCaseGuards guards;
	private final AuditPort auditPort;

	public ReporteParametrosJasperUseCase(ReporteParametrosRepository repository, UseCaseGuards guards, AuditPort auditPort) {
		this.repository = repository;
		this.guards = guards;
		this.auditPort = auditPort;
	}

	@Override
	public ReporteParametrosResult ejecutar(ReporteParametrosQuery query, FunctionalContext context) {
		guards.check(context);
		ReporteParametrosQuery filters = query == null ? new ReporteParametrosQuery(null, null, null) : query;
		try (InputStream template = getClass().getClassLoader().getResourceAsStream(REPORT_PATH)) {
			if (template == null) {
				throw new BusinessException("HR-PAR-REP-001");
			}
			JasperReport report = JasperCompileManager.compileReport(template);
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("TITULO", "Reporte de Parametros");
			parameters.put("USUARIO", context.userId());
			parameters.put("TRACE_ID", context.traceId());
			parameters.put("FILTROS", filtros(filters));

			JasperPrint print = JasperFillManager.fillReport(report, parameters,
					new JRBeanCollectionDataSource(rows(filters)));
			byte[] pdf = JasperExportManager.exportReportToPdf(print);
			auditPort.register(new FunctionalAuditRecord(MODULE, context.process(), context.useCase(),
					context.functionality(), "V1", context.userId(), "OK", "HR_Parametros", "REPORTE", context.traceId(),
					context.requestId(), context.executedAt()));
			return new ReporteParametrosResult(pdf, "reporte-parametros.pdf", context.traceId());
		} catch (JRException e) {
			throw new BusinessException("HR-PAR-REP-002");
		} catch (java.io.IOException e) {
			throw new BusinessException("HR-PAR-REP-003");
		}
	}

	private List<ParametroReporteRow> rows(ReporteParametrosQuery query) {
		return repository.findAll().stream().filter(p -> matches(p.id().compania(), query.compania()))
				.filter(p -> matches(p.id().codigo(), query.codigo()))
				.filter(p -> matches(p.estado() == null ? null : p.estado().name(), query.estado()))
				.map(ParametroReporteRow::from).toList();
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

	public static class ParametroReporteRow {
		private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
				.withZone(ZoneId.systemDefault());

		private final String compania;
		private final String codigo;
		private final String nombre;
		private final String estado;
		private final String ultimoUsuario;
		private final String ultimaFechaModif;

		private ParametroReporteRow(String compania, String codigo, String nombre, String estado, String ultimoUsuario,
				String ultimaFechaModif) {
			this.compania = compania;
			this.codigo = codigo;
			this.nombre = nombre;
			this.estado = estado;
			this.ultimoUsuario = ultimoUsuario;
			this.ultimaFechaModif = ultimaFechaModif;
		}

		private static ParametroReporteRow from(Parametro parametro) {
			return new ParametroReporteRow(parametro.id().compania(), parametro.id().codigo(), parametro.nombre(),
					parametro.estado() == null ? "" : parametro.estado().name(), parametro.ultimoUsuario(),
					parametro.ultimaFechaModif() == null ? "" : FORMATTER.format(parametro.ultimaFechaModif()));
		}

		public String getCompania() {
			return compania;
		}

		public String getCodigo() {
			return codigo;
		}

		public String getNombre() {
			return nombre;
		}

		public String getEstado() {
			return estado;
		}

		public String getUltimoUsuario() {
			return ultimoUsuario;
		}

		public String getUltimaFechaModif() {
			return ultimaFechaModif;
		}
	}
}
