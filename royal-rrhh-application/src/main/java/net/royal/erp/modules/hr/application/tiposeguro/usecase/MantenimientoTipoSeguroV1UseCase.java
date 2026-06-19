package net.royal.erp.modules.hr.application.tiposeguro.usecase;

import java.util.Objects;

import net.royal.erp.framework.audit.*;
import net.royal.erp.framework.kernel.*;
import net.royal.erp.framework.security.UseCaseGuards;
import net.royal.erp.modules.hr.application.tiposeguro.dto.*;
import net.royal.erp.modules.hr.application.tiposeguro.port.MantenimientoTipoSeguroRepository;
import net.royal.erp.modules.hr.domain.tiposeguro.*;

public class MantenimientoTipoSeguroV1UseCase implements MantenimientoTipoSeguroUseCase {
	private static final String MODULE = "HR";
	private static final String ENTITY = "HR_TipoSeguro";

	private final MantenimientoTipoSeguroRepository repository;
	private final UseCaseGuards guards;
	private final AuditPort auditPort;

	public MantenimientoTipoSeguroV1UseCase(MantenimientoTipoSeguroRepository repository, UseCaseGuards guards,
			AuditPort auditPort) {
		this.repository = repository;
		this.guards = guards;
		this.auditPort = auditPort;
	}

	@Override
	public ListarTiposSeguroResult listar(ListarTiposSeguroQuery query, FunctionalContext context) {
		guards.check(context);
		ListarTiposSeguroQuery filters = query == null ? new ListarTiposSeguroQuery(null, null) : query;
		ListarTiposSeguroResult result = new ListarTiposSeguroResult(repository.findAll().stream()
				.filter(t -> filters.tipoSeguro() == null || Objects.equals(t.id().value(), filters.tipoSeguro()))
				.filter(t -> matches(t.estado(), filters.estado()))
				.map(t -> TipoSeguroResultMapper.toResult(t, context.traceId())).toList(), context.traceId());
		registerAudit(context, "LISTAR", "registros=" + result.tiposSeguro().size());
		return result;
	}

	@Override
	public CrearTipoSeguroResult crear(CrearTipoSeguroCommand command, FunctionalContext context) {
		guards.check(context);
		TipoSeguroId id = new TipoSeguroId(command.tipoSeguro());
		if (repository.existsById(id)) {
			throw new BusinessException("HR-TSG-004");
		}
		TipoSeguro tipoSeguro = TipoSeguro.crear(command.tipoSeguro(), command.descripcion(), command.estado(),
				context.userId(), context.executedAt());
		repository.save(tipoSeguro);
		registerAudit(context, id);
		return new CrearTipoSeguroResult(command.tipoSeguro(), "CREADO", context.traceId());
	}

	@Override
	public TipoSeguroResult obtener(ObtenerTipoSeguroQuery query, FunctionalContext context) {
		guards.check(context);
		TipoSeguro tipoSeguro = repository.findById(new TipoSeguroId(query.tipoSeguro()))
				.orElseThrow(() -> new BusinessException("HR-TSG-404"));
		registerAudit(context, tipoSeguro.id());
		return TipoSeguroResultMapper.toResult(tipoSeguro, context.traceId());
	}

	@Override
	public ActualizarTipoSeguroResult actualizar(ActualizarTipoSeguroCommand command, FunctionalContext context) {
		guards.check(context);
		TipoSeguroId id = new TipoSeguroId(command.tipoSeguro());
		TipoSeguro tipoSeguro = repository.findById(id).orElseThrow(() -> new BusinessException("HR-TSG-404"));
		tipoSeguro.actualizar(command.descripcion(), command.estado(), context.userId(), context.executedAt());
		repository.save(tipoSeguro);
		registerAudit(context, id);
		return new ActualizarTipoSeguroResult(command.tipoSeguro(), "ACTUALIZADO", context.traceId());
	}

	@Override
	public TipoSeguroResult cambiarEstado(CambiarEstadoTipoSeguroCommand command, FunctionalContext context) {
		guards.check(context);
		TipoSeguroId id = new TipoSeguroId(command.tipoSeguro());
		TipoSeguro tipoSeguro = repository.findById(id).orElseThrow(() -> new BusinessException("HR-TSG-404"));
		tipoSeguro.cambiarEstado(command.estado(), context.userId(), context.executedAt());
		repository.save(tipoSeguro);
		registerAudit(context, id);
		return TipoSeguroResultMapper.toResult(tipoSeguro, context.traceId());
	}

	@Override
	public EliminarTipoSeguroResult eliminar(EliminarTipoSeguroCommand command, FunctionalContext context) {
		guards.check(context);
		TipoSeguroId id = new TipoSeguroId(command.tipoSeguro());
		repository.deleteById(id);
		registerAudit(context, id);
		return new EliminarTipoSeguroResult(command.tipoSeguro(), true, context.traceId());
	}

	private void registerAudit(FunctionalContext context, TipoSeguroId id) {
		registerAudit(context, String.valueOf(id.value()), null);
	}

	private void registerAudit(FunctionalContext context, String entityId, String message) {
		auditPort.register(new FunctionalAuditRecord(context.tenantId(), context.clientId(), MODULE, context.process(),
				context.useCase(), context.functionality(), "V1", context.userId(), "OK", ENTITY, entityId,
				context.traceId(), context.requestId(), context.executedAt(), context.language(), message));
	}

	private boolean matches(String value, String filter) {
		return filter == null || filter.isBlank() || Objects.equals(normalize(value), normalize(filter));
	}

	private String normalize(String value) {
		return value == null ? "" : value.trim().toUpperCase();
	}
}
