package net.royal.erp.modules.hr.application.parametro.usecase;

import java.util.Objects;

import net.royal.erp.framework.audit.*;
import net.royal.erp.framework.kernel.*;
import net.royal.erp.framework.security.UseCaseGuards;
import net.royal.erp.modules.hr.application.parametro.dto.*;
import net.royal.erp.modules.hr.application.parametro.port.MantenimientoTablaParametrosRepository;
import net.royal.erp.modules.hr.domain.parametro.*;

/**
 * Implementa: - MOD-012 CU-001 Mantenimiento de Tabla Parametros V1.
 */
public class MantenimientoTablaParametrosV1UseCase implements MantenimientoTablaParametrosUseCase {
	private static final String MODULE = "HR";
	private static final String ENTITY = "HR_Parametros";

	private final MantenimientoTablaParametrosRepository repository;
	private final UseCaseGuards guards;
	private final AuditPort auditPort;

	public MantenimientoTablaParametrosV1UseCase(MantenimientoTablaParametrosRepository repository, UseCaseGuards guards,
			AuditPort auditPort) {
		this.repository = repository;
		this.guards = guards;
		this.auditPort = auditPort;
	}

	protected String functionalVersion() {
		return "V1";
	}

	@Override
	public ListarParametrosResult listar(ListarParametrosQuery query, FunctionalContext context) {
		guards.check(context);
		ListarParametrosQuery filters = query == null ? new ListarParametrosQuery(null, null, null) : query;
		ListarParametrosResult result = new ListarParametrosResult(repository.findAll().stream()
				.filter(p -> matches(p.id().compania(), filters.compania()))
				.filter(p -> matches(p.id().codigo(), filters.codigo()))
				.filter(p -> matches(p.estado() == null ? null : p.estado().name(), filters.estado()))
				.map(p -> ParametroResultMapper.toResult(p, context.traceId())).toList(), context.traceId());
		registerAudit(context, "LISTAR", "registros=" + result.parametros().size());
		return result;
	}

	@Override
	public CrearParametroResult crear(CrearParametroCommand command, FunctionalContext context) {
		guards.check(context);
		ParametroId id = new ParametroId(command.compania(), command.codigo());
		if (repository.existsById(id)) {
			throw new BusinessException("HR-PAR-004");
		}
		Parametro parametro = Parametro.crear(command.compania(), command.codigo(), command.nombre(), command.precio(),
				command.cantidad(), command.fechaProceso(), context.userId(), context.executedAt());
		repository.save(parametro);
		registerAudit(context, id);
		return new CrearParametroResult(command.compania(), command.codigo(), "CREADO", context.traceId());
	}

	@Override
	public ParametroResult obtener(ObtenerParametroQuery query, FunctionalContext context) {
		guards.check(context);
		Parametro parametro = repository.findById(new ParametroId(query.compania(), query.codigo()))
				.orElseThrow(() -> new BusinessException("HR-PAR-404"));
		registerAudit(context, parametro.id());
		return ParametroResultMapper.toResult(parametro, context.traceId());
	}

	@Override
	public ActualizarParametroResult actualizar(ActualizarParametroCommand command, FunctionalContext context) {
		guards.check(context);
		ParametroId id = new ParametroId(command.compania(), command.codigo());
		Parametro parametro = repository.findById(id)
				.orElseThrow(() -> new BusinessException("HR-PAR-404"));
		parametro.actualizarDatos(command.nombre(), command.precio(), command.cantidad(), command.fechaProceso(),
				context.userId(), context.executedAt());
		repository.save(parametro);
		registerAudit(context, id);
		return new ActualizarParametroResult(command.compania(), command.codigo(), "ACTUALIZADO", context.traceId());
	}

	@Override
	public ParametroResult cambiarEstado(CambiarEstadoParametroCommand command, FunctionalContext context) {
		guards.check(context);
		ParametroId id = new ParametroId(command.compania(), command.codigo());
		Parametro parametro = repository.findById(id)
				.orElseThrow(() -> new BusinessException("HR-PAR-404"));
		parametro.cambiarEstado(ParametroEstado.valueOf(command.estado().toUpperCase()), context.userId(),
				context.executedAt());
		repository.save(parametro);
		registerAudit(context, id);
		return ParametroResultMapper.toResult(parametro, context.traceId());
	}

	@Override
	public EliminarParametroResult eliminar(EliminarParametroCommand command, FunctionalContext context) {
		guards.check(context);
		ParametroId id = new ParametroId(command.compania(), command.codigo());
		repository.deleteById(id);
		registerAudit(context, id);
		return new EliminarParametroResult(command.compania(), command.codigo(), true, context.traceId());
	}

	private void registerAudit(FunctionalContext context, ParametroId id) {
		registerAudit(context, id.value(), null);
	}

	private void registerAudit(FunctionalContext context, String entityId, String message) {
		auditPort.register(new FunctionalAuditRecord(context.tenantId(), context.clientId(), MODULE, context.process(),
				context.useCase(), context.functionality(), functionalVersion(), context.userId(), "OK", ENTITY,
				entityId, context.traceId(), context.requestId(), context.executedAt(), context.language(), message));
	}

	private boolean matches(String value, String filter) {
		return filter == null || filter.isBlank() || Objects.equals(normalize(value), normalize(filter));
	}

	private String normalize(String value) {
		return value == null ? "" : value.trim().toUpperCase();
	}
}
