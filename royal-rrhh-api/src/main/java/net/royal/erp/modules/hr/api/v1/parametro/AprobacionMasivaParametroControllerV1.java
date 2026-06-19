package net.royal.erp.modules.hr.api.v1.parametro;

import jakarta.servlet.http.HttpServletRequest;
import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.web.FunctionalContextFactory;
import net.royal.erp.modules.hr.api.v1.parametro.dto.*;
import net.royal.erp.modules.hr.application.parametro.usecase.AprobacionMasivaParametrosUseCase;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 * API v1 para el caso de uso Aprobacion masiva de Parametros.
 */
@RestController
@RequestMapping("/api/v1/hr/maestros/parametros")
public class AprobacionMasivaParametroControllerV1 {
	private static final String MODULE = "HR";
	private static final String PROCESS = "Maestros";
	private static final String USE_CASE = "Aprobacion masiva de Parametros";

	private final AprobacionMasivaParametrosUseCase aprobacionMasiva;
	private final FunctionalContextFactory contextFactory;

	public AprobacionMasivaParametroControllerV1(AprobacionMasivaParametrosUseCase aprobacionMasiva,
			FunctionalContextFactory contextFactory) {
		this.aprobacionMasiva = aprobacionMasiva;
		this.contextFactory = contextFactory;
	}

	@PostMapping(value = "/aprobacion-masiva", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AprobacionMasivaParametrosResponseV1> aprobacionMasiva(
			@RequestBody AprobacionMasivaParametrosRequestV1 request, HttpServletRequest httpRequest) {
		return ResponseEntity.ok(ParametroApiV1Mapper.toResponse(aprobacionMasiva.aprobar(
				ParametroApiV1Mapper.toCommand(request), context(httpRequest, "Aprobar"))));
	}

	private FunctionalContext context(HttpServletRequest request, String functionality) {
		return contextFactory.from(request, MODULE, PROCESS, functionality, USE_CASE);
	}
}
