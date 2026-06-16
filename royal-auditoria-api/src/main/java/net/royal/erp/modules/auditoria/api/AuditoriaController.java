package net.royal.erp.modules.auditoria.api;

import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.modules.auditoria.application.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;

/**
 * Implementa: - MOD-012 CU-005 ConsultarAuditoria.
 */
@RestController
@RequestMapping("/api/auditoria")
public class AuditoriaController {
	private final ConsultarAuditoriaUseCase useCase;

	/**
	 * Crea controller de auditoría.
	 *
	 * Implementa: - ARCH-012 API.
	 */
	public AuditoriaController(ConsultarAuditoriaUseCase useCase) {
		this.useCase = useCase;
	}

	/**
	 * Consulta auditoría por TraceId.
	 *
	 * Implementa: - MOD-012 CU-005 ConsultarAuditoria.
	 */
	@GetMapping("/{traceId}")
	public ResponseEntity<ConsultarAuditoriaResult> consultar(@PathVariable String traceId) {
		FunctionalContext context = new FunctionalContext("default", "demo-client", "admin", "AUDITORIA", "Consulta",
				"Consultar Auditoría", "ConsultarAuditoriaUseCase", null, traceId, null, Instant.now());
		return ResponseEntity.ok(useCase.execute(new ConsultarAuditoriaQuery(traceId), context));
	}
}
