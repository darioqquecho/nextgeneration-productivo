package net.royal.erp.hr.application.maestros.parametro.mantenimiento.usecase;

import java.util.Objects;

import net.royal.erp.framework.application.RoyalBaseUseCase;
import net.royal.erp.framework.audit.*;
import net.royal.erp.framework.kernel.*;
import net.royal.erp.framework.security.UseCaseGuards;
import net.royal.erp.hr.application.maestros.parametro.mantenimiento.dto.*;
import net.royal.erp.hr.application.common.security.port.ConsultaPermisoPort;
import net.royal.erp.hr.application.common.security.port.ConsultaPermisoQuery;
import net.royal.erp.hr.application.maestros.parametro.mantenimiento.port.MantenimientoTablaParametrosRepository;
import net.royal.erp.hr.domain.parametro.*;

/**
 * Implementa: - MOD-012 CU-001 Mantenimiento de Tabla Parametros V1.
 */
public class MantenimientoTablaParametrosV1UseCase extends RoyalBaseUseCase {
	private static final String MODULE = "HR";
	private static final String ENTITY = "HR_Parametros";

	private final MantenimientoTablaParametrosRepository repository;
	private final ConsultaPermisoPort consultaPermiso;

	public MantenimientoTablaParametrosV1UseCase(MantenimientoTablaParametrosRepository repository,
			UseCaseGuards guards, AuditPort auditPort) {
		this(repository, ConsultaPermisoPort.permitirSiempre(), guards, auditPort);
	}

	public MantenimientoTablaParametrosV1UseCase(MantenimientoTablaParametrosRepository repository,
			ConsultaPermisoPort consultaPermiso, UseCaseGuards guards, AuditPort auditPort) {
		this(repository, consultaPermiso, guards, auditPort, "V1");
	}

	protected MantenimientoTablaParametrosV1UseCase(MantenimientoTablaParametrosRepository repository,
			ConsultaPermisoPort consultaPermiso, UseCaseGuards guards, AuditPort auditPort, String version) {
		super(MODULE, ENTITY, version, guards, auditPort);
		this.repository = repository;
		this.consultaPermiso = consultaPermiso;
	}

	public ListarParametrosResult listar(ListarParametrosQuery query, FunctionalContext context) {
		checkGuards(context);
		ListarParametrosQuery filters = query == null ? new ListarParametrosQuery(null, null, null) : query;
		ListarParametrosResult result = new ListarParametrosResult(
				repository.findAll().stream().filter(p -> matches(p.id().compania(), filters.compania()))
						.filter(p -> matches(p.id().codigo(), filters.codigo()))
						.filter(p -> matches(p.estado() == null ? null : p.estado().name(), filters.estado()))
						.map(p -> ParametroResultMapper.toResult(p, context.traceId())).toList(),
				context.traceId());
		registerAudit(context, "LISTAR", "registros=" + result.parametros().size());
		return result;
	}

	public CrearParametroResult crear(CrearParametroCommand command, FunctionalContext context) {
		checkGuards(context);
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

	public ParametroResult obtener(ObtenerParametroQuery query, FunctionalContext context) {
		checkGuards(context);
		Parametro parametro = repository.findById(new ParametroId(query.compania(), query.codigo()))
				.orElseThrow(() -> new BusinessException("HR-PAR-404"));
		registerAudit(context, parametro.id());
		return ParametroResultMapper.toResult(parametro, context.traceId());
	}

	public ActualizarParametroResult actualizar(ActualizarParametroCommand command, FunctionalContext context) {
		checkGuards(context);
		ParametroId id = new ParametroId(command.compania(), command.codigo());
		Parametro parametro = repository.findById(id).orElseThrow(() -> new BusinessException("HR-PAR-404"));
		parametro.actualizarDatos(command.nombre(), command.precio(), command.cantidad(), command.fechaProceso(),
				context.userId(), context.executedAt());
		repository.save(parametro);
		registerAudit(context, id);
		return new ActualizarParametroResult(command.compania(), command.codigo(), "ACTUALIZADO", context.traceId());
	}

	public ParametroResult cambiarEstado(CambiarEstadoParametroCommand command, FunctionalContext context) {
		checkGuards(context);
		ParametroId id = new ParametroId(command.compania(), command.codigo());
		Parametro parametro = repository.findById(id).orElseThrow(() -> new BusinessException("HR-PAR-404"));
		parametro.cambiarEstado(ParametroEstado.valueOf(command.estado().toUpperCase()), context.userId(),
				context.executedAt());
		repository.save(parametro);
		registerAudit(context, id);
		return ParametroResultMapper.toResult(parametro, context.traceId());
	}

	public EliminarParametroResult eliminar(EliminarParametroCommand command, FunctionalContext context) {
		checkGuards(context);
		validarPermisoEliminar(context);
		ParametroId id = new ParametroId(command.compania(), command.codigo());
		repository.deleteById(id);
		registerAudit(context, id);
		return new EliminarParametroResult(command.compania(), command.codigo(), true, context.traceId());
	}

	private void validarPermisoEliminar(FunctionalContext context) {
		ConsultaPermisoQuery query = new ConsultaPermisoQuery(context.userId(), MODULE, "Mantenimiento de Parametro",
				"HR_MANTENIMIENTO_DE_PARAMETRO");
		if (!consultaPermiso.autorizado(query, context)) {
			throw new BusinessException("SECURITY-DENIED", query.permiso());
		}
	}

	private void registerAudit(FunctionalContext context, ParametroId id) {
		registerAudit(context, id.value(), null);
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
