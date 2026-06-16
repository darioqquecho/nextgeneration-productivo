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

import net.royal.spring.alertas.dominio.ReglaNegocioPlan;
import net.royal.spring.alertas.dominio.ReglaNegocioPlanPk;
import net.royal.spring.alertas.dominio.dto.ReglaNegocioPlanDto;
import net.royal.spring.alertas.dominio.filtros.FiltroPaginacionReglaNegocioPlan;
import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;

@Repository
public class ReglaNegocioPlanDaoImpl extends GenericoDaoImpl<ReglaNegocioPlan, ReglaNegocioPlanPk> {

	private static final long serialVersionUID = 1L;

	public ReglaNegocioPlanDaoImpl() {
		super("reglanegocioplan");
	}

	//@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public ReglaNegocioPlan obtenerPorId(Integer pidReglaNegocio,Integer pidPlan) {
		return obtenerPorId(new ReglaNegocioPlanPk( pidReglaNegocio, pidPlan));
	}

	public ReglaNegocioPlan coreInsertar(ReglaNegocioPlan bean) {
		// TODO ReglaNegocioPlan.Insertar Datos
		
		this.registrar(bean);
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
								Projections.max("pk.idPlan")));
		return this.incrementarInteger(c);*/
		//return null;
		
		CriteriaBuilder cb = this.getCriteriaBuilder();
		CriteriaQuery cq = cb.createQuery(Integer.class);		
		Root root = cq.from(ReglaNegocioPlan.class);
		cq.select(root).where(  
				cb.equal(root.get("pk").get("idReglaNegocio"), unidad)
			);
		cq.select(cb.max(root.get("pk").get("idPlan")));
		return this.incrementarInteger(cq);
	}

	public ReglaNegocioPlan coreInsertar(SeguridadUsuarioActual usuarioActual, ReglaNegocioPlan bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setCreacionTerminal(usuarioActual.getDireccionIp());
		bean.setCreacionFecha(new Date());
		bean.setCreacionUsuario(usuarioActual.getUsuario());
		this.registrar(bean);
		return bean;
	}

	public ReglaNegocioPlan coreActualizar(SeguridadUsuarioActual usuarioActual, ReglaNegocioPlan bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setModificacionTerminal(usuarioActual.getDireccionIp());
		bean.setModificacionFecha(new Date());
		bean.setModificacionUsuario(usuarioActual.getUsuario());
		this.actualizar(bean);
		return bean;
	}

	public ReglaNegocioPlan coreActualizar(ReglaNegocioPlan bean) {
		this.actualizar(bean);
		return bean;
	}
	

	@Transactional
	public DominioPaginacion listar(FiltroPaginacionReglaNegocioPlan filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		filtro.getPaginacion().setPaginacionRegistrosPorPagina(50);

		parametros.add(new DominioParametroPersistencia("p_regla", BigDecimal.class, filtro.getIdReglaNegocio()));

		Integer registros = this.contar("reglanegocioplan.contar", parametros);
		List<?> datos = this.listarConPaginacion(filtro.getPaginacion(), parametros, "reglanegocioplan.listar",
				ReglaNegocioPlanDto.class);
		filtro.getPaginacion().setPaginacionRegistrosPorPagina(50);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros.intValue());
		filtro.getPaginacion().setPaginacionListaResultado(datos);
		return filtro.getPaginacion();
	}


}
