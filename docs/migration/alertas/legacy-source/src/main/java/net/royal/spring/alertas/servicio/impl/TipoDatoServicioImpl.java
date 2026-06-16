package net.royal.spring.alertas.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.alertas.dao.impl.TipoDatoDaoImpl;
import net.royal.spring.alertas.dominio.TipoDato;
import net.royal.spring.alertas.dominio.TipoDatoPk;
import net.royal.spring.alertas.servicio.validar.TipoDatoServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;

@Service (value = "BeanServicioTipoDato")
public class TipoDatoServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioTipoDato";

	@Autowired
	private TipoDatoDaoImpl tipoDatoDao;

	@Autowired
	private TipoDatoServicioValidar validar;

	@Transactional
	public TipoDato coreInsertar(SeguridadUsuarioActual usuarioActual,TipoDato tipoDato) throws UException {
		// valores por defecto - preparando objeto
		tipoDato = validar.prepararInsertar(usuarioActual, tipoDato);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, tipoDato);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return tipoDatoDao.coreInsertar(tipoDato);
	}

	@Transactional
	public TipoDato coreActualizar(SeguridadUsuarioActual usuarioActual, TipoDato tipoDato) throws UException {
		// valores por defecto - preparando objeto
		tipoDato = validar.prepararActualizar(usuarioActual, tipoDato);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, tipoDato);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
	    tipoDato = tipoDatoDao.coreActualizar(tipoDato);
		return tipoDato;
	}

	@Transactional
	public TipoDato coreAnular(SeguridadUsuarioActual usuarioActual, TipoDato tipoDato) throws UException {
		// valores por defecto - preparando objeto
		tipoDato = validar.prepararAnular(usuarioActual, tipoDato);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, tipoDato);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return tipoDatoDao.coreActualizar(tipoDato);
	}

	public TipoDato coreAnular(SeguridadUsuarioActual usuarioActual, TipoDatoPk pk) throws UException {
		TipoDato bean = tipoDatoDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public TipoDato coreAnular(SeguridadUsuarioActual usuarioActual, String pidTipoDato) throws UException {
		return coreAnular(usuarioActual,new TipoDatoPk( pidTipoDato));
	}

	@Transactional
	public void coreEliminar(SeguridadUsuarioActual usuarioActual, TipoDato tipoDato) throws UException {
		// valores por defecto - preparando objeto
		tipoDato = validar.prepararEliminar(usuarioActual, tipoDato);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, tipoDato);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		tipoDatoDao.eliminar(tipoDato);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, TipoDatoPk pk) throws UException {
		TipoDato tipoDato = tipoDatoDao.obtenerPorId(pk);
		coreEliminar(usuarioActual,tipoDato);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, String pidTipoDato) throws UException {
		coreEliminar(usuarioActual,new TipoDatoPk( pidTipoDato));
	}

}
