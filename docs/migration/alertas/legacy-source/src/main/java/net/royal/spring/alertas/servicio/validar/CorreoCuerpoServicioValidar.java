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

import net.royal.spring.alertas.dao.impl.CorreoCuerpoDaoImpl;
import net.royal.spring.alertas.dominio.CorreoCuerpo;
import net.royal.spring.alertas.dominio.CorreoCuerpoPk;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;
@SuppressWarnings("rawtypes")
@Service (value = "BeanValidarCorreoCuerpo")
public class CorreoCuerpoServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarCorreoCuerpo";

	@Autowired
	private CorreoCuerpoDaoImpl correoCuerpoDao;

	private CorreoCuerpo prepararBasico(SeguridadUsuarioActual usuarioActual,CorreoCuerpo correoCuerpo, Boolean flgInsertar) {
		if (flgInsertar) {
			correoCuerpo.setCreacionTerminal(usuarioActual.getDireccionIp());
			correoCuerpo.setCreacionFecha(new Date());
			correoCuerpo.setCreacionUsuario(usuarioActual.getUsuario());
		} else {
			correoCuerpo.setModificacionTerminal(usuarioActual.getDireccionIp());
			correoCuerpo.setModificacionFecha(new Date());
			correoCuerpo.setModificacionUsuario(usuarioActual.getUsuario());
		}
		
		// TODO CorreoCuerpo : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return correoCuerpo;
	}

	public CorreoCuerpo prepararInsertar(SeguridadUsuarioActual usuarioActual,CorreoCuerpo correoCuerpo) {
		if (correoCuerpo == null)
			return correoCuerpo;
		if (correoCuerpo.getAuxFlgPreparado())
			return correoCuerpo;
		correoCuerpo = prepararBasico(usuarioActual,correoCuerpo, true);
		correoCuerpo.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO CorreoCuerpo.Insertar : valores por defecto
		
		return correoCuerpo;
	}

	public CorreoCuerpo prepararActualizar(SeguridadUsuarioActual usuarioActual,CorreoCuerpo correoCuerpo) {
		if (correoCuerpo == null)
			return correoCuerpo;
		if (correoCuerpo.getAuxFlgPreparado())
			return correoCuerpo;
		correoCuerpo = prepararBasico(usuarioActual,correoCuerpo, false);
		correoCuerpo.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO CorreoCuerpo.Actualizar : valores por defecto
		
		return correoCuerpo;
	}

	public CorreoCuerpo prepararAnular(SeguridadUsuarioActual usuarioActual,CorreoCuerpo correoCuerpo) {
		if (correoCuerpo == null)
			return correoCuerpo;
		if (correoCuerpo.getAuxFlgPreparado())
			return correoCuerpo;
		correoCuerpo = prepararBasico(usuarioActual, correoCuerpo, false);
		correoCuerpo.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO CorreoCuerpo.Anular : valores por defecto
		
		return correoCuerpo;
	}

	public CorreoCuerpo prepararEliminar(SeguridadUsuarioActual usuarioActual,CorreoCuerpo correoCuerpo) {
		if (correoCuerpo == null)
			return correoCuerpo;
		if (correoCuerpo.getAuxFlgPreparado())
			return correoCuerpo;
		correoCuerpo.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO CorreoCuerpo.Eliminar : valores por defecto
		
		return correoCuerpo;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, CorreoCuerpo correoCuerpo) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (correoCuerpo == null)
			lst.add(this.getMsjUsuarioError("correocuerpo.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (correoCuerpo.getPk() != null) {
			Set<ConstraintViolation<CorreoCuerpoPk>> reglasPk = validator.validate(correoCuerpo.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<CorreoCuerpo>> reglas = validator.validate(correoCuerpo);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO CorreoCuerpo : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,CorreoCuerpo correoCuerpo) {
		correoCuerpo = prepararInsertar(usuarioActual, correoCuerpo);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, correoCuerpo);
		if (!lst.isEmpty())
			return lst;
		
		// TODO CorreoCuerpo.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, CorreoCuerpo correoCuerpo) {
		correoCuerpo = prepararActualizar(usuarioActual, correoCuerpo);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, correoCuerpo);
		if (!lst.isEmpty())
			return lst;
		
		// TODO CorreoCuerpo.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,CorreoCuerpo correoCuerpo) {
		correoCuerpo = prepararEliminar(usuarioActual, correoCuerpo);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO CorreoCuerpo.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,CorreoCuerpoPk pk) {
		CorreoCuerpo bean = correoCuerpoDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,Integer pidCorreo) {
		return coreEliminar(usuarioActual,new CorreoCuerpoPk( pidCorreo));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, CorreoCuerpo correoCuerpo) {
		correoCuerpo = prepararAnular(usuarioActual, correoCuerpo);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO CorreoCuerpo.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, CorreoCuerpoPk pk) {
		CorreoCuerpo bean = correoCuerpoDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, Integer pidCorreo) {
		return coreAnular(usuarioActual,new CorreoCuerpoPk( pidCorreo));
	}

	public List<DominioMensajeUsuario> core(SeguridadUsuarioActual usuarioActual,String accion,CorreoCuerpo correoCuerpo) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			return coreInsertar(usuarioActual, correoCuerpo);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			return coreActualizar(usuarioActual, correoCuerpo);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			return coreAnular(usuarioActual, correoCuerpo);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			return coreEliminar(usuarioActual, correoCuerpo);
		return lst;
	}

}
