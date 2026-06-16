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

import net.royal.spring.alertas.dao.impl.ReglaNegocioDaoImpl;
import net.royal.spring.alertas.dominio.ReglaNegocio;
import net.royal.spring.alertas.dominio.ReglaNegocioPk;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;
@SuppressWarnings("rawtypes")
@Service (value = "BeanValidarReglaNegocio")
public class ReglaNegocioServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarReglaNegocio";

	@Autowired
	private ReglaNegocioDaoImpl reglaNegocioDao;

	private ReglaNegocio prepararBasico(SeguridadUsuarioActual usuarioActual,ReglaNegocio reglaNegocio, Boolean flgInsertar) {
		if (flgInsertar) {
			reglaNegocio.setCreacionTerminal(usuarioActual.getDireccionIp());
			reglaNegocio.setCreacionFecha(new Date());
			reglaNegocio.setCreacionUsuario(usuarioActual.getUsuario());
		} else {
			reglaNegocio.setModificacionTerminal(usuarioActual.getDireccionIp());
			reglaNegocio.setModificacionFecha(new Date());
			reglaNegocio.setModificacionUsuario(usuarioActual.getUsuario());
		}
		
		// TODO ReglaNegocio : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return reglaNegocio;
	}

	public ReglaNegocio prepararInsertar(SeguridadUsuarioActual usuarioActual,ReglaNegocio reglaNegocio) {
		if (reglaNegocio == null)
			return reglaNegocio;
		if (reglaNegocio.getAuxFlgPreparado())
			return reglaNegocio;
		reglaNegocio = prepararBasico(usuarioActual,reglaNegocio, true);
		reglaNegocio.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO ReglaNegocio.Insertar : valores por defecto
		
		return reglaNegocio;
	}

	public ReglaNegocio prepararActualizar(SeguridadUsuarioActual usuarioActual,ReglaNegocio reglaNegocio) {
		if (reglaNegocio == null)
			return reglaNegocio;
		if (reglaNegocio.getAuxFlgPreparado())
			return reglaNegocio;
		reglaNegocio = prepararBasico(usuarioActual,reglaNegocio, false);
		reglaNegocio.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO ReglaNegocio.Actualizar : valores por defecto
		
		return reglaNegocio;
	}

	public ReglaNegocio prepararAnular(SeguridadUsuarioActual usuarioActual,ReglaNegocio reglaNegocio) {
		if (reglaNegocio == null)
			return reglaNegocio;
		if (reglaNegocio.getAuxFlgPreparado())
			return reglaNegocio;
		reglaNegocio = prepararBasico(usuarioActual, reglaNegocio, false);
		reglaNegocio.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO ReglaNegocio.Anular : valores por defecto
		
		return reglaNegocio;
	}

	public ReglaNegocio prepararEliminar(SeguridadUsuarioActual usuarioActual,ReglaNegocio reglaNegocio) {
		if (reglaNegocio == null)
			return reglaNegocio;
		if (reglaNegocio.getAuxFlgPreparado())
			return reglaNegocio;
		reglaNegocio.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO ReglaNegocio.Eliminar : valores por defecto
		
		return reglaNegocio;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, ReglaNegocio reglaNegocio) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (reglaNegocio == null)
			lst.add(this.getMsjUsuarioError("reglanegocio.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (reglaNegocio.getPk() != null) {
			Set<ConstraintViolation<ReglaNegocioPk>> reglasPk = validator.validate(reglaNegocio.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<ReglaNegocio>> reglas = validator.validate(reglaNegocio);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO ReglaNegocio : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,ReglaNegocio reglaNegocio) {
		reglaNegocio = prepararInsertar(usuarioActual, reglaNegocio);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, reglaNegocio);
		if (!lst.isEmpty())
			return lst;
		
		// TODO ReglaNegocio.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, ReglaNegocio reglaNegocio) {
		reglaNegocio = prepararActualizar(usuarioActual, reglaNegocio);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, reglaNegocio);
		if (!lst.isEmpty())
			return lst;
		
		// TODO ReglaNegocio.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,ReglaNegocio reglaNegocio) {
		reglaNegocio = prepararEliminar(usuarioActual, reglaNegocio);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO ReglaNegocio.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,ReglaNegocioPk pk) {
		ReglaNegocio bean = reglaNegocioDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,Integer pidReglaNegocio) {
		return coreEliminar(usuarioActual,new ReglaNegocioPk( pidReglaNegocio));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, ReglaNegocio reglaNegocio) {
		reglaNegocio = prepararAnular(usuarioActual, reglaNegocio);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO ReglaNegocio.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, ReglaNegocioPk pk) {
		ReglaNegocio bean = reglaNegocioDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, Integer pidReglaNegocio) {
		return coreAnular(usuarioActual,new ReglaNegocioPk( pidReglaNegocio));
	}

	public List<DominioMensajeUsuario> core(SeguridadUsuarioActual usuarioActual,String accion,ReglaNegocio reglaNegocio) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			return coreInsertar(usuarioActual, reglaNegocio);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			return coreActualizar(usuarioActual, reglaNegocio);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			return coreAnular(usuarioActual, reglaNegocio);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			return coreEliminar(usuarioActual, reglaNegocio);
		return lst;
	}

}
