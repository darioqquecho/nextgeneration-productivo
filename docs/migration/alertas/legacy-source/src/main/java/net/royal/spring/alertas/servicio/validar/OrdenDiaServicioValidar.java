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

import net.royal.spring.alertas.dao.impl.OrdenDiaDaoImpl;
import net.royal.spring.alertas.dominio.OrdenDia;
import net.royal.spring.alertas.dominio.OrdenDiaPk;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;
@SuppressWarnings("rawtypes")
@Service (value = "BeanValidarOrdenDia")
public class OrdenDiaServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarOrdenDia";

	@Autowired
	private OrdenDiaDaoImpl ordenDiaDao;

	private OrdenDia prepararBasico(SeguridadUsuarioActual usuarioActual,OrdenDia ordenDia, Boolean flgInsertar) {
		if (flgInsertar) {
			ordenDia.setCreacionTerminal(usuarioActual.getDireccionIp());
			ordenDia.setCreacionFecha(new Date());
			ordenDia.setCreacionUsuario(usuarioActual.getUsuario());
		} else {
			ordenDia.setModificacionTerminal(usuarioActual.getDireccionIp());
			ordenDia.setModificacionFecha(new Date());
			ordenDia.setModificacionUsuario(usuarioActual.getUsuario());
		}
		
		// TODO OrdenDia : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return ordenDia;
	}

	public OrdenDia prepararInsertar(SeguridadUsuarioActual usuarioActual,OrdenDia ordenDia) {
		if (ordenDia == null)
			return ordenDia;
		if (ordenDia.getAuxFlgPreparado())
			return ordenDia;
		ordenDia = prepararBasico(usuarioActual,ordenDia, true);
		ordenDia.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO OrdenDia.Insertar : valores por defecto
		
		return ordenDia;
	}

	public OrdenDia prepararActualizar(SeguridadUsuarioActual usuarioActual,OrdenDia ordenDia) {
		if (ordenDia == null)
			return ordenDia;
		if (ordenDia.getAuxFlgPreparado())
			return ordenDia;
		ordenDia = prepararBasico(usuarioActual,ordenDia, false);
		ordenDia.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO OrdenDia.Actualizar : valores por defecto
		
		return ordenDia;
	}

	public OrdenDia prepararAnular(SeguridadUsuarioActual usuarioActual,OrdenDia ordenDia) {
		if (ordenDia == null)
			return ordenDia;
		if (ordenDia.getAuxFlgPreparado())
			return ordenDia;
		ordenDia = prepararBasico(usuarioActual, ordenDia, false);
		ordenDia.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO OrdenDia.Anular : valores por defecto
		
		return ordenDia;
	}

	public OrdenDia prepararEliminar(SeguridadUsuarioActual usuarioActual,OrdenDia ordenDia) {
		if (ordenDia == null)
			return ordenDia;
		if (ordenDia.getAuxFlgPreparado())
			return ordenDia;
		ordenDia.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO OrdenDia.Eliminar : valores por defecto
		
		return ordenDia;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, OrdenDia ordenDia) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (ordenDia == null)
			lst.add(this.getMsjUsuarioError("ordendia.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (ordenDia.getPk() != null) {
			Set<ConstraintViolation<OrdenDiaPk>> reglasPk = validator.validate(ordenDia.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<OrdenDia>> reglas = validator.validate(ordenDia);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO OrdenDia : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,OrdenDia ordenDia) {
		ordenDia = prepararInsertar(usuarioActual, ordenDia);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, ordenDia);
		if (!lst.isEmpty())
			return lst;
		
		// TODO OrdenDia.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, OrdenDia ordenDia) {
		ordenDia = prepararActualizar(usuarioActual, ordenDia);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, ordenDia);
		if (!lst.isEmpty())
			return lst;
		
		// TODO OrdenDia.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,OrdenDia ordenDia) {
		ordenDia = prepararEliminar(usuarioActual, ordenDia);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO OrdenDia.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,OrdenDiaPk pk) {
		OrdenDia bean = ordenDiaDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String pidOrdenDia) {
		return coreEliminar(usuarioActual,new OrdenDiaPk( pidOrdenDia));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, OrdenDia ordenDia) {
		ordenDia = prepararAnular(usuarioActual, ordenDia);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO OrdenDia.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, OrdenDiaPk pk) {
		OrdenDia bean = ordenDiaDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, String pidOrdenDia) {
		return coreAnular(usuarioActual,new OrdenDiaPk( pidOrdenDia));
	}

	public List<DominioMensajeUsuario> core(SeguridadUsuarioActual usuarioActual,String accion,OrdenDia ordenDia) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			return coreInsertar(usuarioActual, ordenDia);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			return coreActualizar(usuarioActual, ordenDia);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			return coreAnular(usuarioActual, ordenDia);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			return coreEliminar(usuarioActual, ordenDia);
		return lst;
	}

}
