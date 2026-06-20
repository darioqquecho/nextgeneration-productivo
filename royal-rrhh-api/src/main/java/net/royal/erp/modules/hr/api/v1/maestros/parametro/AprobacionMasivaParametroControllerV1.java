package net.royal.erp.modules.hr.api.v1.maestros.parametro;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import net.royal.erp.framework.web.FunctionalContextFactory;
import net.royal.erp.framework.web.RoyalBaseController;
import net.royal.erp.modules.hr.api.v1.maestros.parametro.dto.*;
import net.royal.erp.modules.hr.application.maestros.parametro.usecase.AprobacionMasivaParametrosUseCase;
import net.royal.erp.modules.hr.application.process.RrhhProcessCatalog;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 * API v1 para el caso de uso Aprobacion masiva de Parametros.
 */
@RestController
@RequestMapping("/api/v1/hr/maestros/parametros")
public class AprobacionMasivaParametroControllerV1 extends RoyalBaseController {
	private final AprobacionMasivaParametrosUseCase aprobacionMasiva;

	public AprobacionMasivaParametroControllerV1(AprobacionMasivaParametrosUseCase aprobacionMasiva,
			FunctionalContextFactory contextFactory) {
		super(contextFactory, RrhhProcessCatalog.MODULE,
				RrhhProcessCatalog.APROBACION_MASIVA_PARAMETROS.processName(),
				RrhhProcessCatalog.APROBACION_MASIVA_PARAMETROS.displayName());
		this.aprobacionMasiva = aprobacionMasiva;
	}

	@PostMapping(value = "/aprobacion-masiva", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AprobacionMasivaParametrosResponseV1> aprobacionMasiva(
			@Valid @RequestBody AprobacionMasivaParametrosRequestV1 request, HttpServletRequest httpRequest) {
		return ResponseEntity.ok(ParametroApiV1Mapper.toResponse(aprobacionMasiva.aprobar(
				ParametroApiV1Mapper.toCommand(request), context(httpRequest, "Aprobar"))));
	}
}
