package net.royal.erp.modules.hr.api.v1.maestros.parametro;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import net.royal.erp.framework.web.FunctionalContextFactory;
import net.royal.erp.framework.web.RoyalBaseController;
import net.royal.erp.modules.hr.api.v1.maestros.parametro.dto.ReporteParametrosRequestV1;
import net.royal.erp.modules.hr.application.maestros.parametro.dto.ReporteParametrosResult;
import net.royal.erp.modules.hr.application.maestros.parametro.usecase.ReporteParametrosUseCase;
import net.royal.erp.modules.hr.application.process.RrhhProcessCatalog;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 * API v1 para el caso de uso Reporte de Parametro.
 */
@RestController
@RequestMapping("/api/v1/hr/maestros/parametros")
public class ReporteParametroControllerV1 extends RoyalBaseController {
	private final ReporteParametrosUseCase reporte;

	public ReporteParametroControllerV1(ReporteParametrosUseCase reporte, FunctionalContextFactory contextFactory) {
		super(contextFactory, RrhhProcessCatalog.MODULE, RrhhProcessCatalog.REPORTE_PARAMETRO.processName(),
				RrhhProcessCatalog.REPORTE_PARAMETRO.displayName());
		this.reporte = reporte;
	}

	@PostMapping(value = "/reporte/pdf", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> reportePdf(@Valid @RequestBody(required = false) ReporteParametrosRequestV1 request,
			HttpServletRequest httpRequest) {
		ReporteParametrosResult result = reporte.ejecutar(ParametroApiV1Mapper.toQuery(request),
				context(httpRequest, "Reporte"));
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + result.fileName() + "\"")
				.contentType(MediaType.APPLICATION_PDF).body(result.pdf());
	}
}
