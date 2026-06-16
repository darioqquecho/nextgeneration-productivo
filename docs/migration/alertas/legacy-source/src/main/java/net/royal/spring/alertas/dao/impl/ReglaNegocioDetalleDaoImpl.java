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

import net.royal.spring.alertas.dominio.ReglaNegocioDetalle;
import net.royal.spring.alertas.dominio.ReglaNegocioDetallePk;
import net.royal.spring.alertas.dominio.dto.ReglaNegocioDetalleDto;
import net.royal.spring.alertas.dominio.filtros.FiltroPaginacionReglaNegocioDetalle;
import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;

@Repository
public class ReglaNegocioDetalleDaoImpl extends GenericoDaoImpl<ReglaNegocioDetalle, ReglaNegocioDetallePk> {

	private static final long serialVersionUID = 1L;

	public ReglaNegocioDetalleDaoImpl() {
		super("reglanegociodetalle");
	}

	//@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public ReglaNegocioDetalle obtenerPorId(Integer pidReglaNegocio,Integer pidDetalle) {
		return obtenerPorId(new ReglaNegocioDetallePk( pidReglaNegocio, pidDetalle));
	}

	public ReglaNegocioDetalle coreInsertar(ReglaNegocioDetalle bean) {
		// TODO ReglaNegocioDetalle.Insertar Datos
		
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
								Projections.max("pk.idDetalle")));
		return this.incrementarInteger(c);*/
		
		CriteriaBuilder cb = this.getCriteriaBuilder();
		CriteriaQuery cq = cb.createQuery(Integer.class);		
		Root root = cq.from(ReglaNegocioDetalle.class);
		cq.select(root).where(  
				cb.equal(root.get("pk").get("idReglaNegocio"), unidad)
		);
		cq.select(cb.max(root.get("pk").get("idDetalle")));
		
		return this.incrementarInteger(cq);
	}
	

	public ReglaNegocioDetalle coreInsertar(SeguridadUsuarioActual usuarioActual, ReglaNegocioDetalle bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setCreacionTerminal(usuarioActual.getDireccionIp());
		bean.setCreacionFecha(new Date());
		bean.setCreacionUsuario(usuarioActual.getUsuario());
		this.registrar(bean);
		return bean;
	}

	public ReglaNegocioDetalle coreActualizar(SeguridadUsuarioActual usuarioActual, ReglaNegocioDetalle bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setModificacionTerminal(usuarioActual.getDireccionIp());
		bean.setModificacionFecha(new Date());
		bean.setModificacionUsuario(usuarioActual.getUsuario());
		this.actualizar(bean);
		return bean;
	}

	public ReglaNegocioDetalle coreActualizar(ReglaNegocioDetalle bean) {
		this.actualizar(bean);
		return bean;
	}
	
	public List<ReglaNegocioDetalle> listarPorIdReglaNegocio(Integer idReglaNegocio){
		/*JDK17 OK*/
		/*org.hibernate.Criteria cri = this.getCriteria();
		cri.add(Restrictions.eq("pk.idReglaNegocio", idReglaNegocio));
		cri.add(Restrictions.eq("estado", "A"));
		cri.addOrder(Order.asc("ordenColumna"));
		return this.listarPorCriterios(cri);*/
		//return null;
		
		CriteriaBuilder cb = this.getCriteriaBuilder();
		CriteriaQuery cr = cb.createQuery(ReglaNegocioDetalle.class);		
		Root root = cr.from(ReglaNegocioDetalle.class);
		cr.select(root).where(  
				cb.equal(root.get("pk").get("idReglaNegocio"), idReglaNegocio),
				cb.equal(root.get("estado"), "A")
		);
		cr.orderBy(
				cb.asc(root.get("ordenColumna"))
		);
		List<ReglaNegocioDetalle> lst = this.listarPorCriteriaQuery(cr);
		return lst;
	}
	
	@Transactional
	public DominioPaginacion listar(FiltroPaginacionReglaNegocioDetalle filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		filtro.getPaginacion().setPaginacionRegistrosPorPagina(50);

		parametros.add(new DominioParametroPersistencia("p_regla", BigDecimal.class, filtro.getIdReglaNegocio()));
 
		Integer registros = this.contar("reglanegociodetalle.contar", parametros);
		List<?> datos = this.listarConPaginacion(filtro.getPaginacion(), parametros, "reglanegociodetalle.listar",
				ReglaNegocioDetalleDto.class);
		filtro.getPaginacion().setPaginacionRegistrosPorPagina(50);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros.intValue());
		filtro.getPaginacion().setPaginacionListaResultado(datos);
		return filtro.getPaginacion();
	}

}
