package net.royal.spring.alertas.servicio.validar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.royal.spring.alertas.dao.impl.AlertaDestinoDaoImpl;
import net.royal.spring.alertas.dominio.AlertaDestino;
import net.royal.spring.alertas.dominio.AlertaDestinoPk;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;

@Service (value = "BeanValidarAlertaDestino")
public class AlertaDestinoServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarAlertaDestino";

	@Autowired
	private AlertaDestinoDaoImpl alertaDestinoDao;

	private AlertaDestino prepararBasico(SeguridadUsuarioActual usuarioActual,AlertaDestino alertaDestino, Boolean flgInsertar) {
		if (flgInsertar) {
			alertaDestino.setCreacionTerminal(usuarioActual.getDireccionIp());
			alertaDestino.setCreacionFecha(new Date());
			alertaDestino.setCreacionUsuario(usuarioActual.getUsuario());
		} else {
			alertaDestino.setModificacionTerminal(usuarioActual.getDireccionIp());
			alertaDestino.setModificacionFecha(new Date());
			alertaDestino.setModificacionUsuario(usuarioActual.getUsuario());
		}
		
		// TODO AlertaDestino : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return alertaDestino;
	}

	public AlertaDestino prepararInsertar(SeguridadUsuarioActual usuarioActual,AlertaDestino alertaDestino) {
		if (alertaDestino == null)
			return alertaDestino;
		if (alertaDestino.getAuxFlgPreparado())
			return alertaDestino;
		alertaDestino = prepararBasico(usuarioActual,alertaDestino, true);
		alertaDestino.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO AlertaDestino.Insertar : valores por defecto
		
		return alertaDestino;
	}

	public AlertaDestino prepararActualizar(SeguridadUsuarioActual usuarioActual,AlertaDestino alertaDestino) {
		if (alertaDestino == null)
			return alertaDestino;
		if (alertaDestino.getAuxFlgPreparado())
			return alertaDestino;
		alertaDestino = prepararBasico(usuarioActual,alertaDestino, false);
		alertaDestino.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO AlertaDestino.Actualizar : valores por defecto
		
		return alertaDestino;
	}

	public AlertaDestino prepararAnular(SeguridadUsuarioActual usuarioActual,AlertaDestino alertaDestino) {
		if (alertaDestino == null)
			return alertaDestino;
		if (alertaDestino.getAuxFlgPreparado())
			return alertaDestino;
		alertaDestino = prepararBasico(usuarioActual, alertaDestino, false);
		alertaDestino.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO AlertaDestino.Anular : valores por defecto
		
		return alertaDestino;
	}

	public AlertaDestino prepararEliminar(SeguridadUsuarioActual usuarioActual,AlertaDestino alertaDestino) {
		if (alertaDestino == null)
			return alertaDestino;
		if (alertaDestino.getAuxFlgPreparado())
			return alertaDestino;
		alertaDestino.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO AlertaDestino.Eliminar : valores por defecto
		
		return alertaDestino;
	}

	@SuppressWarnings("rawtypes")
	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, AlertaDestino alertaDestino) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (alertaDestino == null)
			lst.add(this.getMsjUsuarioError("alertadestino.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (alertaDestino.getPk() != null) {
			Set<ConstraintViolation<AlertaDestinoPk>> reglasPk = validator.validate(alertaDestino.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<AlertaDestino>> reglas = validator.validate(alertaDestino);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO AlertaDestino : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,AlertaDestino alertaDestino) {
		alertaDestino = prepararInsertar(usuarioActual, alertaDestino);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, alertaDestino);
		if (!lst.isEmpty())
			return lst;
		
		// TODO AlertaDestino.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, AlertaDestino alertaDestino) {
		alertaDestino = prepararActualizar(usuarioActual, alertaDestino);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, alertaDestino);
		if (!lst.isEmpty())
			return lst;
		
		// TODO AlertaDestino.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,AlertaDestino alertaDestino) {
		alertaDestino = prepararEliminar(usuarioActual, alertaDestino);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO AlertaDestino.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,AlertaDestinoPk pk) {
		AlertaDestino bean = alertaDestinoDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,Integer pidAlerta,Integer pidDestino) {
		return coreEliminar(usuarioActual,new AlertaDestinoPk( pidAlerta, pidDestino));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, AlertaDestino alertaDestino) {
		alertaDestino = prepararAnular(usuarioActual, alertaDestino);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO AlertaDestino.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, AlertaDestinoPk pk) {
		AlertaDestino bean = alertaDestinoDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, Integer pidAlerta,Integer pidDestino) {
		return coreAnular(usuarioActual,new AlertaDestinoPk( pidAlerta, pidDestino));
	}

	public List<DominioMensajeUsuario> core(SeguridadUsuarioActual usuarioActual,String accion,AlertaDestino alertaDestino) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			return coreInsertar(usuarioActual, alertaDestino);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			return coreActualizar(usuarioActual, alertaDestino);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			return coreAnular(usuarioActual, alertaDestino);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			return coreEliminar(usuarioActual, alertaDestino);
		return lst;
	}

}
