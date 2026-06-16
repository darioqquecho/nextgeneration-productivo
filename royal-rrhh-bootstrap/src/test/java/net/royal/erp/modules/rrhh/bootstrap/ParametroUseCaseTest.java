package net.royal.erp.modules.rrhh.bootstrap;

import net.royal.erp.framework.audit.AuditPort;
import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.licensing.InMemoryLicenseChecker;
import net.royal.erp.framework.security.InMemoryPermissionChecker;
import net.royal.erp.framework.versioning.*;
import net.royal.erp.modules.rrhh.application.common.UseCaseGuards;
import net.royal.erp.modules.rrhh.application.parametro.dto.CrearParametroCommand;
import net.royal.erp.modules.rrhh.application.parametro.usecase.*;
import net.royal.erp.modules.rrhh.infrastructure.audit.ConsoleAuditAdapter;
import net.royal.erp.modules.rrhh.infrastructure.parametro.InMemoryParametroRepositoryAdapter;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Implementa: - MOD-012 CU-001 CRUD HR_Parametros.
 */
class ParametroUseCaseTest {
	@Test
	void crearParametroOk() {
		InMemoryPermissionChecker p = new InMemoryPermissionChecker();
		p.grant("admin", "RRHH_PARAMETRO_CREAR");
		InMemoryLicenseChecker l = new InMemoryLicenseChecker();
		l.enable("demo-client", "RRHH");
		AuditPort audit = new ConsoleAuditAdapter();
		CrearParametroUseCase useCase = new CrearParametroV1UseCase(new InMemoryParametroRepositoryAdapter(),
				new UseCaseGuards(p, l), audit);

		var result = useCase.execute(new CrearParametroCommand("COMP01", "P1", "Parametro 1"),
				new FunctionalContext("default", "demo-client", "admin", "RRHH", "Parametros", "Crear Parametro",
						"CrearParametroUseCase", null, null, null, Instant.now()));

		assertEquals("CREADO", result.estado());
		assertEquals("COMP01", result.compania());
	}

	@Test
	void crearParametroVersionadoOk() {
		InMemoryPermissionChecker p = new InMemoryPermissionChecker();
		p.grant("admin", "RRHH_PARAMETRO_CREAR");
		InMemoryLicenseChecker l = new InMemoryLicenseChecker();
		l.enable("demo-client", "RRHH");
		UseCaseGuards guards = new UseCaseGuards(p, l);
		AuditPort audit = new ConsoleAuditAdapter();
		InMemoryParametroRepositoryAdapter repository = new InMemoryParametroRepositoryAdapter();
		InMemoryFunctionalVersionResolver resolver = new InMemoryFunctionalVersionResolver(FunctionalVersion.V1);
		resolver.register("demo-client", "RRHH", "CrearParametroUseCase", FunctionalVersion.V2);
		CrearParametroUseCase useCase = new CrearParametroVersionedUseCase(resolver,
				new CrearParametroV1UseCase(repository, guards, audit),
				new CrearParametroV2UseCase(repository, guards, audit));

		var result = useCase.execute(new CrearParametroCommand("COMP01", "P2", "Parametro 2"),
				new FunctionalContext("default", "demo-client", "admin", "RRHH", "Parametros", "Crear Parametro",
						"CrearParametroUseCase", null, null, null, Instant.now()));

		assertEquals("CREADO", result.estado());
		assertEquals("COMP01", result.compania());
	}
}
