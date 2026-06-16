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

import net.royal.spring.alertas.dao.impl.FrecuenciaDaoImpl;
import net.royal.spring.alertas.dominio.Frecuencia;
import net.royal.spring.alertas.dominio.FrecuenciaPk;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;
@SuppressWarnings("rawtypes")
@Service (value = "BeanValidarFrecuencia")
public class FrecuenciaServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarFrecuencia";

	@Autowired
	private FrecuenciaDaoImpl frecuenciaDao;

	private Frecuencia prepararBasico(SeguridadUsuarioActual usuarioActual,Frecuencia frecuencia, Boolean flgInsertar) {
		if (flgInsertar) {
			frecuencia.setCreacionTerminal(usuarioActual.getDireccionIp());
			frecuencia.setCreacionFecha(new Date());
			frecuencia.setCreacionUsuario(usuarioActual.getUsuario());
		} else {
			frecuencia.setModificacionTerminal(usuarioActual.getDireccionIp());
			frecuencia.setModificacionFecha(new Date());
			frecuencia.setModificacionUsuario(usuarioActual.getUsuario());
		}
		
		// TODO Frecuencia : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return frecuencia;
	}

	public Frecuencia prepararInsertar(SeguridadUsuarioActual usuarioActual,Frecuencia frecuencia) {
		if (frecuencia == null)
			return frecuencia;
		if (frecuencia.getAuxFlgPreparado())
			return frecuencia;
		frecuencia = prepararBasico(usuarioActual,frecuencia, true);
		frecuencia.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO Frecuencia.Insertar : valores por defecto
		
		return frecuencia;
	}

	public Frecuencia prepararActualizar(SeguridadUsuarioActual usuarioActual,Frecuencia frecuencia) {
		if (frecuencia == null)
			return frecuencia;
		if (frecuencia.getAuxFlgPreparado())
			return frecuencia;
		frecuencia = prepararBasico(usuarioActual,frecuencia, false);
		frecuencia.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO Frecuencia.Actualizar : valores por defecto
		
		return frecuencia;
	}

	public Frecuencia prepararAnular(SeguridadUsuarioActual usuarioActual,Frecuencia frecuencia) {
		if (frecuencia == null)
			return frecuencia;
		if (frecuencia.getAuxFlgPreparado())
			return frecuencia;
		frecuencia = prepararBasico(usuarioActual, frecuencia, false);
		frecuencia.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO Frecuencia.Anular : valores por defecto
		
		return frecuencia;
	}

	public Frecuencia prepararEliminar(SeguridadUsuarioActual usuarioActual,Frecuencia frecuencia) {
		if (frecuencia == null)
			return frecuencia;
		if (frecuencia.getAuxFlgPreparado())
			return frecuencia;
		frecuencia.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO Frecuencia.Eliminar : valores por defecto
		
		return frecuencia;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, Frecuencia frecuencia) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (frecuencia == null)
			lst.add(this.getMsjUsuarioError("frecuencia.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (frecuencia.getPk() != null) {
			Set<ConstraintViolation<FrecuenciaPk>> reglasPk = validator.validate(frecuencia.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<Frecuencia>> reglas = validator.validate(frecuencia);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO Frecuencia : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,Frecuencia frecuencia) {
		frecuencia = prepararInsertar(usuarioActual, frecuencia);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, frecuencia);
		if (!lst.isEmpty())
			return lst;
		
		// TODO Frecuencia.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, Frecuencia frecuencia) {
		frecuencia = prepararActualizar(usuarioActual, frecuencia);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, frecuencia);
		if (!lst.isEmpty())
			return lst;
		
		// TODO Frecuencia.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,Frecuencia frecuencia) {
		frecuencia = prepararEliminar(usuarioActual, frecuencia);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO Frecuencia.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,FrecuenciaPk pk) {
		Frecuencia bean = frecuenciaDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String pidFrecuencia) {
		return coreEliminar(usuarioActual,new FrecuenciaPk( pidFrecuencia));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, Frecuencia frecuencia) {
		frecuencia = prepararAnular(usuarioActual, frecuencia);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO Frecuencia.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, FrecuenciaPk pk) {
		Frecuencia bean = frecuenciaDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, String pidFrecuencia) {
		return coreAnular(usuarioActual,new FrecuenciaPk( pidFrecuencia));
	}

	public List<DominioMensajeUsuario> core(SeguridadUsuarioActual usuarioActual,String accion,Frecuencia frecuencia) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			return coreInsertar(usuarioActual, frecuencia);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			return coreActualizar(usuarioActual, frecuencia);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			return coreAnular(usuarioActual, frecuencia);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			return coreEliminar(usuarioActual, frecuencia);
		return lst;
	}

}
