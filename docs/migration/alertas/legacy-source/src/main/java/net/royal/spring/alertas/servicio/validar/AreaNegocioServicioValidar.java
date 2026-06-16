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

import net.royal.spring.alertas.dao.impl.AreaNegocioDaoImpl;
import net.royal.spring.alertas.dominio.AreaNegocio;
import net.royal.spring.alertas.dominio.AreaNegocioPk;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;
@SuppressWarnings("rawtypes")
@Service (value = "BeanValidarAreaNegocio")
public class AreaNegocioServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarAreaNegocio";

	@Autowired
	private AreaNegocioDaoImpl areaNegocioDao;

	private AreaNegocio prepararBasico(SeguridadUsuarioActual usuarioActual,AreaNegocio areaNegocio, Boolean flgInsertar) {
		if (flgInsertar) {
			areaNegocio.setCreacionTerminal(usuarioActual.getDireccionIp());
			areaNegocio.setCreacionFecha(new Date());
			areaNegocio.setCreacionUsuario(usuarioActual.getUsuario());
		} else {
			areaNegocio.setModificacionTerminal(usuarioActual.getDireccionIp());
			areaNegocio.setModificacionFecha(new Date());
			areaNegocio.setModificacionUsuario(usuarioActual.getUsuario());
		}
		
		// TODO AreaNegocio : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return areaNegocio;
	}

	public AreaNegocio prepararInsertar(SeguridadUsuarioActual usuarioActual,AreaNegocio areaNegocio) {
		if (areaNegocio == null)
			return areaNegocio;
		if (areaNegocio.getAuxFlgPreparado())
			return areaNegocio;
		areaNegocio = prepararBasico(usuarioActual,areaNegocio, true);
		areaNegocio.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO AreaNegocio.Insertar : valores por defecto
		
		return areaNegocio;
	}

	public AreaNegocio prepararActualizar(SeguridadUsuarioActual usuarioActual,AreaNegocio areaNegocio) {
		if (areaNegocio == null)
			return areaNegocio;
		if (areaNegocio.getAuxFlgPreparado())
			return areaNegocio;
		areaNegocio = prepararBasico(usuarioActual,areaNegocio, false);
		areaNegocio.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO AreaNegocio.Actualizar : valores por defecto
		
		return areaNegocio;
	}

	public AreaNegocio prepararAnular(SeguridadUsuarioActual usuarioActual,AreaNegocio areaNegocio) {
		if (areaNegocio == null)
			return areaNegocio;
		if (areaNegocio.getAuxFlgPreparado())
			return areaNegocio;
		areaNegocio = prepararBasico(usuarioActual, areaNegocio, false);
		areaNegocio.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO AreaNegocio.Anular : valores por defecto
		
		return areaNegocio;
	}

	public AreaNegocio prepararEliminar(SeguridadUsuarioActual usuarioActual,AreaNegocio areaNegocio) {
		if (areaNegocio == null)
			return areaNegocio;
		if (areaNegocio.getAuxFlgPreparado())
			return areaNegocio;
		areaNegocio.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO AreaNegocio.Eliminar : valores por defecto
		
		return areaNegocio;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, AreaNegocio areaNegocio) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (areaNegocio == null)
			lst.add(this.getMsjUsuarioError("areanegocio.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (areaNegocio.getPk() != null) {
			Set<ConstraintViolation<AreaNegocioPk>> reglasPk = validator.validate(areaNegocio.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<AreaNegocio>> reglas = validator.validate(areaNegocio);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO AreaNegocio : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,AreaNegocio areaNegocio) {
		areaNegocio = prepararInsertar(usuarioActual, areaNegocio);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, areaNegocio);
		if (!lst.isEmpty())
			return lst;
		
		// TODO AreaNegocio.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, AreaNegocio areaNegocio) {
		areaNegocio = prepararActualizar(usuarioActual, areaNegocio);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, areaNegocio);
		if (!lst.isEmpty())
			return lst;
		
		// TODO AreaNegocio.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,AreaNegocio areaNegocio) {
		areaNegocio = prepararEliminar(usuarioActual, areaNegocio);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO AreaNegocio.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,AreaNegocioPk pk) {
		AreaNegocio bean = areaNegocioDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,Integer pidAreaNegocio) {
		return coreEliminar(usuarioActual,new AreaNegocioPk( pidAreaNegocio));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, AreaNegocio areaNegocio) {
		areaNegocio = prepararAnular(usuarioActual, areaNegocio);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO AreaNegocio.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, AreaNegocioPk pk) {
		AreaNegocio bean = areaNegocioDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, Integer pidAreaNegocio) {
		return coreAnular(usuarioActual,new AreaNegocioPk( pidAreaNegocio));
	}

	public List<DominioMensajeUsuario> core(SeguridadUsuarioActual usuarioActual,String accion,AreaNegocio areaNegocio) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			return coreInsertar(usuarioActual, areaNegocio);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			return coreActualizar(usuarioActual, areaNegocio);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			return coreAnular(usuarioActual, areaNegocio);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			return coreEliminar(usuarioActual, areaNegocio);
		return lst;
	}

}
