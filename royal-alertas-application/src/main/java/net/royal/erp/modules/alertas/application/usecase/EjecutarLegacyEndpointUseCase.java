package net.royal.erp.modules.alertas.application.usecase;

import net.royal.erp.framework.kernel.BusinessException;
import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.kernel.UseCase;
import net.royal.erp.modules.alertas.application.dto.*;
import net.royal.erp.modules.alertas.domain.metadata.AlertasMetadataCatalog;
import java.util.Map;

/**
 * Implementa: - MOD-012 Migración funcional de endpoints legacy. - ASIS-020
 * Frontend actual por módulo.
 *
 * Propósito: Mantiene compatibilidad REST legacy /spring/alertas/** y enruta
 * hacia casos de uso nuevos.
 */
public class EjecutarLegacyEndpointUseCase implements UseCase<LegacyEndpointCommand, Object> {
	private final CrearRegistroUseCase crear;
	private final ActualizarRegistroUseCase actualizar;
	private final ObtenerRegistroUseCase obtener;
	private final ListarRegistrosUseCase listar;
	private final AnularRegistroUseCase anular;
	private final EliminarRegistroUseCase eliminar;
	private final EjecutarNamedQueryUseCase namedQuery;

	public EjecutarLegacyEndpointUseCase(CrearRegistroUseCase crear, ActualizarRegistroUseCase actualizar,
			ObtenerRegistroUseCase obtener, ListarRegistrosUseCase listar, AnularRegistroUseCase anular,
			EliminarRegistroUseCase eliminar, EjecutarNamedQueryUseCase namedQuery) {
		this.crear = crear;
		this.actualizar = actualizar;
		this.obtener = obtener;
		this.listar = listar;
		this.anular = anular;
		this.eliminar = eliminar;
		this.namedQuery = namedQuery;
	}

	/**
	 * Ejecuta endpoint legacy y lo traduce a caso de uso nuevo.
	 *
	 * Implementa: - MOD-012 Migración funcional.
	 */
	@Override
	public Object execute(LegacyEndpointCommand command, FunctionalContext context) {
		String action = command.action() == null ? "" : command.action().toLowerCase();
		String entity = AlertasMetadataCatalog.byResource(command.resource()).map(e -> e.entityName()).orElse(null);
		Map<String, Object> payload = command.payload() == null ? Map.of() : command.payload();
		if (action.startsWith("validar")) {
			return java.util.List.of();
		}
		if (entity == null) {
			String queryName = command.resource() + "." + action;
			return namedQuery.execute(new NamedQueryCommand(queryName, payload), context);
		}
		if (action.startsWith("registrar"))
			return crear.execute(new AlertasCrudCommand(entity, payload), context);
		if (action.startsWith("actualizar"))
			return actualizar.execute(new AlertasCrudCommand(entity, payload), context);
		if (action.startsWith("obtenerporid"))
			return obtener.execute(new AlertasIdCommand(entity, payload), context);
		if (action.startsWith("anular"))
			return anular.execute(new AlertasIdCommand(entity, payload), context);
		if (action.startsWith("eliminar"))
			return eliminar.execute(new AlertasIdCommand(entity, payload), context);
		if (action.startsWith("listar") || action.contains("paginacion") || action.contains("filtro")) {
			String queryName = command.resource() + "." + command.methodName();
			if (command.methodName() != null && !command.methodName().isBlank()) {
				try {
					return namedQuery.execute(new NamedQueryCommand(queryName, payload), context);
				} catch (Exception ignored) {
				}
			}
			return listar.execute(new AlertasIdCommand(entity, payload), context);
		}
		throw new BusinessException("ALT-LEGACY-001", command.resource(), command.action());
	}
}
