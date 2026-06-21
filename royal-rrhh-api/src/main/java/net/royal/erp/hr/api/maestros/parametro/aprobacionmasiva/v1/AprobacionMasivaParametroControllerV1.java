package net.royal.erp.hr.api.maestros.parametro.aprobacionmasiva.v1;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import net.royal.erp.framework.web.FunctionalContextFactory;
import net.royal.erp.framework.web.RoyalBaseController;
import net.royal.erp.hr.api.maestros.parametro.aprobacionmasiva.v1.dto.*;
import net.royal.erp.hr.application.maestros.parametro.aprobacionmasiva.usecase.AprobacionMasivaParametrosV1UseCase;
import net.royal.erp.hr.application.process.RrhhProcessCatalog;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 * API v1 para el caso de uso Aprobacion masiva de Parametros.
 */
@RestController
@RequestMapping("/api/v1/hr/maestros/parametros")
public class AprobacionMasivaParametroControllerV1 extends RoyalBaseController {
	private final AprobacionMasivaParametrosV1UseCase aprobacionMasiva;

	public AprobacionMasivaParametroControllerV1(AprobacionMasivaParametrosV1UseCase aprobacionMasiva,
			FunctionalContextFactory contextFactory) {
		super(contextFactory, RrhhProcessCatalog.MODULE,
				RrhhProcessCatalog.APROBACION_MASIVA_PARAMETROS.processName(),
				RrhhProcessCatalog.APROBACION_MASIVA_PARAMETROS.displayName());
		this.aprobacionMasiva = aprobacionMasiva;
	}

	@PostMapping(value = "/aprobacion-masiva", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AprobacionMasivaParametrosResponseV1> aprobacionMasiva(
			@Valid @RequestBody AprobacionMasivaParametrosRequestV1 request, HttpServletRequest httpRequest) {
		return ResponseEntity.ok(AprobacionMasivaParametroApiV1Mapper.toResponse(aprobacionMasiva.aprobar(
				AprobacionMasivaParametroApiV1Mapper.toCommand(request), context(httpRequest, "Aprobar"))));
	}
}
