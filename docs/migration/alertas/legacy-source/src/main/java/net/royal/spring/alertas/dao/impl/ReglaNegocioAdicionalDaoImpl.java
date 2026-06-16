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

import net.royal.spring.alertas.dominio.ReglaNegocioAdicional;
import net.royal.spring.alertas.dominio.ReglaNegocioAdicionalPk;
import net.royal.spring.alertas.dominio.dto.ReglaNegocioAdicionalDto;
import net.royal.spring.alertas.dominio.filtros.FiltroPaginacionReglaNegocioAdicional;
import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;

@Repository
public class ReglaNegocioAdicionalDaoImpl extends GenericoDaoImpl<ReglaNegocioAdicional, ReglaNegocioAdicionalPk> {

	private static final long serialVersionUID = 1L;

	public ReglaNegocioAdicionalDaoImpl() {
		super("reglanegocioadicional");
	}

	//@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public ReglaNegocioAdicional obtenerPorId(Integer pidReglaNegocio,Integer pidAdicional) {
		return obtenerPorId(new ReglaNegocioAdicionalPk( pidReglaNegocio, pidAdicional));
	}

	public ReglaNegocioAdicional coreInsertar(ReglaNegocioAdicional bean) {
		// TODO ReglaNegocioAdicional.Insertar Datos
		
		this.registrar(bean);
		return bean;
	}

	public ReglaNegocioAdicional coreInsertar(SeguridadUsuarioActual usuarioActual, ReglaNegocioAdicional bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setCreacionTerminal(usuarioActual.getDireccionIp());
		bean.setCreacionFecha(new Date());
		bean.setCreacionUsuario(usuarioActual.getUsuario());
		this.registrar(bean);
		return bean;
	}

	public ReglaNegocioAdicional coreActualizar(SeguridadUsuarioActual usuarioActual, ReglaNegocioAdicional bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setModificacionTerminal(usuarioActual.getDireccionIp());
		bean.setModificacionFecha(new Date());
		bean.setModificacionUsuario(usuarioActual.getUsuario());
		this.actualizar(bean);
		return bean;
	}

	public ReglaNegocioAdicional coreActualizar(ReglaNegocioAdicional bean) {
		this.actualizar(bean);
		return bean;
	}
	
	@Transactional
	public Integer generarSecuenciaActualizar(Integer id) {
		/*JDK17 OK*/
		/*Criteria c = this.getCriteria().add(Restrictions.eq("pk.idReglaNegocio", id))
				.setProjection(Projections.projectionList().add(Projections.max("pk.idAdicional")));
		return this.incrementarInteger(c);*/		

		CriteriaBuilder cb = this.getCriteriaBuilder();
		CriteriaQuery cq = cb.createQuery(Integer.class);		
		Root root = cq.from(ReglaNegocioAdicional.class);
		cq.select(root).where(  
				cb.equal(root.get("pk").get("idReglaNegocio"), id)
			);
		cq.select(cb.max(root.get("pk").get("idAdicional")));
		
		return this.incrementarInteger(cq);
		
	}
	
	@Transactional
	public Integer generarSecuencia() {
		/*JDK17 OK*/
		/*Criteria c = this.getCriteria()
				.setProjection(Projections.projectionList().add(Projections.max("pk.idAdicional")));
		return this.incrementarInteger(c);*/
		
		CriteriaBuilder cb = this.getCriteriaBuilder();
		CriteriaQuery cq = cb.createQuery(Integer.class);		
		Root root = cq.from(ReglaNegocioAdicional.class);
		cq.select(cb.max(root.get("pk").get("idAdicional")));
		
		return this.incrementarInteger(cq);
	}
	
	@Transactional
	public Integer generarSecuencia(Integer unidad) {
		/*JDK17 OK*/
		/*Criteria c = this
				.getCriteria()
				.add(Restrictions.eq("pk.idReglaNegocio", unidad))
				.setProjection(
						Projections.projectionList().add(
								Projections.max("pk.idAdicional")));
		return this.incrementarInteger(c);*/
		
		CriteriaBuilder cb = this.getCriteriaBuilder();
		CriteriaQuery cq = cb.createQuery(Integer.class);		
		Root root = cq.from(ReglaNegocioAdicional.class);
		cq.select(root).where(  
				cb.equal(root.get("pk").get("idReglaNegocio"), unidad)
			);
		cq.select(cb.max(root.get("pk").get("idAdicional")));
		
		return this.incrementarInteger(cq);
		
	}
	
 

	
	@Transactional
	public DominioPaginacion listar(FiltroPaginacionReglaNegocioAdicional filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		filtro.getPaginacion().setPaginacionRegistrosPorPagina(50);

		parametros.add(new DominioParametroPersistencia("p_regla", BigDecimal.class, filtro.getIdReglaNegocio()));
 
		Integer registros = this.contar("reglanegocioadicional.contar", parametros);
		List<?> datos = this.listarConPaginacion(filtro.getPaginacion(), parametros, "reglanegocioadicional.listar",
				ReglaNegocioAdicionalDto.class);
		filtro.getPaginacion().setPaginacionRegistrosPorPagina(50);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros.intValue());
		filtro.getPaginacion().setPaginacionListaResultado(datos);
		return filtro.getPaginacion();
	}

}
