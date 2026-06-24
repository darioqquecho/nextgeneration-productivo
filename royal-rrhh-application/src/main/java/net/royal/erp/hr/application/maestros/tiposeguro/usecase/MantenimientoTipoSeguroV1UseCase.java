package net.royal.erp.hr.application.maestros.tiposeguro.usecase;

import java.util.Objects;

import net.royal.erp.framework.application.RoyalBaseUseCase;
import net.royal.erp.framework.audit.*;
import net.royal.erp.framework.kernel.*;
import net.royal.erp.framework.security.UseCaseGuards;
import net.royal.erp.hr.application.maestros.tiposeguro.dto.*;
import net.royal.erp.hr.application.maestros.tiposeguro.port.MantenimientoTipoSeguroRepository;
import net.royal.erp.hr.application.process.RrhhProcessCatalog;
import net.royal.erp.hr.domain.RrhhBusinessErrorCodes;
import net.royal.erp.hr.domain.tiposeguro.*;

public class MantenimientoTipoSeguroV1UseCase extends RoyalBaseUseCase {
	private static final String ENTITY = "HR_TipoSeguro";

	private final MantenimientoTipoSeguroRepository repository;

	public MantenimientoTipoSeguroV1UseCase(MantenimientoTipoSeguroRepository repository, UseCaseGuards guards,
			AuditPort auditPort) {
		super(RrhhProcessCatalog.MODULE, ENTITY, "V1", guards, auditPort);
		this.repository = repository;
	}

	public ListarTiposSeguroResult listar(ListarTiposSeguroQuery query, FunctionalContext context) {
		checkGuards(context);
		ListarTiposSeguroQuery filters = query == null ? new ListarTiposSeguroQuery(null, null) : query;
		ListarTiposSeguroResult result = new ListarTiposSeguroResult(repository.findAll().stream()
				.filter(t -> filters.tipoSeguro() == null || Objects.equals(t.id().value(), filters.tipoSeguro()))
				.filter(t -> matches(t.estado(), filters.estado()))
				.map(t -> TipoSeguroResultMapper.toResult(t, context.traceId())).toList(), context.traceId());
		registerAudit(context, "LISTAR", "registros=" + result.tiposSeguro().size());
		return result;
	}

	public CrearTipoSeguroResult crear(CrearTipoSeguroCommand command, FunctionalContext context) {
		checkGuards(context);
		TipoSeguroId id = new TipoSeguroId(command.tipoSeguro());
		if (repository.existsById(id)) {
			throw new BusinessException(RrhhBusinessErrorCodes.TIPO_SEGURO_DUPLICADO);
		}
		TipoSeguro tipoSeguro = TipoSeguro.crear(command.tipoSeguro(), command.descripcion(), command.estado(),
				context.userId(), context.executedAt());
		repository.save(tipoSeguro);
		registerAudit(context, id);
		return new CrearTipoSeguroResult(command.tipoSeguro(), "CREADO", context.traceId());
	}

	public TipoSeguroResult obtener(ObtenerTipoSeguroQuery query, FunctionalContext context) {
		checkGuards(context);
		TipoSeguro tipoSeguro = repository.findById(new TipoSeguroId(query.tipoSeguro()))
				.orElseThrow(() -> new BusinessException(RrhhBusinessErrorCodes.TIPO_SEGURO_NO_ENCONTRADO));
		registerAudit(context, tipoSeguro.id());
		return TipoSeguroResultMapper.toResult(tipoSeguro, context.traceId());
	}

	public ActualizarTipoSeguroResult actualizar(ActualizarTipoSeguroCommand command, FunctionalContext context) {
		checkGuards(context);
		TipoSeguroId id = new TipoSeguroId(command.tipoSeguro());
		TipoSeguro tipoSeguro = repository.findById(id)
				.orElseThrow(() -> new BusinessException(RrhhBusinessErrorCodes.TIPO_SEGURO_NO_ENCONTRADO));
		tipoSeguro.actualizar(command.descripcion(), command.estado(), context.userId(), context.executedAt());
		repository.save(tipoSeguro);
		registerAudit(context, id);
		return new ActualizarTipoSeguroResult(command.tipoSeguro(), "ACTUALIZADO", context.traceId());
	}

	public TipoSeguroResult cambiarEstado(CambiarEstadoTipoSeguroCommand command, FunctionalContext context) {
		checkGuards(context);
		TipoSeguroId id = new TipoSeguroId(command.tipoSeguro());
		TipoSeguro tipoSeguro = repository.findById(id)
				.orElseThrow(() -> new BusinessException(RrhhBusinessErrorCodes.TIPO_SEGURO_NO_ENCONTRADO));
		tipoSeguro.cambiarEstado(command.estado(), context.userId(), context.executedAt());
		repository.save(tipoSeguro);
		registerAudit(context, id);
		return TipoSeguroResultMapper.toResult(tipoSeguro, context.traceId());
	}

	public EliminarTipoSeguroResult eliminar(EliminarTipoSeguroCommand command, FunctionalContext context) {
		checkGuards(context);
		TipoSeguroId id = new TipoSeguroId(command.tipoSeguro());
		repository.deleteById(id);
		registerAudit(context, id);
		return new EliminarTipoSeguroResult(command.tipoSeguro(), true, context.traceId());
	}

	private void registerAudit(FunctionalContext context, TipoSeguroId id) {
		registerAudit(context, String.valueOf(id.value()), null);
	}

	private void registerAudit(FunctionalContext context, String entityId, String message) {
		auditOk(context, entityId, message);
	}

	private boolean matches(String value, String filter) {
		return filter == null || filter.isBlank() || Objects.equals(normalize(value), normalize(filter));
	}

	private String normalize(String value) {
		return value == null ? "" : value.trim().toUpperCase();
	}
}
