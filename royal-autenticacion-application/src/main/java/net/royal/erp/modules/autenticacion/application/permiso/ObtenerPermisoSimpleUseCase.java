package net.royal.erp.modules.autenticacion.application.permiso;

import java.time.Instant;
import java.text.Normalizer;
import java.util.Locale;

import net.royal.erp.framework.kernel.BusinessException;
import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.security.PermissionChecker;

/**
 * Implementacion inicial de consulta de permisos.
 */
public class ObtenerPermisoSimpleUseCase implements ObtenerPermisoUseCase {
	private final PermissionChecker permissionChecker;

	public ObtenerPermisoSimpleUseCase(PermissionChecker permissionChecker) {
		this.permissionChecker = permissionChecker;
	}

	public ObtenerPermisoResult execute(ObtenerPermisoCommand command, FunctionalContext context) {
		Instant fechaConsulta = Instant.now();
		if (command == null) {
			return denied(context.userId(), null, null, null, "Solicitud requerida", fechaConsulta);
		}
		String usuario = isBlank(command.usuario()) ? context.userId() : command.usuario().trim();
		String concepto = trimToNull(command.concepto());
		String funcionalidad = trimToNull(command.funcionalidad());
		String permiso = resolvePermiso(command);
		if (isBlank(permiso)) {
			return denied(usuario, concepto, funcionalidad, null, "Permiso o concepto/funcionalidad requerido",
					fechaConsulta);
		}
		FunctionalContext permissionContext = new FunctionalContext(context.tenantId(), context.clientId(), usuario,
				context.module(), context.process(), context.functionality(), context.useCase(),
				context.requestedVersion(), context.traceId(), context.requestId(), fechaConsulta, context.language());
		try {
			permissionChecker.check(permissionContext, permiso);
			return new ObtenerPermisoResult(usuario, concepto, funcionalidad, permiso, "S", "Permiso autorizado",
					fechaConsulta);
		} catch (BusinessException ex) {
			return denied(usuario, concepto, funcionalidad, permiso, "Permiso denegado", fechaConsulta);
		}
	}

	private ObtenerPermisoResult denied(String usuario, String concepto, String funcionalidad, String permiso,
			String mensaje, Instant fechaConsulta) {
		return new ObtenerPermisoResult(usuario, concepto, funcionalidad, permiso, "N", mensaje, fechaConsulta);
	}

	private String resolvePermiso(ObtenerPermisoCommand command) {
		if (!isBlank(command.permiso())) {
			return command.permiso().trim();
		}
		if (isBlank(command.concepto()) || isBlank(command.funcionalidad())) {
			return null;
		}
		return normalize(command.concepto()) + "_" + normalize(command.funcionalidad());
	}

	private String normalize(String value) {
		String normalized = Normalizer.normalize(value == null ? "" : value, Normalizer.Form.NFD)
				.replaceAll("\\p{M}", "");
		return normalized.trim().replaceAll("[^A-Za-z0-9]+", "_").replaceAll("^_|_$", "")
				.toUpperCase(Locale.ROOT);
	}

	private String trimToNull(String value) {
		return isBlank(value) ? null : value.trim();
	}

	private boolean isBlank(String value) {
		return value == null || value.isBlank();
	}
}
