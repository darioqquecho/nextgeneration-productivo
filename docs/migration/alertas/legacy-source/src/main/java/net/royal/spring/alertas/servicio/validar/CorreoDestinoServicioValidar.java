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

import net.royal.spring.alertas.dao.impl.CorreoDestinoDaoImpl;
import net.royal.spring.alertas.dominio.CorreoDestino;
import net.royal.spring.alertas.dominio.CorreoDestinoPk;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;
@SuppressWarnings("rawtypes")
@Service (value = "BeanValidarCorreoDestino")
public class CorreoDestinoServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarCorreoDestino";

	@Autowired
	private CorreoDestinoDaoImpl correoDestinoDao;

	private CorreoDestino prepararBasico(SeguridadUsuarioActual usuarioActual,CorreoDestino correoDestino, Boolean flgInsertar) {
		if (flgInsertar) {
			correoDestino.setCreacionTerminal(usuarioActual.getDireccionIp());
			correoDestino.setCreacionFecha(new Date());
			correoDestino.setCreacionUsuario(usuarioActual.getUsuario());
		} else {
			correoDestino.setModificacionTerminal(usuarioActual.getDireccionIp());
			correoDestino.setModificacionFecha(new Date());
			correoDestino.setModificacionUsuario(usuarioActual.getUsuario());
		}
		
		// TODO CorreoDestino : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return correoDestino;
	}

	public CorreoDestino prepararInsertar(SeguridadUsuarioActual usuarioActual,CorreoDestino correoDestino) {
		if (correoDestino == null)
			return correoDestino;
		if (correoDestino.getAuxFlgPreparado())
			return correoDestino;
		correoDestino = prepararBasico(usuarioActual,correoDestino, true);
		correoDestino.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO CorreoDestino.Insertar : valores por defecto
		correoDestino.getPk().setIdCorreo(-1);
		return correoDestino;
	}
	
	public List<CorreoDestino> prepararInsertar(SeguridadUsuarioActual usuarioActual,List<CorreoDestino> lista) {
		if (lista==null)
			lista=new ArrayList<CorreoDestino>();		
		Integer index=0;
		for (CorreoDestino correoDestino : lista) {	
			correoDestino = prepararInsertar(usuarioActual,correoDestino);
			lista.set(index, correoDestino);
			index++;
		}		
		return lista;
	}

	public CorreoDestino prepararActualizar(SeguridadUsuarioActual usuarioActual,CorreoDestino correoDestino) {
		if (correoDestino == null)
			return correoDestino;
		if (correoDestino.getAuxFlgPreparado())
			return correoDestino;
		correoDestino = prepararBasico(usuarioActual,correoDestino, false);
		correoDestino.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO CorreoDestino.Actualizar : valores por defecto
		
		return correoDestino;
	}

	public CorreoDestino prepararAnular(SeguridadUsuarioActual usuarioActual,CorreoDestino correoDestino) {
		if (correoDestino == null)
			return correoDestino;
		if (correoDestino.getAuxFlgPreparado())
			return correoDestino;
		correoDestino = prepararBasico(usuarioActual, correoDestino, false);
		correoDestino.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO CorreoDestino.Anular : valores por defecto
		
		return correoDestino;
	}

	public CorreoDestino prepararEliminar(SeguridadUsuarioActual usuarioActual,CorreoDestino correoDestino) {
		if (correoDestino == null)
			return correoDestino;
		if (correoDestino.getAuxFlgPreparado())
			return correoDestino;
		correoDestino.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO CorreoDestino.Eliminar : valores por defecto
		
		return correoDestino;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, CorreoDestino correoDestino) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (correoDestino == null)
			lst.add(this.getMsjUsuarioError("correodestino.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (correoDestino.getPk() != null) {
			Set<ConstraintViolation<CorreoDestinoPk>> reglasPk = validator.validate(correoDestino.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<CorreoDestino>> reglas = validator.validate(correoDestino);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO CorreoDestino : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,CorreoDestino correoDestino) {
		correoDestino = prepararInsertar(usuarioActual, correoDestino);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, correoDestino);
		if (!lst.isEmpty())
			return lst;
		
		// TODO CorreoDestino.Insertar : validaciones
		
		return lst;
	}
	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,List<CorreoDestino> lista) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (lista==null)
			return lst;		
		for (CorreoDestino correoDestino : lista) {
			List<DominioMensajeUsuario> lst2 = coreInsertar(usuarioActual,correoDestino);
			lst.addAll(lst2);
		}		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, CorreoDestino correoDestino) {
		correoDestino = prepararActualizar(usuarioActual, correoDestino);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, correoDestino);
		if (!lst.isEmpty())
			return lst;
		
		// TODO CorreoDestino.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,CorreoDestino correoDestino) {
		correoDestino = prepararEliminar(usuarioActual, correoDestino);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO CorreoDestino.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,CorreoDestinoPk pk) {
		CorreoDestino bean = correoDestinoDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,Integer pidCorreo,String pcorreoDestino) {
		return coreEliminar(usuarioActual,new CorreoDestinoPk( pidCorreo, pcorreoDestino));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, CorreoDestino correoDestino) {
		correoDestino = prepararAnular(usuarioActual, correoDestino);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO CorreoDestino.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, CorreoDestinoPk pk) {
		CorreoDestino bean = correoDestinoDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, Integer pidCorreo,String pcorreoDestino) {
		return coreAnular(usuarioActual,new CorreoDestinoPk( pidCorreo, pcorreoDestino));
	}

	public List<DominioMensajeUsuario> core(SeguridadUsuarioActual usuarioActual,String accion,CorreoDestino correoDestino) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			return coreInsertar(usuarioActual, correoDestino);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			return coreActualizar(usuarioActual, correoDestino);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			return coreAnular(usuarioActual, correoDestino);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			return coreEliminar(usuarioActual, correoDestino);
		return lst;
	}

}
