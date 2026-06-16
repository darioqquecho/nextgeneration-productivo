package net.royal.spring.alertas.dao.impl;

import java.util.Date;
import java.util.List;

import jakarta.annotation.Resource;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import net.royal.spring.alertas.dominio.AlertaPlan;
import net.royal.spring.alertas.dominio.AlertaPlanPk;
import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;

@Repository
public class AlertaPlanDaoImpl extends GenericoDaoImpl<AlertaPlan, AlertaPlanPk> {

	private static final long serialVersionUID = 1L;

	public AlertaPlanDaoImpl() {
		super("alertaplan");
	}

	//@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public AlertaPlan obtenerPorId(Integer pidAlerta, Integer pidPlan) {
		return obtenerPorId(new AlertaPlanPk(pidAlerta, pidPlan));
	}

	public AlertaPlan coreInsertar(AlertaPlan bean) {
		// TODO AlertaPlan.Insertar Datos

		this.registrar(bean);
		return bean;
	}

	public AlertaPlan coreInsertar(SeguridadUsuarioActual usuarioActual, AlertaPlan bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setCreacionTerminal(usuarioActual.getDireccionIp());
		bean.setCreacionFecha(new Date());
		bean.setCreacionUsuario(usuarioActual.getUsuario());
		this.registrar(bean);
		return bean;
	}

	public AlertaPlan coreActualizar(SeguridadUsuarioActual usuarioActual, AlertaPlan bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setModificacionTerminal(usuarioActual.getDireccionIp());
		bean.setModificacionFecha(new Date());
		bean.setModificacionUsuario(usuarioActual.getUsuario());
		this.actualizar(bean);
		return bean;
	}

	public AlertaPlan coreActualizar(AlertaPlan bean) {
		this.actualizar(bean);
		return bean;
	}

	public List<AlertaPlan> listarPorIdAlerta(Integer idAlerta) {
		/*JDK17 OK*/
		/*org.hibernate.Criteria cri = this.getCriteria();
		cri.add(Restrictions.eq("pk.idAlerta", idAlerta));
		cri.add(Restrictions.eq("estado", "A"));
		cri.addOrder(Order.asc("ordenColumna"));
		return this.listarPorCriterios(cri);*/
		
		CriteriaBuilder cb = this.getCriteriaBuilder();
		CriteriaQuery cr = cb.createQuery(AlertaPlan.class);		
		Root root = cr.from(AlertaPlan.class);
		cr.select(root).where(  
				cb.equal(root.get("pk").get("idAlerta"), idAlerta) ,
				cb.equal(root.get("estado"), "A")
				);			
		cr.orderBy(
				cb.asc(root.get("ordenColumna"))
			);
		
		List<AlertaPlan> lst = this.listarPorCriteriaQuery(cr);
		
		return lst;
	}

}
