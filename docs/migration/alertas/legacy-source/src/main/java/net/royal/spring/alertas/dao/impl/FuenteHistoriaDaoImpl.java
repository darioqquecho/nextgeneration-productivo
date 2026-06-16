package net.royal.spring.alertas.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.annotation.Resource;


import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.alertas.dominio.FuenteHistoria;
import net.royal.spring.alertas.dominio.FuenteHistoriaPk;
import net.royal.spring.alertas.dominio.dto.FuenteHistoriaDto;
import net.royal.spring.alertas.dominio.filtros.FiltroPaginacionFuenteHistoria;
import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UBigDecimal;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;

@Repository
public class FuenteHistoriaDaoImpl extends GenericoDaoImpl<FuenteHistoria, FuenteHistoriaPk> {

	private static final long serialVersionUID = 1L;

	public FuenteHistoriaDaoImpl() {
		super("fuentehistoria");
	}

	//@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public FuenteHistoria obtenerPorId(Integer pidReglaNegocio,Integer pidFuenteAlerta,Integer pnroRegistro) {
		return obtenerPorId(new FuenteHistoriaPk( pidReglaNegocio, pidFuenteAlerta, pnroRegistro));
	}

	public FuenteHistoria coreInsertar(FuenteHistoria bean) {
		// TODO FuenteHistoria.Insertar Datos
		
		this.registrar(bean);
		return bean;
	}

	public FuenteHistoria coreInsertar(SeguridadUsuarioActual usuarioActual, FuenteHistoria bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setCreacionTerminal(usuarioActual.getDireccionIp());
		bean.setCreacionFecha(new Date());
		bean.setCreacionUsuario(usuarioActual.getUsuario());
		this.registrar(bean);
		return bean;
	}

	public FuenteHistoria coreActualizar(SeguridadUsuarioActual usuarioActual, FuenteHistoria bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setModificacionTerminal(usuarioActual.getDireccionIp());
		bean.setModificacionFecha(new Date());
		bean.setModificacionUsuario(usuarioActual.getUsuario());
		this.actualizar(bean);
		return bean;
	}

	public FuenteHistoria coreActualizar(FuenteHistoria bean) {
		this.actualizar(bean);
		return bean;
	}

	@Transactional
	public DominioPaginacion listar(FiltroPaginacionFuenteHistoria filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		filtro.getPaginacion().setPaginacionRegistrosPorPagina(50);

		if(UBigDecimal.esCeroOrNulo(filtro.getId_rn())) {
			filtro.setId_rn(null);
		}
		if(UBigDecimal.esCeroOrNulo(filtro.getClaveentera())) {
			filtro.setClaveentera(null);
		}
		if(UString.esNuloVacio(filtro.getClavecadena())) {
			filtro.setClavecadena(null);
		}
		
		parametros.add(new DominioParametroPersistencia("p_codigo", BigDecimal.class, filtro.getId_rn()));
		parametros.add(new DominioParametroPersistencia("fecha1", String.class, filtro.getFecha1()));
		parametros.add(new DominioParametroPersistencia("fecha2", String.class, filtro.getFecha2()));
		parametros.add(new DominioParametroPersistencia("p_claveentera", BigDecimal.class, filtro.getClaveentera()));
		parametros.add(new DominioParametroPersistencia("p_clavecadena", String.class, filtro.getClavecadena()));

		Integer registros = this.contar("fuentehistoria.contar", parametros);
		List<?> datos = this.listarConPaginacion(filtro.getPaginacion(), parametros,
				"fuentehistoria.listar", FuenteHistoriaDto.class);
		filtro.getPaginacion().setPaginacionRegistrosPorPagina(50);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros.intValue());
		filtro.getPaginacion().setPaginacionListaResultado(datos);
		return filtro.getPaginacion();
	}
	
	@Transactional
	public List<FuenteHistoriaDto> inactivar(SeguridadUsuarioActual usuarioActual, List<FuenteHistoriaDto> dto)
			throws UException {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		
		for (FuenteHistoriaDto x : dto) {
			//if(x.getFlgInactivar() == true) {
				/*
				FuenteHistoria obid =  fuenteHistoriaDao.obtenerPorId(x.getIdReglaNegocio().intValue(),
						x.getIdFuenteAlerta().intValue(),x.getNroRegistro().intValue());
				obid.setEstado("I");
				fuenteHistoriaDao.actualizar(obid);
				*/
				parametros.add(new DominioParametroPersistencia("p_reglanegocio", BigDecimal.class, x.getIdReglaNegocio()));
				parametros.add(new DominioParametroPersistencia("p_fuentealerta", BigDecimal.class, x.getIdFuenteAlerta()));
				parametros.add(new DominioParametroPersistencia("p_nroregistro", BigDecimal.class, x.getNroRegistro()));
				parametros.add(new DominioParametroPersistencia("p_estado", String.class, x.getEstado()));
				
				this.ejecutarPorQuery("fuentehistoria.inactivar", parametros);
			//}
		}
		return dto;
	}
}
