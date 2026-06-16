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

import net.royal.spring.alertas.dao.impl.FuenteHistoriaDaoImpl;
import net.royal.spring.alertas.dominio.FuenteHistoria;
import net.royal.spring.alertas.dominio.FuenteHistoriaPk;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;
@SuppressWarnings("rawtypes")
@Service (value = "BeanValidarFuenteHistoria")
public class FuenteHistoriaServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarFuenteHistoria";

	@Autowired
	private FuenteHistoriaDaoImpl fuenteHistoriaDao;

	private FuenteHistoria prepararBasico(SeguridadUsuarioActual usuarioActual,FuenteHistoria fuenteHistoria, Boolean flgInsertar) {
		if (flgInsertar) {
			fuenteHistoria.setCreacionTerminal(usuarioActual.getDireccionIp());
			fuenteHistoria.setCreacionFecha(new Date());
			fuenteHistoria.setCreacionUsuario(usuarioActual.getUsuario());
		} else {
			fuenteHistoria.setModificacionTerminal(usuarioActual.getDireccionIp());
			fuenteHistoria.setModificacionFecha(new Date());
			fuenteHistoria.setModificacionUsuario(usuarioActual.getUsuario());
		}
		
		// TODO FuenteHistoria : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return fuenteHistoria;
	}

	public FuenteHistoria prepararInsertar(SeguridadUsuarioActual usuarioActual,FuenteHistoria fuenteHistoria) {
		if (fuenteHistoria == null)
			return fuenteHistoria;
		if (fuenteHistoria.getAuxFlgPreparado())
			return fuenteHistoria;
		fuenteHistoria = prepararBasico(usuarioActual,fuenteHistoria, true);
		fuenteHistoria.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO FuenteHistoria.Insertar : valores por defecto
		
		return fuenteHistoria;
	}

	public FuenteHistoria prepararActualizar(SeguridadUsuarioActual usuarioActual,FuenteHistoria fuenteHistoria) {
		if (fuenteHistoria == null)
			return fuenteHistoria;
		if (fuenteHistoria.getAuxFlgPreparado())
			return fuenteHistoria;
		fuenteHistoria = prepararBasico(usuarioActual,fuenteHistoria, false);
		fuenteHistoria.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO FuenteHistoria.Actualizar : valores por defecto
		
		return fuenteHistoria;
	}

	public FuenteHistoria prepararAnular(SeguridadUsuarioActual usuarioActual,FuenteHistoria fuenteHistoria) {
		if (fuenteHistoria == null)
			return fuenteHistoria;
		if (fuenteHistoria.getAuxFlgPreparado())
			return fuenteHistoria;
		fuenteHistoria = prepararBasico(usuarioActual, fuenteHistoria, false);
		fuenteHistoria.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO FuenteHistoria.Anular : valores por defecto
		
		return fuenteHistoria;
	}

	public FuenteHistoria prepararEliminar(SeguridadUsuarioActual usuarioActual,FuenteHistoria fuenteHistoria) {
		if (fuenteHistoria == null)
			return fuenteHistoria;
		if (fuenteHistoria.getAuxFlgPreparado())
			return fuenteHistoria;
		fuenteHistoria.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO FuenteHistoria.Eliminar : valores por defecto
		
		return fuenteHistoria;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, FuenteHistoria fuenteHistoria) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (fuenteHistoria == null)
			lst.add(this.getMsjUsuarioError("fuentehistoria.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (fuenteHistoria.getPk() != null) {
			Set<ConstraintViolation<FuenteHistoriaPk>> reglasPk = validator.validate(fuenteHistoria.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<FuenteHistoria>> reglas = validator.validate(fuenteHistoria);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO FuenteHistoria : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,FuenteHistoria fuenteHistoria) {
		fuenteHistoria = prepararInsertar(usuarioActual, fuenteHistoria);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, fuenteHistoria);
		if (!lst.isEmpty())
			return lst;
		
		// TODO FuenteHistoria.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, FuenteHistoria fuenteHistoria) {
		fuenteHistoria = prepararActualizar(usuarioActual, fuenteHistoria);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, fuenteHistoria);
		if (!lst.isEmpty())
			return lst;
		
		// TODO FuenteHistoria.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,FuenteHistoria fuenteHistoria) {
		fuenteHistoria = prepararEliminar(usuarioActual, fuenteHistoria);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO FuenteHistoria.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,FuenteHistoriaPk pk) {
		FuenteHistoria bean = fuenteHistoriaDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,Integer pidReglaNegocio,Integer pidFuenteAlerta,Integer pnroRegistro) {
		return coreEliminar(usuarioActual,new FuenteHistoriaPk( pidReglaNegocio, pidFuenteAlerta, pnroRegistro));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, FuenteHistoria fuenteHistoria) {
		fuenteHistoria = prepararAnular(usuarioActual, fuenteHistoria);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO FuenteHistoria.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, FuenteHistoriaPk pk) {
		FuenteHistoria bean = fuenteHistoriaDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, Integer pidReglaNegocio,Integer pidFuenteAlerta,Integer pnroRegistro) {
		return coreAnular(usuarioActual,new FuenteHistoriaPk( pidReglaNegocio, pidFuenteAlerta, pnroRegistro));
	}

	public List<DominioMensajeUsuario> core(SeguridadUsuarioActual usuarioActual,String accion,FuenteHistoria fuenteHistoria) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			return coreInsertar(usuarioActual, fuenteHistoria);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			return coreActualizar(usuarioActual, fuenteHistoria);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			return coreAnular(usuarioActual, fuenteHistoria);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			return coreEliminar(usuarioActual, fuenteHistoria);
		return lst;
	}

}
