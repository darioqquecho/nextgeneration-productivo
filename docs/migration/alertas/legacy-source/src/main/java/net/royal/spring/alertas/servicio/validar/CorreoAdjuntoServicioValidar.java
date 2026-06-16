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

import net.royal.spring.alertas.dao.impl.CorreoAdjuntoDaoImpl;
import net.royal.spring.alertas.dominio.CorreoAdjunto;
import net.royal.spring.alertas.dominio.CorreoAdjuntoPk;
import net.royal.spring.alertas.dominio.CorreoDestino;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;
@SuppressWarnings("rawtypes")
@Service (value = "BeanValidarCorreoAdjunto")
public class CorreoAdjuntoServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarCorreoAdjunto";

	@Autowired
	private CorreoAdjuntoDaoImpl correoAdjuntoDao;

	private CorreoAdjunto prepararBasico(SeguridadUsuarioActual usuarioActual,CorreoAdjunto correoAdjunto, Boolean flgInsertar) {
		if (flgInsertar) {
			correoAdjunto.setCreacionTerminal(usuarioActual.getDireccionIp());
			correoAdjunto.setCreacionFecha(new Date());
			correoAdjunto.setCreacionUsuario(usuarioActual.getUsuario());
		} else {
			correoAdjunto.setModificacionTerminal(usuarioActual.getDireccionIp());
			correoAdjunto.setModificacionFecha(new Date());
			correoAdjunto.setModificacionUsuario(usuarioActual.getUsuario());
		}
		
		// TODO CorreoAdjunto : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return correoAdjunto;
	}

	public CorreoAdjunto prepararInsertar(SeguridadUsuarioActual usuarioActual,CorreoAdjunto correoAdjunto) {
		if (correoAdjunto == null)
			return correoAdjunto;
		if (correoAdjunto.getAuxFlgPreparado())
			return correoAdjunto;
		correoAdjunto = prepararBasico(usuarioActual,correoAdjunto, true);
		correoAdjunto.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO CorreoAdjunto.Insertar : valores por defecto
		correoAdjunto.getPk().setIdCorreo(-1);
		correoAdjunto.getPk().setIdAdjunto(-1);
		return correoAdjunto;
	}
	
	public List<CorreoAdjunto> prepararInsertar(SeguridadUsuarioActual usuarioActual,List<CorreoAdjunto> lista) {
		if (lista==null)
			lista=new ArrayList<CorreoAdjunto>();		
		Integer index=0;
		for (CorreoAdjunto correoAdjunto : lista) {	
			correoAdjunto = prepararInsertar(usuarioActual,correoAdjunto);
			lista.set(index, correoAdjunto);
			index++;
		}		
		return lista;
	}

	public CorreoAdjunto prepararActualizar(SeguridadUsuarioActual usuarioActual,CorreoAdjunto correoAdjunto) {
		if (correoAdjunto == null)
			return correoAdjunto;
		if (correoAdjunto.getAuxFlgPreparado())
			return correoAdjunto;
		correoAdjunto = prepararBasico(usuarioActual,correoAdjunto, false);
		correoAdjunto.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO CorreoAdjunto.Actualizar : valores por defecto
		
		return correoAdjunto;
	}

	public CorreoAdjunto prepararAnular(SeguridadUsuarioActual usuarioActual,CorreoAdjunto correoAdjunto) {
		if (correoAdjunto == null)
			return correoAdjunto;
		if (correoAdjunto.getAuxFlgPreparado())
			return correoAdjunto;
		correoAdjunto = prepararBasico(usuarioActual, correoAdjunto, false);
		correoAdjunto.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO CorreoAdjunto.Anular : valores por defecto
		
		return correoAdjunto;
	}

	public CorreoAdjunto prepararEliminar(SeguridadUsuarioActual usuarioActual,CorreoAdjunto correoAdjunto) {
		if (correoAdjunto == null)
			return correoAdjunto;
		if (correoAdjunto.getAuxFlgPreparado())
			return correoAdjunto;
		correoAdjunto.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO CorreoAdjunto.Eliminar : valores por defecto
		
		return correoAdjunto;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, CorreoAdjunto correoAdjunto) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (correoAdjunto == null)
			lst.add(this.getMsjUsuarioError("correoadjunto.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (correoAdjunto.getPk() != null) {
			Set<ConstraintViolation<CorreoAdjuntoPk>> reglasPk = validator.validate(correoAdjunto.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<CorreoAdjunto>> reglas = validator.validate(correoAdjunto);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO CorreoAdjunto : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,CorreoAdjunto correoAdjunto) {
		correoAdjunto = prepararInsertar(usuarioActual, correoAdjunto);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, correoAdjunto);
		if (!lst.isEmpty())
			return lst;
		
		// TODO CorreoAdjunto.Insertar : validaciones
		
		return lst;
	}
	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,List<CorreoAdjunto> lista) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (lista==null)
			return lst;		
		for (CorreoAdjunto adjunto : lista) {
			List<DominioMensajeUsuario> lst2 = coreInsertar(usuarioActual,adjunto);
			lst.addAll(lst2);
		}		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, CorreoAdjunto correoAdjunto) {
		correoAdjunto = prepararActualizar(usuarioActual, correoAdjunto);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, correoAdjunto);
		if (!lst.isEmpty())
			return lst;
		
		// TODO CorreoAdjunto.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,CorreoAdjunto correoAdjunto) {
		correoAdjunto = prepararEliminar(usuarioActual, correoAdjunto);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO CorreoAdjunto.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,CorreoAdjuntoPk pk) {
		CorreoAdjunto bean = correoAdjuntoDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,Integer pidCorreo,Integer pidAdjunto) {
		return coreEliminar(usuarioActual,new CorreoAdjuntoPk( pidCorreo, pidAdjunto));
	}


	public List<DominioMensajeUsuario> core(SeguridadUsuarioActual usuarioActual,String accion,CorreoAdjunto correoAdjunto) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			return coreInsertar(usuarioActual, correoAdjunto);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			return coreActualizar(usuarioActual, correoAdjunto);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			return coreEliminar(usuarioActual, correoAdjunto);
		return lst;
	}

}
