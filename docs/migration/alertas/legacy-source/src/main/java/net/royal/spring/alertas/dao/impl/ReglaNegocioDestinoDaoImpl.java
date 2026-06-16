package net.royal.spring.alertas.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.annotation.Resource;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.alertas.dominio.ReglaNegocioDestino;
import net.royal.spring.alertas.dominio.ReglaNegocioDestinoPk;
import net.royal.spring.alertas.dominio.dto.ReglaNegocioDestinoDto;
import net.royal.spring.alertas.dominio.dto.SelectorGenericoDto;
import net.royal.spring.alertas.dominio.filtros.FiltroGenericoSelector;
import net.royal.spring.alertas.dominio.filtros.FiltroPaginacionReglaNegocioDestino;
import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;

@Repository
public class ReglaNegocioDestinoDaoImpl extends GenericoDaoImpl<ReglaNegocioDestino, ReglaNegocioDestinoPk> {

	private static final long serialVersionUID = 1L;

	public ReglaNegocioDestinoDaoImpl() {
		super("reglanegociodestino");
	}

	//@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public ReglaNegocioDestino obtenerPorId(Integer pidReglaNegocio,String pcorreoDestino) {
		return obtenerPorId(new ReglaNegocioDestinoPk( pidReglaNegocio, pcorreoDestino));
	}

	public ReglaNegocioDestino coreInsertar(ReglaNegocioDestino bean) {
		// TODO ReglaNegocioDestino.Insertar Datos
		
		this.registrar(bean);
		return bean;
	}
	
	

	public ReglaNegocioDestino coreInsertar(SeguridadUsuarioActual usuarioActual, ReglaNegocioDestino bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setCreacionTerminal(usuarioActual.getDireccionIp());
		bean.setCreacionFecha(new Date());
		bean.setCreacionUsuario(usuarioActual.getUsuario());
		this.registrar(bean);
		return bean;
	}

	public ReglaNegocioDestino coreActualizar(SeguridadUsuarioActual usuarioActual, ReglaNegocioDestino bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setModificacionTerminal(usuarioActual.getDireccionIp());
		bean.setModificacionFecha(new Date());
		bean.setModificacionUsuario(usuarioActual.getUsuario());
		this.actualizar(bean);
		return bean;
	}

	public ReglaNegocioDestino coreActualizar(ReglaNegocioDestino bean) {
		this.actualizar(bean);
		return bean;
	}
	
	@Transactional
	public Integer generarSecuencia(Integer unidad) {
		/*JDK17 OK*/
		/*Criteria c = this
				.getCriteria()
				.add(Restrictions.eq("pk.idReglaNegocio", unidad))
				.setProjection(
						Projections.projectionList().add(
								Projections.max("pk.idDetalle")));
		return this.incrementarInteger(c);*/		
		
		
		CriteriaBuilder cb = this.getCriteriaBuilder();
		CriteriaQuery cq = cb.createQuery(Integer.class);		
		Root root = cq.from(ReglaNegocioDestino.class);
		cq.select(root).where(  
				cb.equal(root.get("pk").get("idReglaNegocio"), unidad)
		);
		cq.select(cb.max(root.get("pk").get("idDetalle")));
		
		return this.incrementarInteger(cq);
		
	}

	@Transactional
	public DominioPaginacion listar(FiltroPaginacionReglaNegocioDestino filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		filtro.getPaginacion().setPaginacionRegistrosPorPagina(50);

		parametros.add(new DominioParametroPersistencia("p_regla", BigDecimal.class, filtro.getIdReglaNegocio()));

		Integer registros = this.contar("reglanegociodestino.contar", parametros);
		List<?> datos = this.listarConPaginacion(filtro.getPaginacion(), parametros, "reglanegociodestino.listar",
				ReglaNegocioDestinoDto.class);
		filtro.getPaginacion().setPaginacionRegistrosPorPagina(50);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros.intValue());
		filtro.getPaginacion().setPaginacionListaResultado(datos);
		return filtro.getPaginacion();
	}
	
	@Transactional
	public DominioPaginacion listarSelectorEmp(FiltroGenericoSelector filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		filtro.getPaginacion().setPaginacionRegistrosPorPagina(50);
		
		parametros.add(new DominioParametroPersistencia("p_documento", String.class, filtro.getDocumento()));
		parametros.add(new DominioParametroPersistencia("p_busqueda", String.class, filtro.getBusqueda()));
		

		Integer registros = this.contar("reglanegociodestino.selectorempcontar", parametros);
		List<?> datos = this.listarConPaginacion(filtro.getPaginacion(), parametros, "reglanegociodestino.selectoremplistar",
				SelectorGenericoDto.class);
		filtro.getPaginacion().setPaginacionRegistrosPorPagina(50);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros.intValue());
		filtro.getPaginacion().setPaginacionListaResultado(datos);
		return filtro.getPaginacion();
	}
	
	@Transactional
	public Integer validarcorreoyausado(ReglaNegocioDestinoDto filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		parametros.add(new DominioParametroPersistencia("p_regla", BigDecimal.class, filtro.getIdReglaNegocio()));
		parametros.add(new DominioParametroPersistencia("p_correo", String.class, filtro.getCorreoDestino()));
	
		Integer count = this.contar("reglanegociodestino.validarcorreoyausado", parametros);
		
		return count;
	}
	
	
}
