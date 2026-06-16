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

import net.royal.spring.alertas.dao.impl.ReglaNegocioAdicionalDaoImpl;
import net.royal.spring.alertas.dominio.ReglaNegocioAdicional;
import net.royal.spring.alertas.dominio.ReglaNegocioAdicionalPk;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;
@SuppressWarnings("rawtypes")
@Service (value = "BeanValidarReglaNegocioAdicional")
public class ReglaNegocioAdicionalServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarReglaNegocioAdicional";

	@Autowired
	private ReglaNegocioAdicionalDaoImpl reglaNegocioAdicionalDao;

	private ReglaNegocioAdicional prepararBasico(SeguridadUsuarioActual usuarioActual,ReglaNegocioAdicional reglaNegocioAdicional, Boolean flgInsertar) {
		if (flgInsertar) {
			reglaNegocioAdicional.setCreacionTerminal(usuarioActual.getDireccionIp());
			reglaNegocioAdicional.setCreacionFecha(new Date());
			reglaNegocioAdicional.setCreacionUsuario(usuarioActual.getUsuario());
		} else {
			reglaNegocioAdicional.setModificacionTerminal(usuarioActual.getDireccionIp());
			reglaNegocioAdicional.setModificacionFecha(new Date());
			reglaNegocioAdicional.setModificacionUsuario(usuarioActual.getUsuario());
		}
		
		// TODO ReglaNegocioAdicional : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return reglaNegocioAdicional;
	}

	public ReglaNegocioAdicional prepararInsertar(SeguridadUsuarioActual usuarioActual,ReglaNegocioAdicional reglaNegocioAdicional) {
		if (reglaNegocioAdicional == null)
			return reglaNegocioAdicional;
		if (reglaNegocioAdicional.getAuxFlgPreparado())
			return reglaNegocioAdicional;
		reglaNegocioAdicional = prepararBasico(usuarioActual,reglaNegocioAdicional, true);
		reglaNegocioAdicional.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO ReglaNegocioAdicional.Insertar : valores por defecto
		
		return reglaNegocioAdicional;
	}

	public ReglaNegocioAdicional prepararActualizar(SeguridadUsuarioActual usuarioActual,ReglaNegocioAdicional reglaNegocioAdicional) {
		if (reglaNegocioAdicional == null)
			return reglaNegocioAdicional;
		if (reglaNegocioAdicional.getAuxFlgPreparado())
			return reglaNegocioAdicional;
		reglaNegocioAdicional = prepararBasico(usuarioActual,reglaNegocioAdicional, false);
		reglaNegocioAdicional.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO ReglaNegocioAdicional.Actualizar : valores por defecto
		
		return reglaNegocioAdicional;
	}

	public ReglaNegocioAdicional prepararAnular(SeguridadUsuarioActual usuarioActual,ReglaNegocioAdicional reglaNegocioAdicional) {
		if (reglaNegocioAdicional == null)
			return reglaNegocioAdicional;
		if (reglaNegocioAdicional.getAuxFlgPreparado())
			return reglaNegocioAdicional;
		reglaNegocioAdicional = prepararBasico(usuarioActual, reglaNegocioAdicional, false);
		reglaNegocioAdicional.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO ReglaNegocioAdicional.Anular : valores por defecto
		
		return reglaNegocioAdicional;
	}

	public ReglaNegocioAdicional prepararEliminar(SeguridadUsuarioActual usuarioActual,ReglaNegocioAdicional reglaNegocioAdicional) {
		if (reglaNegocioAdicional == null)
			return reglaNegocioAdicional;
		if (reglaNegocioAdicional.getAuxFlgPreparado())
			return reglaNegocioAdicional;
		reglaNegocioAdicional.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO ReglaNegocioAdicional.Eliminar : valores por defecto
		
		return reglaNegocioAdicional;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, ReglaNegocioAdicional reglaNegocioAdicional) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (reglaNegocioAdicional == null)
			lst.add(this.getMsjUsuarioError("reglanegocioadicional.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (reglaNegocioAdicional.getPk() != null) {
			Set<ConstraintViolation<ReglaNegocioAdicionalPk>> reglasPk = validator.validate(reglaNegocioAdicional.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<ReglaNegocioAdicional>> reglas = validator.validate(reglaNegocioAdicional);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO ReglaNegocioAdicional : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,ReglaNegocioAdicional reglaNegocioAdicional) {
		reglaNegocioAdicional = prepararInsertar(usuarioActual, reglaNegocioAdicional);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, reglaNegocioAdicional);
		if (!lst.isEmpty())
			return lst;
		
		// TODO ReglaNegocioAdicional.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, ReglaNegocioAdicional reglaNegocioAdicional) {
		reglaNegocioAdicional = prepararActualizar(usuarioActual, reglaNegocioAdicional);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, reglaNegocioAdicional);
		if (!lst.isEmpty())
			return lst;
		
		// TODO ReglaNegocioAdicional.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,ReglaNegocioAdicional reglaNegocioAdicional) {
		reglaNegocioAdicional = prepararEliminar(usuarioActual, reglaNegocioAdicional);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO ReglaNegocioAdicional.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,ReglaNegocioAdicionalPk pk) {
		ReglaNegocioAdicional bean = reglaNegocioAdicionalDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,Integer pidReglaNegocio,Integer pidAdicional) {
		return coreEliminar(usuarioActual,new ReglaNegocioAdicionalPk( pidReglaNegocio, pidAdicional));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, ReglaNegocioAdicional reglaNegocioAdicional) {
		reglaNegocioAdicional = prepararAnular(usuarioActual, reglaNegocioAdicional);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO ReglaNegocioAdicional.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, ReglaNegocioAdicionalPk pk) {
		ReglaNegocioAdicional bean = reglaNegocioAdicionalDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, Integer pidReglaNegocio,Integer pidAdicional) {
		return coreAnular(usuarioActual,new ReglaNegocioAdicionalPk( pidReglaNegocio, pidAdicional));
	}

	public List<DominioMensajeUsuario> core(SeguridadUsuarioActual usuarioActual,String accion,ReglaNegocioAdicional reglaNegocioAdicional) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			return coreInsertar(usuarioActual, reglaNegocioAdicional);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			return coreActualizar(usuarioActual, reglaNegocioAdicional);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			return coreAnular(usuarioActual, reglaNegocioAdicional);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			return coreEliminar(usuarioActual, reglaNegocioAdicional);
		return lst;
	}

}
