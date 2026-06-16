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

import net.royal.spring.alertas.dao.impl.CorreoDaoImpl;
import net.royal.spring.alertas.dominio.Correo;
import net.royal.spring.alertas.dominio.CorreoPk;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.correo.EmailTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;
@SuppressWarnings("rawtypes")
@Service (value = "BeanValidarCorreo")
public class CorreoServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarCorreo";

	@Autowired
	private CorreoDaoImpl correoDao;

	private Correo prepararBasico(SeguridadUsuarioActual usuarioActual,Correo correo, Boolean flgInsertar) {
		if (flgInsertar) {
			correo.setCreacionTerminal(usuarioActual.getDireccionIp());
			correo.setCreacionFecha(new Date());
			correo.setCreacionUsuario(usuarioActual.getUsuario());
		} else {
			correo.setModificacionTerminal(usuarioActual.getDireccionIp());
			correo.setModificacionFecha(new Date());
			correo.setModificacionUsuario(usuarioActual.getUsuario());
		}
		
		// TODO Correo : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return correo;
	}

	public Correo prepararInsertar(SeguridadUsuarioActual usuarioActual,Correo correo) {
		if (correo == null)
			return correo;
		if (correo.getAuxFlgPreparado())
			return correo;
		correo = prepararBasico(usuarioActual,correo, true);
		correo.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO Correo.Insertar : valores por defecto
		if (UString.esNuloVacio(correo.getEstado()))
			correo.setEstado("PEN");
		correo.getPk().setIdCorreo(-1);
		
		if (UString.esNuloVacio(correo.getAuxTipoTransaccion()))
			correo.setAuxTipoTransaccion(EmailTransaccion.TIPO_TRANSACCION_ALERTA);
		
		return correo;
	}

	public Correo prepararActualizar(SeguridadUsuarioActual usuarioActual,Correo correo) {
		if (correo == null)
			return correo;
		if (correo.getAuxFlgPreparado())
			return correo;
		correo = prepararBasico(usuarioActual,correo, false);
		correo.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO Correo.Actualizar : valores por defecto
		
		return correo;
	}

	public Correo prepararAnular(SeguridadUsuarioActual usuarioActual,Correo correo) {
		if (correo == null)
			return correo;
		if (correo.getAuxFlgPreparado())
			return correo;
		correo = prepararBasico(usuarioActual, correo, false);
		correo.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO Correo.Anular : valores por defecto
		
		return correo;
	}

	public Correo prepararEliminar(SeguridadUsuarioActual usuarioActual,Correo correo) {
		if (correo == null)
			return correo;
		if (correo.getAuxFlgPreparado())
			return correo;
		correo.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO Correo.Eliminar : valores por defecto
		
		return correo;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, Correo correo) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (correo == null)
			lst.add(this.getMsjUsuarioError("correo.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (correo.getPk() != null) {
			Set<ConstraintViolation<CorreoPk>> reglasPk = validator.validate(correo.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<Correo>> reglas = validator.validate(correo);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO Correo : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,Correo correo) {
		correo = prepararInsertar(usuarioActual, correo);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, correo);
		if (!lst.isEmpty())
			return lst;
		
		// TODO Correo.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, Correo correo) {
		correo = prepararActualizar(usuarioActual, correo);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, correo);
		if (!lst.isEmpty())
			return lst;
		
		// TODO Correo.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,Correo correo) {
		correo = prepararEliminar(usuarioActual, correo);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO Correo.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,CorreoPk pk) {
		Correo bean = correoDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,Integer pidCorreo) {
		return coreEliminar(usuarioActual,new CorreoPk( pidCorreo));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, Correo correo) {
		correo = prepararAnular(usuarioActual, correo);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO Correo.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, CorreoPk pk) {
		Correo bean = correoDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, Integer pidCorreo) {
		return coreAnular(usuarioActual,new CorreoPk( pidCorreo));
	}

	public List<DominioMensajeUsuario> core(SeguridadUsuarioActual usuarioActual,String accion,Correo correo) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			return coreInsertar(usuarioActual, correo);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			return coreActualizar(usuarioActual, correo);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			return coreAnular(usuarioActual, correo);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			return coreEliminar(usuarioActual, correo);
		return lst;
	}

}
