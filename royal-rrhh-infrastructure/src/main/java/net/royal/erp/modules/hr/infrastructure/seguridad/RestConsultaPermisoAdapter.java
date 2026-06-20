package net.royal.erp.modules.hr.infrastructure.seguridad;

import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.web.client.RestApiClient;
import net.royal.erp.modules.hr.application.maestros.parametro.port.ConsultaPermisoPort;
import net.royal.erp.modules.hr.application.maestros.parametro.port.ConsultaPermisoQuery;

/**
 * Adapter REST para consultar permisos en el modulo de autenticacion.
 */
public class RestConsultaPermisoAdapter implements ConsultaPermisoPort {
	private static final String PATH = "/api/autenticacion/obtenerpermiso";

	private final RestApiClient client;

	public RestConsultaPermisoAdapter(RestApiClient client) {
		this.client = client;
	}

	public boolean autorizado(ConsultaPermisoQuery query, FunctionalContext context) {
		ObtenerPermisoRequest request = new ObtenerPermisoRequest(query.usuario(), query.concepto(),
				query.funcionalidad(), query.permiso());
		ObtenerPermisoResponse result = client.post(PATH, request, ObtenerPermisoResponse.class, context);
		return "S".equalsIgnoreCase(result.autorizado());
	}

	private record ObtenerPermisoRequest(String usuario, String concepto, String funcionalidad, String permiso) {
	}

	private record ObtenerPermisoResponse(String usuario, String concepto, String funcionalidad, String permiso,
			String autorizado, String mensaje, String fechaConsulta) {
	}
}
