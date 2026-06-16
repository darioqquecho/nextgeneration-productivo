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

import net.royal.spring.alertas.dao.impl.TipoProgramacionDaoImpl;
import net.royal.spring.alertas.dominio.TipoProgramacion;
import net.royal.spring.alertas.dominio.TipoProgramacionPk;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;
@SuppressWarnings("rawtypes")
@Service (value = "BeanValidarTipoProgramacion")
public class TipoProgramacionServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarTipoProgramacion";

	@Autowired
	private TipoProgramacionDaoImpl tipoProgramacionDao;

	private TipoProgramacion prepararBasico(SeguridadUsuarioActual usuarioActual,TipoProgramacion tipoProgramacion, Boolean flgInsertar) {
		if (flgInsertar) {
			tipoProgramacion.setCreacionTerminal(usuarioActual.getDireccionIp());
			tipoProgramacion.setCreacionFecha(new Date());
			tipoProgramacion.setCreacionUsuario(usuarioActual.getUsuario());
		} else {
			tipoProgramacion.setModificacionTerminal(usuarioActual.getDireccionIp());
			tipoProgramacion.setModificacionFecha(new Date());
			tipoProgramacion.setModificacionUsuario(usuarioActual.getUsuario());
		}
		
		// TODO TipoProgramacion : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return tipoProgramacion;
	}

	public TipoProgramacion prepararInsertar(SeguridadUsuarioActual usuarioActual,TipoProgramacion tipoProgramacion) {
		if (tipoProgramacion == null)
			return tipoProgramacion;
		if (tipoProgramacion.getAuxFlgPreparado())
			return tipoProgramacion;
		tipoProgramacion = prepararBasico(usuarioActual,tipoProgramacion, true);
		tipoProgramacion.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO TipoProgramacion.Insertar : valores por defecto
		
		return tipoProgramacion;
	}

	public TipoProgramacion prepararActualizar(SeguridadUsuarioActual usuarioActual,TipoProgramacion tipoProgramacion) {
		if (tipoProgramacion == null)
			return tipoProgramacion;
		if (tipoProgramacion.getAuxFlgPreparado())
			return tipoProgramacion;
		tipoProgramacion = prepararBasico(usuarioActual,tipoProgramacion, false);
		tipoProgramacion.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO TipoProgramacion.Actualizar : valores por defecto
		
		return tipoProgramacion;
	}

	public TipoProgramacion prepararAnular(SeguridadUsuarioActual usuarioActual,TipoProgramacion tipoProgramacion) {
		if (tipoProgramacion == null)
			return tipoProgramacion;
		if (tipoProgramacion.getAuxFlgPreparado())
			return tipoProgramacion;
		tipoProgramacion = prepararBasico(usuarioActual, tipoProgramacion, false);
		tipoProgramacion.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO TipoProgramacion.Anular : valores por defecto
		
		return tipoProgramacion;
	}

	public TipoProgramacion prepararEliminar(SeguridadUsuarioActual usuarioActual,TipoProgramacion tipoProgramacion) {
		if (tipoProgramacion == null)
			return tipoProgramacion;
		if (tipoProgramacion.getAuxFlgPreparado())
			return tipoProgramacion;
		tipoProgramacion.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO TipoProgramacion.Eliminar : valores por defecto
		
		return tipoProgramacion;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, TipoProgramacion tipoProgramacion) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (tipoProgramacion == null)
			lst.add(this.getMsjUsuarioError("tipoprogramacion.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (tipoProgramacion.getPk() != null) {
			Set<ConstraintViolation<TipoProgramacionPk>> reglasPk = validator.validate(tipoProgramacion.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<TipoProgramacion>> reglas = validator.validate(tipoProgramacion);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO TipoProgramacion : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,TipoProgramacion tipoProgramacion) {
		tipoProgramacion = prepararInsertar(usuarioActual, tipoProgramacion);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, tipoProgramacion);
		if (!lst.isEmpty())
			return lst;
		
		// TODO TipoProgramacion.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, TipoProgramacion tipoProgramacion) {
		tipoProgramacion = prepararActualizar(usuarioActual, tipoProgramacion);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, tipoProgramacion);
		if (!lst.isEmpty())
			return lst;
		
		// TODO TipoProgramacion.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,TipoProgramacion tipoProgramacion) {
		tipoProgramacion = prepararEliminar(usuarioActual, tipoProgramacion);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO TipoProgramacion.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,TipoProgramacionPk pk) {
		TipoProgramacion bean = tipoProgramacionDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String pidTipoProgramacion) {
		return coreEliminar(usuarioActual,new TipoProgramacionPk( pidTipoProgramacion));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, TipoProgramacion tipoProgramacion) {
		tipoProgramacion = prepararAnular(usuarioActual, tipoProgramacion);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO TipoProgramacion.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, TipoProgramacionPk pk) {
		TipoProgramacion bean = tipoProgramacionDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, String pidTipoProgramacion) {
		return coreAnular(usuarioActual,new TipoProgramacionPk( pidTipoProgramacion));
	}

	public List<DominioMensajeUsuario> core(SeguridadUsuarioActual usuarioActual,String accion,TipoProgramacion tipoProgramacion) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			return coreInsertar(usuarioActual, tipoProgramacion);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			return coreActualizar(usuarioActual, tipoProgramacion);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			return coreAnular(usuarioActual, tipoProgramacion);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			return coreEliminar(usuarioActual, tipoProgramacion);
		return lst;
	}

}
