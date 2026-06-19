package net.royal.erp.modules.rrhh.infrastructure.observability;

import java.util.concurrent.TimeUnit;

import org.slf4j.*;

import io.micrometer.core.instrument.*;
import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.observability.ObservabilityPort;

/**
 * Registra logs estructurados y metricas Micrometer por caso de uso.
 */
public class MicrometerStructuredObservabilityAdapter implements ObservabilityPort {
	private static final Logger LOGGER = LoggerFactory.getLogger(MicrometerStructuredObservabilityAdapter.class);

	private final MeterRegistry registry;

	public MicrometerStructuredObservabilityAdapter(MeterRegistry registry) {
		this.registry = registry;
	}

	@Override
	public void succeeded(FunctionalContext context, long durationMillis) {
		recordMetrics(context, durationMillis, "SUCCESS");
		LOGGER.info(
				"event=usecase.completed status=SUCCESS traceId={} requestId={} tenantId={} clientId={} userId={} module={} process={} functionality={} useCase={} requestedVersion={} language={} durationMs={}",
				context.traceId(), context.requestId(), context.tenantId(), context.clientId(), context.userId(),
				context.module(), context.process(), context.functionality(), context.useCase(),
				context.requestedVersion(), context.language(), durationMillis);
	}

	@Override
	public void failed(FunctionalContext context, long durationMillis, Throwable error) {
		recordMetrics(context, durationMillis, "ERROR");
		LOGGER.warn(
				"event=usecase.completed status=ERROR traceId={} requestId={} tenantId={} clientId={} userId={} module={} process={} functionality={} useCase={} requestedVersion={} language={} durationMs={} errorType={} errorMessage={}",
				context.traceId(), context.requestId(), context.tenantId(), context.clientId(), context.userId(),
				context.module(), context.process(), context.functionality(), context.useCase(),
				context.requestedVersion(), context.language(), durationMillis, error.getClass().getSimpleName(),
				error.getMessage());
	}

	private void recordMetrics(FunctionalContext context, long durationMillis, String status) {
		Tags tags = Tags.of("module", value(context.module()), "useCase", value(context.useCase()), "version",
				value(context.requestedVersion()), "clientId", value(context.clientId()), "status", status);
		registry.counter("royal_usecase_total", tags).increment();
		Timer.builder("royal_usecase_duration").tags(tags).publishPercentileHistogram()
				.register(registry).record(durationMillis, TimeUnit.MILLISECONDS);
		if ("ERROR".equals(status)) {
			registry.counter("royal_usecase_errors_total", tags).increment();
		}
	}

	private String value(String value) {
		return value == null || value.isBlank() ? "none" : value;
	}
}
