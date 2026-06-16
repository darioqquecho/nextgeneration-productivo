package net.royal.spring.alertas.dao.impl;

import java.util.Date;
import java.util.List;

import jakarta.annotation.Resource;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import net.royal.spring.alertas.dominio.AlertaAdicional;
import net.royal.spring.alertas.dominio.AlertaAdicionalPk;
import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;

@Repository
public class AlertaAdicionalDaoImpl extends GenericoDaoImpl<AlertaAdicional, AlertaAdicionalPk> {

	private static final long serialVersionUID = 1L;

	public AlertaAdicionalDaoImpl() {
		super("alertaadicional");
	}

	//@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public AlertaAdicional obtenerPorId(Integer pidAlerta, Integer pidAdicional) {
		return obtenerPorId(new AlertaAdicionalPk(pidAlerta, pidAdicional));
	}

	public AlertaAdicional coreInsertar(AlertaAdicional bean) {
		// TODO AlertaAdicional.Insertar Datos

		this.registrar(bean);
		return bean;
	}

	public AlertaAdicional coreInsertar(SeguridadUsuarioActual usuarioActual, AlertaAdicional bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setCreacionTerminal(usuarioActual.getDireccionIp());
		bean.setCreacionFecha(new Date());
		bean.setCreacionUsuario(usuarioActual.getUsuario());
		this.registrar(bean);
		return bean;
	}

	public AlertaAdicional coreActualizar(SeguridadUsuarioActual usuarioActual, AlertaAdicional bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setModificacionTerminal(usuarioActual.getDireccionIp());
		bean.setModificacionFecha(new Date());
		bean.setModificacionUsuario(usuarioActual.getUsuario());
		this.actualizar(bean);
		return bean;
	}

	public AlertaAdicional coreActualizar(AlertaAdicional bean) {
		this.actualizar(bean);
		return bean;
	}

	public List<AlertaAdicional> listarPorIdAlerta(Integer idAlerta) {
		/*JDK17 OK*/
		/*Criteria cri = this.getCriteria();
		
		cri.addOrder(Order.asc("idLogAlerta"));
		cri.addOrder(Order.asc("ordenColumna"));
		return this.listarPorCriterios(cri);*/
		
		CriteriaBuilder cb = this.getCriteriaBuilder();
		CriteriaQuery cr = cb.createQuery(AlertaAdicional.class);		
		Root root = cr.from(AlertaAdicional.class);
		cr.select(root).where(  
				cb.equal(root.get("pk").get("idAlerta"), idAlerta) ,
				cb.equal(root.get("estado"), "A")
				);			
		cr.orderBy(
				cb.asc(root.get("idLogAlerta")),
				cb.asc(root.get("ordenColumna"))
			);
		
		List<AlertaAdicional> lst = this.listarPorCriteriaQuery(cr);
		
		return lst;
	}

}
