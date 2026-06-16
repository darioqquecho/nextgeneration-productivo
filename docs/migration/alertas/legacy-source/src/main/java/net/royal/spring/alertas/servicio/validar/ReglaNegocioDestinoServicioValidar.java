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

import net.royal.spring.alertas.dao.impl.ReglaNegocioDestinoDaoImpl;
import net.royal.spring.alertas.dominio.ReglaNegocioDestino;
import net.royal.spring.alertas.dominio.ReglaNegocioDestinoPk;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;
@SuppressWarnings("rawtypes")
@Service (value = "BeanValidarReglaNegocioDestino")
public class ReglaNegocioDestinoServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarReglaNegocioDestino";

	@Autowired
	private ReglaNegocioDestinoDaoImpl reglaNegocioDestinoDao;

	private ReglaNegocioDestino prepararBasico(SeguridadUsuarioActual usuarioActual,ReglaNegocioDestino reglaNegocioDestino, Boolean flgInsertar) {
		if (flgInsertar) {
			reglaNegocioDestino.setCreacionTerminal(usuarioActual.getDireccionIp());
			reglaNegocioDestino.setCreacionFecha(new Date());
			reglaNegocioDestino.setCreacionUsuario(usuarioActual.getUsuario());
		} else {
			reglaNegocioDestino.setModificacionTerminal(usuarioActual.getDireccionIp());
			reglaNegocioDestino.setModificacionFecha(new Date());
			reglaNegocioDestino.setModificacionUsuario(usuarioActual.getUsuario());
		}
		
		// TODO ReglaNegocioDestino : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return reglaNegocioDestino;
	}

	public ReglaNegocioDestino prepararInsertar(SeguridadUsuarioActual usuarioActual,ReglaNegocioDestino reglaNegocioDestino) {
		if (reglaNegocioDestino == null)
			return reglaNegocioDestino;
		if (reglaNegocioDestino.getAuxFlgPreparado())
			return reglaNegocioDestino;
		reglaNegocioDestino = prepararBasico(usuarioActual,reglaNegocioDestino, true);
		reglaNegocioDestino.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO ReglaNegocioDestino.Insertar : valores por defecto
		
		return reglaNegocioDestino;
	}

	public ReglaNegocioDestino prepararActualizar(SeguridadUsuarioActual usuarioActual,ReglaNegocioDestino reglaNegocioDestino) {
		if (reglaNegocioDestino == null)
			return reglaNegocioDestino;
		if (reglaNegocioDestino.getAuxFlgPreparado())
			return reglaNegocioDestino;
		reglaNegocioDestino = prepararBasico(usuarioActual,reglaNegocioDestino, false);
		reglaNegocioDestino.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO ReglaNegocioDestino.Actualizar : valores por defecto
		
		return reglaNegocioDestino;
	}

	public ReglaNegocioDestino prepararAnular(SeguridadUsuarioActual usuarioActual,ReglaNegocioDestino reglaNegocioDestino) {
		if (reglaNegocioDestino == null)
			return reglaNegocioDestino;
		if (reglaNegocioDestino.getAuxFlgPreparado())
			return reglaNegocioDestino;
		reglaNegocioDestino = prepararBasico(usuarioActual, reglaNegocioDestino, false);
		reglaNegocioDestino.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO ReglaNegocioDestino.Anular : valores por defecto
		
		return reglaNegocioDestino;
	}

	public ReglaNegocioDestino prepararEliminar(SeguridadUsuarioActual usuarioActual,ReglaNegocioDestino reglaNegocioDestino) {
		if (reglaNegocioDestino == null)
			return reglaNegocioDestino;
		if (reglaNegocioDestino.getAuxFlgPreparado())
			return reglaNegocioDestino;
		reglaNegocioDestino.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO ReglaNegocioDestino.Eliminar : valores por defecto
		
		return reglaNegocioDestino;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, ReglaNegocioDestino reglaNegocioDestino) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (reglaNegocioDestino == null)
			lst.add(this.getMsjUsuarioError("reglanegociodestino.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (reglaNegocioDestino.getPk() != null) {
			Set<ConstraintViolation<ReglaNegocioDestinoPk>> reglasPk = validator.validate(reglaNegocioDestino.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<ReglaNegocioDestino>> reglas = validator.validate(reglaNegocioDestino);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO ReglaNegocioDestino : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,ReglaNegocioDestino reglaNegocioDestino) {
		reglaNegocioDestino = prepararInsertar(usuarioActual, reglaNegocioDestino);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, reglaNegocioDestino);
		if (!lst.isEmpty())
			return lst;
		
		// TODO ReglaNegocioDestino.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, ReglaNegocioDestino reglaNegocioDestino) {
		reglaNegocioDestino = prepararActualizar(usuarioActual, reglaNegocioDestino);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, reglaNegocioDestino);
		if (!lst.isEmpty())
			return lst;
		
		// TODO ReglaNegocioDestino.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,ReglaNegocioDestino reglaNegocioDestino) {
		reglaNegocioDestino = prepararEliminar(usuarioActual, reglaNegocioDestino);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO ReglaNegocioDestino.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,ReglaNegocioDestinoPk pk) {
		ReglaNegocioDestino bean = reglaNegocioDestinoDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,Integer pidReglaNegocio,String pcorreoDestino) {
		return coreEliminar(usuarioActual,new ReglaNegocioDestinoPk( pidReglaNegocio, pcorreoDestino));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, ReglaNegocioDestino reglaNegocioDestino) {
		reglaNegocioDestino = prepararAnular(usuarioActual, reglaNegocioDestino);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO ReglaNegocioDestino.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, ReglaNegocioDestinoPk pk) {
		ReglaNegocioDestino bean = reglaNegocioDestinoDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, Integer pidReglaNegocio,String pcorreoDestino) {
		return coreAnular(usuarioActual,new ReglaNegocioDestinoPk( pidReglaNegocio, pcorreoDestino));
	}

	public List<DominioMensajeUsuario> core(SeguridadUsuarioActual usuarioActual,String accion,ReglaNegocioDestino reglaNegocioDestino) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			return coreInsertar(usuarioActual, reglaNegocioDestino);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			return coreActualizar(usuarioActual, reglaNegocioDestino);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			return coreAnular(usuarioActual, reglaNegocioDestino);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			return coreEliminar(usuarioActual, reglaNegocioDestino);
		return lst;
	}

}
