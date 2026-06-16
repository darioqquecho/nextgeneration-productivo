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

import net.royal.spring.alertas.dao.impl.FuenteAlertaDetalleDaoImpl;
import net.royal.spring.alertas.dominio.FuenteAlertaDetalle;
import net.royal.spring.alertas.dominio.FuenteAlertaDetallePk;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;
@SuppressWarnings("rawtypes")
@Service (value = "BeanValidarFuenteAlertaDetalle")
public class FuenteAlertaDetalleServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarFuenteAlertaDetalle";

	@Autowired
	private FuenteAlertaDetalleDaoImpl fuenteAlertaDetalleDao;

	private FuenteAlertaDetalle prepararBasico(SeguridadUsuarioActual usuarioActual,FuenteAlertaDetalle fuenteAlertaDetalle, Boolean flgInsertar) {
		if (flgInsertar) {
			fuenteAlertaDetalle.setCreacionTerminal(usuarioActual.getDireccionIp());
			fuenteAlertaDetalle.setCreacionFecha(new Date());
			fuenteAlertaDetalle.setCreacionUsuario(usuarioActual.getUsuario());
		} else {
			fuenteAlertaDetalle.setModificacionTerminal(usuarioActual.getDireccionIp());
			fuenteAlertaDetalle.setModificacionFecha(new Date());
			fuenteAlertaDetalle.setModificacionUsuario(usuarioActual.getUsuario());
		}
		
		// TODO FuenteAlertaDetalle : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return fuenteAlertaDetalle;
	}

	public FuenteAlertaDetalle prepararInsertar(SeguridadUsuarioActual usuarioActual,FuenteAlertaDetalle fuenteAlertaDetalle) {
		if (fuenteAlertaDetalle == null)
			return fuenteAlertaDetalle;
		if (fuenteAlertaDetalle.getAuxFlgPreparado())
			return fuenteAlertaDetalle;
		fuenteAlertaDetalle = prepararBasico(usuarioActual,fuenteAlertaDetalle, true);
		fuenteAlertaDetalle.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO FuenteAlertaDetalle.Insertar : valores por defecto
		
		return fuenteAlertaDetalle;
	}

	public FuenteAlertaDetalle prepararActualizar(SeguridadUsuarioActual usuarioActual,FuenteAlertaDetalle fuenteAlertaDetalle) {
		if (fuenteAlertaDetalle == null)
			return fuenteAlertaDetalle;
		if (fuenteAlertaDetalle.getAuxFlgPreparado())
			return fuenteAlertaDetalle;
		fuenteAlertaDetalle = prepararBasico(usuarioActual,fuenteAlertaDetalle, false);
		fuenteAlertaDetalle.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO FuenteAlertaDetalle.Actualizar : valores por defecto
		
		return fuenteAlertaDetalle;
	}

	public FuenteAlertaDetalle prepararAnular(SeguridadUsuarioActual usuarioActual,FuenteAlertaDetalle fuenteAlertaDetalle) {
		if (fuenteAlertaDetalle == null)
			return fuenteAlertaDetalle;
		if (fuenteAlertaDetalle.getAuxFlgPreparado())
			return fuenteAlertaDetalle;
		fuenteAlertaDetalle = prepararBasico(usuarioActual, fuenteAlertaDetalle, false);
		fuenteAlertaDetalle.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO FuenteAlertaDetalle.Anular : valores por defecto
		
		return fuenteAlertaDetalle;
	}

	public FuenteAlertaDetalle prepararEliminar(SeguridadUsuarioActual usuarioActual,FuenteAlertaDetalle fuenteAlertaDetalle) {
		if (fuenteAlertaDetalle == null)
			return fuenteAlertaDetalle;
		if (fuenteAlertaDetalle.getAuxFlgPreparado())
			return fuenteAlertaDetalle;
		fuenteAlertaDetalle.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO FuenteAlertaDetalle.Eliminar : valores por defecto
		
		return fuenteAlertaDetalle;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, FuenteAlertaDetalle fuenteAlertaDetalle) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (fuenteAlertaDetalle == null)
			lst.add(this.getMsjUsuarioError("fuentealertadetalle.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (fuenteAlertaDetalle.getPk() != null) {
			Set<ConstraintViolation<FuenteAlertaDetallePk>> reglasPk = validator.validate(fuenteAlertaDetalle.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<FuenteAlertaDetalle>> reglas = validator.validate(fuenteAlertaDetalle);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO FuenteAlertaDetalle : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,FuenteAlertaDetalle fuenteAlertaDetalle) {
		fuenteAlertaDetalle = prepararInsertar(usuarioActual, fuenteAlertaDetalle);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, fuenteAlertaDetalle);
		if (!lst.isEmpty())
			return lst;
		
		// TODO FuenteAlertaDetalle.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, FuenteAlertaDetalle fuenteAlertaDetalle) {
		fuenteAlertaDetalle = prepararActualizar(usuarioActual, fuenteAlertaDetalle);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, fuenteAlertaDetalle);
		if (!lst.isEmpty())
			return lst;
		
		// TODO FuenteAlertaDetalle.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,FuenteAlertaDetalle fuenteAlertaDetalle) {
		fuenteAlertaDetalle = prepararEliminar(usuarioActual, fuenteAlertaDetalle);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO FuenteAlertaDetalle.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,FuenteAlertaDetallePk pk) {
		FuenteAlertaDetalle bean = fuenteAlertaDetalleDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,Integer pidFuenteAlerta,Integer pnroRegistro,String pcampoNombre) {
		return coreEliminar(usuarioActual,new FuenteAlertaDetallePk( pidFuenteAlerta, pnroRegistro, pcampoNombre));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, FuenteAlertaDetalle fuenteAlertaDetalle) {
		fuenteAlertaDetalle = prepararAnular(usuarioActual, fuenteAlertaDetalle);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO FuenteAlertaDetalle.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, FuenteAlertaDetallePk pk) {
		FuenteAlertaDetalle bean = fuenteAlertaDetalleDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, Integer pidFuenteAlerta,Integer pnroRegistro,String pcampoNombre) {
		return coreAnular(usuarioActual,new FuenteAlertaDetallePk( pidFuenteAlerta, pnroRegistro, pcampoNombre));
	}

	public List<DominioMensajeUsuario> core(SeguridadUsuarioActual usuarioActual,String accion,FuenteAlertaDetalle fuenteAlertaDetalle) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			return coreInsertar(usuarioActual, fuenteAlertaDetalle);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			return coreActualizar(usuarioActual, fuenteAlertaDetalle);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			return coreAnular(usuarioActual, fuenteAlertaDetalle);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			return coreEliminar(usuarioActual, fuenteAlertaDetalle);
		return lst;
	}

}
