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

import net.royal.spring.alertas.dao.impl.PerfilCorreoDaoImpl;
import net.royal.spring.alertas.dominio.PerfilCorreo;
import net.royal.spring.alertas.dominio.PerfilCorreoPk;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;
@SuppressWarnings("rawtypes")
@Service (value = "BeanValidarPerfilCorreo")
public class PerfilCorreoServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarPerfilCorreo";

	@Autowired
	private PerfilCorreoDaoImpl perfilCorreoDao;

	private PerfilCorreo prepararBasico(SeguridadUsuarioActual usuarioActual,PerfilCorreo perfilCorreo, Boolean flgInsertar) {
		if (flgInsertar) {
			perfilCorreo.setCreacionTerminal(usuarioActual.getDireccionIp());
			perfilCorreo.setCreacionFecha(new Date());
			perfilCorreo.setCreacionUsuario(usuarioActual.getUsuario());
		} else {
			perfilCorreo.setModificacionTerminal(usuarioActual.getDireccionIp());
			perfilCorreo.setModificacionFecha(new Date());
			perfilCorreo.setModificacionUsuario(usuarioActual.getUsuario());
		}
		
		// TODO PerfilCorreo : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return perfilCorreo;
	}

	public PerfilCorreo prepararInsertar(SeguridadUsuarioActual usuarioActual,PerfilCorreo perfilCorreo) {
		if (perfilCorreo == null)
			return perfilCorreo;
		if (perfilCorreo.getAuxFlgPreparado())
			return perfilCorreo;
		perfilCorreo = prepararBasico(usuarioActual,perfilCorreo, true);
		perfilCorreo.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO PerfilCorreo.Insertar : valores por defecto
		
		return perfilCorreo;
	}

	public PerfilCorreo prepararActualizar(SeguridadUsuarioActual usuarioActual,PerfilCorreo perfilCorreo) {
		if (perfilCorreo == null)
			return perfilCorreo;
		if (perfilCorreo.getAuxFlgPreparado())
			return perfilCorreo;
		perfilCorreo = prepararBasico(usuarioActual,perfilCorreo, false);
		perfilCorreo.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO PerfilCorreo.Actualizar : valores por defecto
		
		return perfilCorreo;
	}

	public PerfilCorreo prepararAnular(SeguridadUsuarioActual usuarioActual,PerfilCorreo perfilCorreo) {
		if (perfilCorreo == null)
			return perfilCorreo;
		if (perfilCorreo.getAuxFlgPreparado())
			return perfilCorreo;
		perfilCorreo = prepararBasico(usuarioActual, perfilCorreo, false);
		perfilCorreo.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO PerfilCorreo.Anular : valores por defecto
		
		return perfilCorreo;
	}

	public PerfilCorreo prepararEliminar(SeguridadUsuarioActual usuarioActual,PerfilCorreo perfilCorreo) {
		if (perfilCorreo == null)
			return perfilCorreo;
		if (perfilCorreo.getAuxFlgPreparado())
			return perfilCorreo;
		perfilCorreo.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO PerfilCorreo.Eliminar : valores por defecto
		
		return perfilCorreo;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, PerfilCorreo perfilCorreo) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (perfilCorreo == null)
			lst.add(this.getMsjUsuarioError("perfilcorreo.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (perfilCorreo.getPk() != null) {
			Set<ConstraintViolation<PerfilCorreoPk>> reglasPk = validator.validate(perfilCorreo.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<PerfilCorreo>> reglas = validator.validate(perfilCorreo);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO PerfilCorreo : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,PerfilCorreo perfilCorreo) {
		perfilCorreo = prepararInsertar(usuarioActual, perfilCorreo);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, perfilCorreo);
		if (!lst.isEmpty())
			return lst;
		
		// TODO PerfilCorreo.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, PerfilCorreo perfilCorreo) {
		perfilCorreo = prepararActualizar(usuarioActual, perfilCorreo);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, perfilCorreo);
		if (!lst.isEmpty())
			return lst;
		
		// TODO PerfilCorreo.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,PerfilCorreo perfilCorreo) {
		perfilCorreo = prepararEliminar(usuarioActual, perfilCorreo);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO PerfilCorreo.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,PerfilCorreoPk pk) {
		PerfilCorreo bean = perfilCorreoDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,Integer pidPerfilCorreo) {
		return coreEliminar(usuarioActual,new PerfilCorreoPk( pidPerfilCorreo));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, PerfilCorreo perfilCorreo) {
		perfilCorreo = prepararAnular(usuarioActual, perfilCorreo);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO PerfilCorreo.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, PerfilCorreoPk pk) {
		PerfilCorreo bean = perfilCorreoDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, Integer pidPerfilCorreo) {
		return coreAnular(usuarioActual,new PerfilCorreoPk( pidPerfilCorreo));
	}

	public List<DominioMensajeUsuario> core(SeguridadUsuarioActual usuarioActual,String accion,PerfilCorreo perfilCorreo) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			return coreInsertar(usuarioActual, perfilCorreo);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			return coreActualizar(usuarioActual, perfilCorreo);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			return coreAnular(usuarioActual, perfilCorreo);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			return coreEliminar(usuarioActual, perfilCorreo);
		return lst;
	}

}
