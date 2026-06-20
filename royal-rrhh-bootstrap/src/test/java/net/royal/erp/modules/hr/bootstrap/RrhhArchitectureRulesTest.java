package net.royal.erp.modules.hr.bootstrap;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

/**
 * Reglas minimas para mantener los casos de uso legibles y basados en clases
 * maestras.
 */
class RrhhArchitectureRulesTest {
	private static final Path APPLICATION = repositoryRoot().resolve(Paths.get("royal-rrhh-application", "src", "main",
			"java"));
	private static final Path API = repositoryRoot().resolve(Paths.get("royal-rrhh-api", "src", "main", "java"));

	@Test
	void casosDeUsoNoRepitenInfraestructuraTransversal() throws IOException {
		List<String> forbidden = List.of("guards.check(", "new FunctionalAuditRecord", "ExecutionTimer.start()",
				"private final UseCaseGuards", "private final AuditPort", "private final ObservabilityPort");
		try (Stream<Path> files = Files.walk(APPLICATION)) {
			List<String> violations = files.filter(Files::isRegularFile).filter(path -> path.toString().endsWith(".java"))
					.filter(path -> path.getFileName().toString().contains("UseCase")).flatMap(path -> violations(path, forbidden))
					.toList();
			assertTrue(violations.isEmpty(), () -> "Infraestructura repetida en casos de uso:\n" + String.join("\n", violations));
		}
	}

	@Test
	void casosDeUsoConcretosUsanClaseMaestra() throws IOException {
		try (Stream<Path> files = Files.walk(APPLICATION)) {
			List<String> violations = files.filter(Files::isRegularFile).filter(path -> path.toString().endsWith("UseCase.java"))
					.filter(path -> !path.getFileName().toString().equals("MantenimientoTablaParametrosUseCase.java"))
					.filter(path -> !path.getFileName().toString().equals("MantenimientoTipoSeguroUseCase.java"))
					.filter(path -> !path.getFileName().toString().equals("AprobacionMasivaParametrosUseCase.java"))
					.filter(path -> !path.getFileName().toString().equals("ReporteParametrosUseCase.java"))
					.filter(path -> !path.getFileName().toString().equals("RegistrarCapacitacionUseCase.java"))
					.filter(path -> !path.getFileName().toString().equals("AprobarRequerimientoPersonalUseCase.java"))
					.filter(path -> !usesBaseClass(path)).map(Path::toString).toList();
			assertTrue(violations.isEmpty(), () -> "Casos de uso sin clase maestra:\n" + String.join("\n", violations));
		}
	}

	@Test
	void apiNoDebeTenerControllersEnRaizDelModulo() throws IOException {
		Path apiRoot = API.resolve(Paths.get("net", "royal", "erp", "modules", "hr", "api"));
		try (Stream<Path> files = Files.list(apiRoot)) {
			List<String> violations = files.filter(Files::isRegularFile)
					.filter(path -> path.getFileName().toString().endsWith("Controller.java")).map(Path::toString).toList();
			assertTrue(violations.isEmpty(), () -> "Controllers fuera de proceso funcional:\n" + String.join("\n", violations));
		}
	}

	@Test
	void controllersDeRrhhUsanCatalogoFuncional() throws IOException {
		try (Stream<Path> files = Files.walk(API)) {
			List<String> violations = files.filter(Files::isRegularFile).filter(path -> path.toString().endsWith("ControllerV1.java"))
					.filter(path -> !usesProcessCatalog(path)).map(Path::toString).toList();
			assertTrue(violations.isEmpty(), () -> "Controllers sin RrhhProcessCatalog:\n" + String.join("\n", violations));
		}
	}

	private Stream<String> violations(Path path, List<String> forbidden) {
		try {
			String source = Files.readString(path);
			return forbidden.stream().filter(source::contains).map(pattern -> path + " contiene " + pattern);
		} catch (IOException ex) {
			throw new IllegalStateException(ex);
		}
	}

	private boolean usesBaseClass(Path path) {
		try {
			String source = Files.readString(path);
			return source.contains("extends RoyalBaseUseCase") || source.contains("extends RoyalObservedUseCase")
					|| source.contains("extends RoyalBaseVersionedUseCase")
					|| source.contains("extends RoyalDelegatingUseCase")
					|| source.contains("extends MantenimientoTablaParametrosV1UseCase");
		} catch (IOException ex) {
			throw new IllegalStateException(ex);
		}
	}

	private boolean usesProcessCatalog(Path path) {
		try {
			String source = Files.readString(path);
			return source.contains("RrhhProcessCatalog.");
		} catch (IOException ex) {
			throw new IllegalStateException(ex);
		}
	}

	private static Path repositoryRoot() {
		Path current = Paths.get("").toAbsolutePath();
		while (current != null) {
			if (Files.exists(current.resolve("pom.xml"))
					&& Files.exists(current.resolve("royal-rrhh-application"))) {
				return current;
			}
			current = current.getParent();
		}
		throw new IllegalStateException("No se encontro la raiz del repositorio");
	}
}
