package net.royal.erp.modules.alertas.bootstrap;

import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.modules.alertas.application.dto.NamedQueryCommand;
import net.royal.erp.modules.alertas.application.usecase.ProcesarCicloAlertasUseCase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.Instant;
import java.util.Map;

/**
 * Implementa: - ASIS-017 Procesos Batch y Scheduler. - ARCH-008 Modos de
 * Ejecución.
 *
 * Propósito: Scheduler migrado. No contiene reglas de negocio; delega a caso de
 * uso.
 */
@Component
public class AlertasScheduler {
	private final ProcesarCicloAlertasUseCase useCase;
	private final boolean enabled;

	public AlertasScheduler(ProcesarCicloAlertasUseCase useCase,
			@Value("${royal.alertas.scheduler.enabled:false}") boolean enabled) {
		this.useCase = useCase;
		this.enabled = enabled;
	}

	/** Ejecuta ciclo programado si está habilitado por properties. */
	@Scheduled(fixedDelayString = "${royal.alertas.scheduler.fixed-delay-ms:60000}")
	public void run() {
		if (!enabled)
			return;
		useCase.execute(new NamedQueryCommand("alerta.cambiarEjecutandoEvaluarRegla", Map.of("p_estado", "N")),
				new FunctionalContext("default", "demo-client", "scheduler", "ALERTAS", "Scheduler", "Procesar Ciclo",
						"ProcesarCicloAlertasUseCase", null, null, null, Instant.now()));
	}
}
