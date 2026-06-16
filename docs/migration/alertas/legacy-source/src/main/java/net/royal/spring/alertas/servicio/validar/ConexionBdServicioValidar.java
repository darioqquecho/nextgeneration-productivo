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

import net.royal.spring.alertas.dao.impl.ConexionBdDaoImpl;
import net.royal.spring.alertas.dominio.ConexionBd;
import net.royal.spring.alertas.dominio.ConexionBdPk;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;
@SuppressWarnings("rawtypes")
@Service (value = "BeanValidarConexionBd")
public class ConexionBdServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarConexionBd";

	@Autowired
	private ConexionBdDaoImpl conexionBdDao;

	private ConexionBd prepararBasico(SeguridadUsuarioActual usuarioActual,ConexionBd conexionBd, Boolean flgInsertar) {
		if (flgInsertar) {
			conexionBd.setCreacionTerminal(usuarioActual.getDireccionIp());
			conexionBd.setCreacionFecha(new Date());
			conexionBd.setCreacionUsuario(usuarioActual.getUsuario());
		} else {
			conexionBd.setModificacionTerminal(usuarioActual.getDireccionIp());
			conexionBd.setModificacionFecha(new Date());
			conexionBd.setModificacionUsuario(usuarioActual.getUsuario());
		}
		
		// TODO ConexionBd : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return conexionBd;
	}

	public ConexionBd prepararInsertar(SeguridadUsuarioActual usuarioActual,ConexionBd conexionBd) {
		if (conexionBd == null)
			return conexionBd;
		if (conexionBd.getAuxFlgPreparado())
			return conexionBd;
		conexionBd = prepararBasico(usuarioActual,conexionBd, true);
		conexionBd.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO ConexionBd.Insertar : valores por defecto
		
		return conexionBd;
	}

	public ConexionBd prepararActualizar(SeguridadUsuarioActual usuarioActual,ConexionBd conexionBd) {
		if (conexionBd == null)
			return conexionBd;
		if (conexionBd.getAuxFlgPreparado())
			return conexionBd;
		conexionBd = prepararBasico(usuarioActual,conexionBd, false);
		conexionBd.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO ConexionBd.Actualizar : valores por defecto
		
		return conexionBd;
	}

	public ConexionBd prepararAnular(SeguridadUsuarioActual usuarioActual,ConexionBd conexionBd) {
		if (conexionBd == null)
			return conexionBd;
		if (conexionBd.getAuxFlgPreparado())
			return conexionBd;
		conexionBd = prepararBasico(usuarioActual, conexionBd, false);
		conexionBd.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO ConexionBd.Anular : valores por defecto
		
		return conexionBd;
	}

	public ConexionBd prepararEliminar(SeguridadUsuarioActual usuarioActual,ConexionBd conexionBd) {
		if (conexionBd == null)
			return conexionBd;
		if (conexionBd.getAuxFlgPreparado())
			return conexionBd;
		conexionBd.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO ConexionBd.Eliminar : valores por defecto
		
		return conexionBd;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, ConexionBd conexionBd) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (conexionBd == null)
			lst.add(this.getMsjUsuarioError("conexionbd.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (conexionBd.getPk() != null) {
			Set<ConstraintViolation<ConexionBdPk>> reglasPk = validator.validate(conexionBd.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<ConexionBd>> reglas = validator.validate(conexionBd);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO ConexionBd : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,ConexionBd conexionBd) {
		conexionBd = prepararInsertar(usuarioActual, conexionBd);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, conexionBd);
		if (!lst.isEmpty())
			return lst;
		
		// TODO ConexionBd.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, ConexionBd conexionBd) {
		conexionBd = prepararActualizar(usuarioActual, conexionBd);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, conexionBd);
		if (!lst.isEmpty())
			return lst;
		
		// TODO ConexionBd.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,ConexionBd conexionBd) {
		conexionBd = prepararEliminar(usuarioActual, conexionBd);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO ConexionBd.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,ConexionBdPk pk) {
		ConexionBd bean = conexionBdDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,Integer pidConexionBd) {
		return coreEliminar(usuarioActual,new ConexionBdPk( pidConexionBd));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, ConexionBd conexionBd) {
		conexionBd = prepararAnular(usuarioActual, conexionBd);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO ConexionBd.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, ConexionBdPk pk) {
		ConexionBd bean = conexionBdDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, Integer pidConexionBd) {
		return coreAnular(usuarioActual,new ConexionBdPk( pidConexionBd));
	}

	public List<DominioMensajeUsuario> core(SeguridadUsuarioActual usuarioActual,String accion,ConexionBd conexionBd) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			return coreInsertar(usuarioActual, conexionBd);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			return coreActualizar(usuarioActual, conexionBd);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			return coreAnular(usuarioActual, conexionBd);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			return coreEliminar(usuarioActual, conexionBd);
		return lst;
	}

}
