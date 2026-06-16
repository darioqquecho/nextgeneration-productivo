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

import net.royal.spring.alertas.dao.impl.AlertaDetalleDaoImpl;
import net.royal.spring.alertas.dominio.AlertaDetalle;
import net.royal.spring.alertas.dominio.AlertaDetallePk;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;

@Service (value = "BeanValidarAlertaDetalle")
public class AlertaDetalleServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarAlertaDetalle";

	@Autowired
	private AlertaDetalleDaoImpl alertaDetalleDao;

	private AlertaDetalle prepararBasico(SeguridadUsuarioActual usuarioActual,AlertaDetalle alertaDetalle, Boolean flgInsertar) {
		if (flgInsertar) {
			alertaDetalle.setCreacionTerminal(usuarioActual.getDireccionIp());
			alertaDetalle.setCreacionFecha(new Date());
			alertaDetalle.setCreacionUsuario(usuarioActual.getUsuario());
		} else {
			alertaDetalle.setModificacionTerminal(usuarioActual.getDireccionIp());
			alertaDetalle.setModificacionFecha(new Date());
			alertaDetalle.setModificacionUsuario(usuarioActual.getUsuario());
		}
		
		// TODO AlertaDetalle : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return alertaDetalle;
	}

	public AlertaDetalle prepararInsertar(SeguridadUsuarioActual usuarioActual,AlertaDetalle alertaDetalle) {
		if (alertaDetalle == null)
			return alertaDetalle;
		if (alertaDetalle.getAuxFlgPreparado())
			return alertaDetalle;
		alertaDetalle = prepararBasico(usuarioActual,alertaDetalle, true);
		alertaDetalle.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO AlertaDetalle.Insertar : valores por defecto
		
		return alertaDetalle;
	}

	public AlertaDetalle prepararActualizar(SeguridadUsuarioActual usuarioActual,AlertaDetalle alertaDetalle) {
		if (alertaDetalle == null)
			return alertaDetalle;
		if (alertaDetalle.getAuxFlgPreparado())
			return alertaDetalle;
		alertaDetalle = prepararBasico(usuarioActual,alertaDetalle, false);
		alertaDetalle.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO AlertaDetalle.Actualizar : valores por defecto
		
		return alertaDetalle;
	}

	public AlertaDetalle prepararAnular(SeguridadUsuarioActual usuarioActual,AlertaDetalle alertaDetalle) {
		if (alertaDetalle == null)
			return alertaDetalle;
		if (alertaDetalle.getAuxFlgPreparado())
			return alertaDetalle;
		alertaDetalle = prepararBasico(usuarioActual, alertaDetalle, false);
		alertaDetalle.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO AlertaDetalle.Anular : valores por defecto
		
		return alertaDetalle;
	}

	public AlertaDetalle prepararEliminar(SeguridadUsuarioActual usuarioActual,AlertaDetalle alertaDetalle) {
		if (alertaDetalle == null)
			return alertaDetalle;
		if (alertaDetalle.getAuxFlgPreparado())
			return alertaDetalle;
		alertaDetalle.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO AlertaDetalle.Eliminar : valores por defecto
		
		return alertaDetalle;
	}

	@SuppressWarnings("rawtypes")
	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, AlertaDetalle alertaDetalle) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (alertaDetalle == null)
			lst.add(this.getMsjUsuarioError("alertadetalle.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (alertaDetalle.getPk() != null) {
			Set<ConstraintViolation<AlertaDetallePk>> reglasPk = validator.validate(alertaDetalle.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<AlertaDetalle>> reglas = validator.validate(alertaDetalle);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO AlertaDetalle : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,AlertaDetalle alertaDetalle) {
		alertaDetalle = prepararInsertar(usuarioActual, alertaDetalle);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, alertaDetalle);
		if (!lst.isEmpty())
			return lst;
		
		// TODO AlertaDetalle.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, AlertaDetalle alertaDetalle) {
		alertaDetalle = prepararActualizar(usuarioActual, alertaDetalle);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, alertaDetalle);
		if (!lst.isEmpty())
			return lst;
		
		// TODO AlertaDetalle.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,AlertaDetalle alertaDetalle) {
		alertaDetalle = prepararEliminar(usuarioActual, alertaDetalle);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO AlertaDetalle.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,AlertaDetallePk pk) {
		AlertaDetalle bean = alertaDetalleDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,Integer pidAlerta,Integer pidDetalle) {
		return coreEliminar(usuarioActual,new AlertaDetallePk( pidAlerta, pidDetalle));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, AlertaDetalle alertaDetalle) {
		alertaDetalle = prepararAnular(usuarioActual, alertaDetalle);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO AlertaDetalle.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, AlertaDetallePk pk) {
		AlertaDetalle bean = alertaDetalleDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, Integer pidAlerta,Integer pidDetalle) {
		return coreAnular(usuarioActual,new AlertaDetallePk( pidAlerta, pidDetalle));
	}

	public List<DominioMensajeUsuario> core(SeguridadUsuarioActual usuarioActual,String accion,AlertaDetalle alertaDetalle) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			return coreInsertar(usuarioActual, alertaDetalle);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			return coreActualizar(usuarioActual, alertaDetalle);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			return coreAnular(usuarioActual, alertaDetalle);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			return coreEliminar(usuarioActual, alertaDetalle);
		return lst;
	}

}
