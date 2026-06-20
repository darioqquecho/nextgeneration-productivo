package net.royal.erp.modules.hr.bootstrap;

import java.time.Instant;

import net.royal.erp.framework.audit.*;
import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.licensing.InMemoryLicenseChecker;
import net.royal.erp.framework.security.*;
import net.royal.erp.modules.hr.application.maestros.tiposeguro.dto.*;
import net.royal.erp.modules.hr.application.maestros.tiposeguro.usecase.*;
import net.royal.erp.modules.hr.infrastructure.maestros.tiposeguro.inmemory.InMemoryTipoSeguroRepositoryAdapter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TipoSeguroUseCaseTest {
	@Test
	void crudTipoSeguroOk() {
		InMemoryPermissionChecker permissions = new InMemoryPermissionChecker();
		permissions.grant("admin", "HR_MANTENIMIENTO_DE_TIPO_SEGURO");
		InMemoryLicenseChecker licenses = new InMemoryLicenseChecker();
		licenses.enable("demo-client", "HR");
		InMemoryTipoSeguroRepositoryAdapter repository = new InMemoryTipoSeguroRepositoryAdapter();
		MantenimientoTipoSeguroUseCase useCase = new MantenimientoTipoSeguroV1UseCase(repository,
				new UseCaseGuards(permissions, licenses), new ConsoleAuditAdapter());
		FunctionalContext context = context("Registrar");

		var creado = useCase.crear(new CrearTipoSeguroCommand(1, "Seguro Regular", "A"), context);
		assertEquals("CREADO", creado.estado());

		var obtenido = useCase.obtener(new ObtenerTipoSeguroQuery(1), context("Obtener"));
		assertEquals("Seguro Regular", obtenido.descripcion());
		assertEquals("A", obtenido.estado());

		var actualizado = useCase.actualizar(new ActualizarTipoSeguroCommand(1, "Seguro Especial", "I"),
				context("Actualizar"));
		assertEquals("ACTUALIZADO", actualizado.estado());

		var listado = useCase.listar(new ListarTiposSeguroQuery(null, "I"), context("Listar"));
		assertEquals(1, listado.tiposSeguro().size());
		assertEquals("Seguro Especial", listado.tiposSeguro().get(0).descripcion());

		var activo = useCase.cambiarEstado(new CambiarEstadoTipoSeguroCommand(1, "A"), context("Cambiar de Estado"));
		assertEquals("A", activo.estado());

		var eliminado = useCase.eliminar(new EliminarTipoSeguroCommand(1), context("Eliminar"));
		assertTrue(eliminado.eliminado());
		assertTrue(repository.findAll().isEmpty());
	}

	private FunctionalContext context(String functionality) {
		return new FunctionalContext("default", "demo-client", "admin", "HR", "Maestros", functionality,
				"Mantenimiento de Tipo Seguro", null, null, null, Instant.now());
	}
}
