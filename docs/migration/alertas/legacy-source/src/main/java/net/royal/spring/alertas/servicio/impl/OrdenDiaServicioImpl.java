package net.royal.spring.alertas.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.alertas.dao.impl.OrdenDiaDaoImpl;
import net.royal.spring.alertas.dominio.OrdenDia;
import net.royal.spring.alertas.dominio.OrdenDiaPk;
import net.royal.spring.alertas.servicio.validar.OrdenDiaServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;

@Service (value = "BeanServicioOrdenDia")
public class OrdenDiaServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioOrdenDia";

	@Autowired
	private OrdenDiaDaoImpl ordenDiaDao;

	@Autowired
	private OrdenDiaServicioValidar validar;

	@Transactional
	public OrdenDia coreInsertar(SeguridadUsuarioActual usuarioActual,OrdenDia ordenDia) throws UException {
		// valores por defecto - preparando objeto
		ordenDia = validar.prepararInsertar(usuarioActual, ordenDia);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, ordenDia);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return ordenDiaDao.coreInsertar(ordenDia);
	}

	@Transactional
	public OrdenDia coreActualizar(SeguridadUsuarioActual usuarioActual, OrdenDia ordenDia) throws UException {
		// valores por defecto - preparando objeto
		ordenDia = validar.prepararActualizar(usuarioActual, ordenDia);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, ordenDia);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
	    ordenDia = ordenDiaDao.coreActualizar(ordenDia);
		return ordenDia;
	}

	@Transactional
	public OrdenDia coreAnular(SeguridadUsuarioActual usuarioActual, OrdenDia ordenDia) throws UException {
		// valores por defecto - preparando objeto
		ordenDia = validar.prepararAnular(usuarioActual, ordenDia);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, ordenDia);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return ordenDiaDao.coreActualizar(ordenDia);
	}

	public OrdenDia coreAnular(SeguridadUsuarioActual usuarioActual, OrdenDiaPk pk) throws UException {
		OrdenDia bean = ordenDiaDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public OrdenDia coreAnular(SeguridadUsuarioActual usuarioActual, String pidOrdenDia) throws UException {
		return coreAnular(usuarioActual,new OrdenDiaPk( pidOrdenDia));
	}

	@Transactional
	public void coreEliminar(SeguridadUsuarioActual usuarioActual, OrdenDia ordenDia) throws UException {
		// valores por defecto - preparando objeto
		ordenDia = validar.prepararEliminar(usuarioActual, ordenDia);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, ordenDia);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		ordenDiaDao.eliminar(ordenDia);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, OrdenDiaPk pk) throws UException {
		OrdenDia ordenDia = ordenDiaDao.obtenerPorId(pk);
		coreEliminar(usuarioActual,ordenDia);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, String pidOrdenDia) throws UException {
		coreEliminar(usuarioActual,new OrdenDiaPk( pidOrdenDia));
	}

}
