package net.royal.spring.alertas.servicio.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.alertas.dao.impl.AreaNegocioDaoImpl;
import net.royal.spring.alertas.dominio.AreaNegocio;
import net.royal.spring.alertas.dominio.AreaNegocioPk;
import net.royal.spring.alertas.dominio.dto.AreaNegocioDto;
import net.royal.spring.alertas.dominio.filtros.AreaNegocioFiltro;
import net.royal.spring.alertas.servicio.validar.AreaNegocioServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;

@Service (value = "BeanServicioAreaNegocio")
public class AreaNegocioServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioAreaNegocio";

	@Autowired
	private AreaNegocioDaoImpl areaNegocioDao;

	@Autowired
	private AreaNegocioServicioValidar validar;

	@Transactional
	public AreaNegocio coreInsertar(SeguridadUsuarioActual usuarioActual,AreaNegocio areaNegocio) throws UException {
		// valores por defecto - preparando objeto
		areaNegocio = validar.prepararInsertar(usuarioActual, areaNegocio);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, areaNegocio);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return areaNegocioDao.coreInsertar(areaNegocio);
	}
	
	

	@Transactional
	public AreaNegocio coreActualizar(SeguridadUsuarioActual usuarioActual, AreaNegocio areaNegocio) throws UException {
		// valores por defecto - preparando objeto
		areaNegocio = validar.prepararActualizar(usuarioActual, areaNegocio);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, areaNegocio);
		if (!lst.isEmpty())
			throw new UException(lst);
		areaNegocio.setCreacionTerminal("192.192.35");
		
		// transaccion
	    areaNegocio = areaNegocioDao.coreActualizar(areaNegocio);
		return areaNegocio;
	}

	@Transactional
	public AreaNegocio coreAnular(SeguridadUsuarioActual usuarioActual, AreaNegocio areaNegocio) throws UException {
		// valores por defecto - preparando objeto
		areaNegocio = validar.prepararAnular(usuarioActual, areaNegocio);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, areaNegocio);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return areaNegocioDao.coreActualizar(areaNegocio);
	}

	public AreaNegocio coreAnular(SeguridadUsuarioActual usuarioActual, AreaNegocioPk pk) throws UException {
		AreaNegocio bean = areaNegocioDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public AreaNegocio coreAnular(SeguridadUsuarioActual usuarioActual, Integer pidAreaNegocio) throws UException {
		return coreAnular(usuarioActual,new AreaNegocioPk( pidAreaNegocio));
	}

	@Transactional
	public void coreEliminar(SeguridadUsuarioActual usuarioActual, AreaNegocio areaNegocio) throws UException {
		// valores por defecto - preparando objeto
		areaNegocio = validar.prepararEliminar(usuarioActual, areaNegocio);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, areaNegocio);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		areaNegocioDao.eliminar(areaNegocio);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, AreaNegocioPk pk) throws UException {
		AreaNegocio areaNegocio = areaNegocioDao.obtenerPorId(pk);
		coreEliminar(usuarioActual,areaNegocio);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, Integer pidAreaNegocio) throws UException {
		coreEliminar(usuarioActual,new AreaNegocioPk( pidAreaNegocio));
	}

	public DominioPaginacion listarAreasNegocio(AreaNegocioFiltro filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		filtro.getPaginacion().setPaginacionRegistrosPorPagina(50);
		if (UString.esNuloVacio(filtro.getNombre()))
			filtro.setNombre(null);

		parametros.add(new DominioParametroPersistencia("p_codigo", BigDecimal.class, filtro.getCodigo()));
		parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getNombre()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
	 

		Integer registros = areaNegocioDao.contar("areanegocio.listarcontar", parametros);
		List<?> datos = areaNegocioDao.listarConPaginacion(filtro.getPaginacion(), parametros, "areanegocio.listar",
				AreaNegocioDto.class);
		filtro.getPaginacion().setPaginacionRegistrosPorPagina(50);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros.intValue());
		filtro.getPaginacion().setPaginacionListaResultado(datos);
		
		
		return filtro.getPaginacion();
	}

	
	
	
	//Metodos Agregados
	@Transactional
	public AreaNegocio ActualizarAreasNegocio(AreaNegocio areaNegocio) throws UException {
		areaNegocioDao.actualizar(areaNegocio);
		return areaNegocio;
	}
	
	
	@Transactional
	public AreaNegocio ActualizarAreasNegocioEstado(AreaNegocio areaNegocio) throws UException {
		areaNegocioDao.actualizar(areaNegocio);
		return areaNegocio;
	}
	
	
	@Transactional
	public AreaNegocio RegistrarAreasNegocio(AreaNegocio areaNegocio) throws UException {
		areaNegocioDao.registrar(areaNegocio);
		return areaNegocio;
	}
	
	
	
	
	
	
	
	
	
	
	
}
