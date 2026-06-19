package net.royal.erp.modules.rrhh.api.v1.parametro;

import jakarta.servlet.http.HttpServletRequest;
import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.modules.rrhh.api.FunctionalContextFactory;
import net.royal.erp.modules.rrhh.api.v1.parametro.dto.ReporteParametrosRequestV1;
import net.royal.erp.modules.rrhh.application.parametro.dto.ReporteParametrosResult;
import net.royal.erp.modules.rrhh.application.parametro.usecase.ReporteParametrosUseCase;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 * API v1 para el caso de uso Reporte de Parametro.
 */
@RestController
@RequestMapping("/api/v1/hr/maestros/parametros")
public class ReporteParametroControllerV1 {
	private static final String PROCESS = "Maestros";
	private static final String USE_CASE = "Reporte de Parametro";

	private final ReporteParametrosUseCase reporte;
	private final FunctionalContextFactory contextFactory;

	public ReporteParametroControllerV1(ReporteParametrosUseCase reporte, FunctionalContextFactory contextFactory) {
		this.reporte = reporte;
		this.contextFactory = contextFactory;
	}

	@PostMapping(value = "/reporte/pdf", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> reportePdf(@RequestBody(required = false) ReporteParametrosRequestV1 request,
			HttpServletRequest httpRequest) {
		ReporteParametrosResult result = reporte.ejecutar(ParametroApiV1Mapper.toQuery(request),
				context(httpRequest, "Reporte"));
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + result.fileName() + "\"")
				.contentType(MediaType.APPLICATION_PDF).body(result.pdf());
	}

	private FunctionalContext context(HttpServletRequest request, String functionality) {
		return contextFactory.from(request, PROCESS, functionality, USE_CASE);
	}
}
