package net.royal.erp.hr.application.maestros.parametro.aprobacionmasiva.port;

import java.time.Instant;
import java.util.Optional;

import net.royal.erp.hr.domain.parametro.*;

/**
 * Puerto de persistencia del caso de uso Aprobacion masiva de Parametros.
 */
public interface AprobacionMasivaParametrosRepository {
	Optional<Parametro> findById(ParametroId id);

	boolean approveIfPending(ParametroId id, String usuario, Instant fechaModif);
}
