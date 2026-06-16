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

import net.royal.spring.alertas.dao.impl.AlertaAdicionalDaoImpl;
import net.royal.spring.alertas.dominio.AlertaAdicional;
import net.royal.spring.alertas.dominio.AlertaAdicionalPk;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;

@Service (value = "BeanValidarAlertaAdicional")
public class AlertaAdicionalServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarAlertaAdicional";

	@Autowired
	private AlertaAdicionalDaoImpl alertaAdicionalDao;

	private AlertaAdicional prepararBasico(SeguridadUsuarioActual usuarioActual,AlertaAdicional alertaAdicional, Boolean flgInsertar) {
		if (flgInsertar) {
			alertaAdicional.setCreacionTerminal(usuarioActual.getDireccionIp());
			alertaAdicional.setCreacionFecha(new Date());
			alertaAdicional.setCreacionUsuario(usuarioActual.getUsuario());
		} else {
			alertaAdicional.setModificacionTerminal(usuarioActual.getDireccionIp());
			alertaAdicional.setModificacionFecha(new Date());
			alertaAdicional.setModificacionUsuario(usuarioActual.getUsuario());
		}
		
		// TODO AlertaAdicional : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return alertaAdicional;
	}

	public AlertaAdicional prepararInsertar(SeguridadUsuarioActual usuarioActual,AlertaAdicional alertaAdicional) {
		if (alertaAdicional == null)
			return alertaAdicional;
		if (alertaAdicional.getAuxFlgPreparado())
			return alertaAdicional;
		alertaAdicional = prepararBasico(usuarioActual,alertaAdicional, true);
		alertaAdicional.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO AlertaAdicional.Insertar : valores por defecto
		
		return alertaAdicional;
	}

	public AlertaAdicional prepararActualizar(SeguridadUsuarioActual usuarioActual,AlertaAdicional alertaAdicional) {
		if (alertaAdicional == null)
			return alertaAdicional;
		if (alertaAdicional.getAuxFlgPreparado())
			return alertaAdicional;
		alertaAdicional = prepararBasico(usuarioActual,alertaAdicional, false);
		alertaAdicional.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO AlertaAdicional.Actualizar : valores por defecto
		
		return alertaAdicional;
	}

	public AlertaAdicional prepararAnular(SeguridadUsuarioActual usuarioActual,AlertaAdicional alertaAdicional) {
		if (alertaAdicional == null)
			return alertaAdicional;
		if (alertaAdicional.getAuxFlgPreparado())
			return alertaAdicional;
		alertaAdicional = prepararBasico(usuarioActual, alertaAdicional, false);
		alertaAdicional.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO AlertaAdicional.Anular : valores por defecto
		
		return alertaAdicional;
	}

	public AlertaAdicional prepararEliminar(SeguridadUsuarioActual usuarioActual,AlertaAdicional alertaAdicional) {
		if (alertaAdicional == null)
			return alertaAdicional;
		if (alertaAdicional.getAuxFlgPreparado())
			return alertaAdicional;
		alertaAdicional.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO AlertaAdicional.Eliminar : valores por defecto
		
		return alertaAdicional;
	}

	@SuppressWarnings("rawtypes")
	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, AlertaAdicional alertaAdicional) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (alertaAdicional == null)
			lst.add(this.getMsjUsuarioError("alertaadicional.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (alertaAdicional.getPk() != null) {
			Set<ConstraintViolation<AlertaAdicionalPk>> reglasPk = validator.validate(alertaAdicional.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<AlertaAdicional>> reglas = validator.validate(alertaAdicional);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO AlertaAdicional : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,AlertaAdicional alertaAdicional) {
		alertaAdicional = prepararInsertar(usuarioActual, alertaAdicional);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, alertaAdicional);
		if (!lst.isEmpty())
			return lst;
		
		// TODO AlertaAdicional.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, AlertaAdicional alertaAdicional) {
		alertaAdicional = prepararActualizar(usuarioActual, alertaAdicional);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, alertaAdicional);
		if (!lst.isEmpty())
			return lst;
		
		// TODO AlertaAdicional.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,AlertaAdicional alertaAdicional) {
		alertaAdicional = prepararEliminar(usuarioActual, alertaAdicional);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO AlertaAdicional.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,AlertaAdicionalPk pk) {
		AlertaAdicional bean = alertaAdicionalDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,Integer pidAlerta,Integer pidAdicional) {
		return coreEliminar(usuarioActual,new AlertaAdicionalPk( pidAlerta, pidAdicional));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, AlertaAdicional alertaAdicional) {
		alertaAdicional = prepararAnular(usuarioActual, alertaAdicional);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO AlertaAdicional.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, AlertaAdicionalPk pk) {
		AlertaAdicional bean = alertaAdicionalDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, Integer pidAlerta,Integer pidAdicional) {
		return coreAnular(usuarioActual,new AlertaAdicionalPk( pidAlerta, pidAdicional));
	}

	public List<DominioMensajeUsuario> core(SeguridadUsuarioActual usuarioActual,String accion,AlertaAdicional alertaAdicional) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			return coreInsertar(usuarioActual, alertaAdicional);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			return coreActualizar(usuarioActual, alertaAdicional);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			return coreAnular(usuarioActual, alertaAdicional);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			return coreEliminar(usuarioActual, alertaAdicional);
		return lst;
	}

}
