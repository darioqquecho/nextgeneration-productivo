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

import net.royal.spring.alertas.dao.impl.ProveedorBdDaoImpl;
import net.royal.spring.alertas.dominio.ProveedorBd;
import net.royal.spring.alertas.dominio.ProveedorBdPk;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;
@SuppressWarnings("rawtypes")
@Service (value = "BeanValidarProveedorBd")
public class ProveedorBdServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarProveedorBd";

	@Autowired
	private ProveedorBdDaoImpl proveedorBdDao;

	private ProveedorBd prepararBasico(SeguridadUsuarioActual usuarioActual,ProveedorBd proveedorBd, Boolean flgInsertar) {
		if (flgInsertar) {
			proveedorBd.setCreacionTerminal(usuarioActual.getDireccionIp());
			proveedorBd.setCreacionFecha(new Date());
			proveedorBd.setCreacionUsuario(usuarioActual.getUsuario());
		} else {
			proveedorBd.setModificacionTerminal(usuarioActual.getDireccionIp());
			proveedorBd.setModificacionFecha(new Date());
			proveedorBd.setModificacionUsuario(usuarioActual.getUsuario());
		}
		
		// TODO ProveedorBd : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return proveedorBd;
	}

	public ProveedorBd prepararInsertar(SeguridadUsuarioActual usuarioActual,ProveedorBd proveedorBd) {
		if (proveedorBd == null)
			return proveedorBd;
		if (proveedorBd.getAuxFlgPreparado())
			return proveedorBd;
		proveedorBd = prepararBasico(usuarioActual,proveedorBd, true);
		proveedorBd.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO ProveedorBd.Insertar : valores por defecto
		
		return proveedorBd;
	}

	public ProveedorBd prepararActualizar(SeguridadUsuarioActual usuarioActual,ProveedorBd proveedorBd) {
		if (proveedorBd == null)
			return proveedorBd;
		if (proveedorBd.getAuxFlgPreparado())
			return proveedorBd;
		proveedorBd = prepararBasico(usuarioActual,proveedorBd, false);
		proveedorBd.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO ProveedorBd.Actualizar : valores por defecto
		
		return proveedorBd;
	}

	public ProveedorBd prepararAnular(SeguridadUsuarioActual usuarioActual,ProveedorBd proveedorBd) {
		if (proveedorBd == null)
			return proveedorBd;
		if (proveedorBd.getAuxFlgPreparado())
			return proveedorBd;
		proveedorBd = prepararBasico(usuarioActual, proveedorBd, false);
		proveedorBd.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO ProveedorBd.Anular : valores por defecto
		
		return proveedorBd;
	}

	public ProveedorBd prepararEliminar(SeguridadUsuarioActual usuarioActual,ProveedorBd proveedorBd) {
		if (proveedorBd == null)
			return proveedorBd;
		if (proveedorBd.getAuxFlgPreparado())
			return proveedorBd;
		proveedorBd.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO ProveedorBd.Eliminar : valores por defecto
		
		return proveedorBd;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, ProveedorBd proveedorBd) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (proveedorBd == null)
			lst.add(this.getMsjUsuarioError("proveedorbd.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (proveedorBd.getPk() != null) {
			Set<ConstraintViolation<ProveedorBdPk>> reglasPk = validator.validate(proveedorBd.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<ProveedorBd>> reglas = validator.validate(proveedorBd);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO ProveedorBd : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,ProveedorBd proveedorBd) {
		proveedorBd = prepararInsertar(usuarioActual, proveedorBd);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, proveedorBd);
		if (!lst.isEmpty())
			return lst;
		
		// TODO ProveedorBd.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, ProveedorBd proveedorBd) {
		proveedorBd = prepararActualizar(usuarioActual, proveedorBd);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, proveedorBd);
		if (!lst.isEmpty())
			return lst;
		
		// TODO ProveedorBd.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,ProveedorBd proveedorBd) {
		proveedorBd = prepararEliminar(usuarioActual, proveedorBd);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO ProveedorBd.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,ProveedorBdPk pk) {
		ProveedorBd bean = proveedorBdDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String pidProveedorBd) {
		return coreEliminar(usuarioActual,new ProveedorBdPk( pidProveedorBd));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, ProveedorBd proveedorBd) {
		proveedorBd = prepararAnular(usuarioActual, proveedorBd);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO ProveedorBd.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, ProveedorBdPk pk) {
		ProveedorBd bean = proveedorBdDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, String pidProveedorBd) {
		return coreAnular(usuarioActual,new ProveedorBdPk( pidProveedorBd));
	}

	public List<DominioMensajeUsuario> core(SeguridadUsuarioActual usuarioActual,String accion,ProveedorBd proveedorBd) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			return coreInsertar(usuarioActual, proveedorBd);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			return coreActualizar(usuarioActual, proveedorBd);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			return coreAnular(usuarioActual, proveedorBd);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			return coreEliminar(usuarioActual, proveedorBd);
		return lst;
	}

}
