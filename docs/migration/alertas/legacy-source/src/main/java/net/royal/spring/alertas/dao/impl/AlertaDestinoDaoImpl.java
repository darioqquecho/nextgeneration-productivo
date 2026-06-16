package net.royal.spring.alertas.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.annotation.Resource;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import net.royal.spring.alertas.dominio.AlertaDestino;
import net.royal.spring.alertas.dominio.AlertaDestinoPk;
import net.royal.spring.alertas.dominio.dto.DtoBdCampo;
import net.royal.spring.alertas.dominio.dto.ReglaNegocioTaskDto;
import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;

@Repository
public class AlertaDestinoDaoImpl extends GenericoDaoImpl<AlertaDestino, AlertaDestinoPk> {

	private static final long serialVersionUID = 1L;

	public AlertaDestinoDaoImpl() {
		super("alertadestino");
	}

	//@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public AlertaDestino obtenerPorId(Integer pidAlerta, Integer pidDestino) {
		return obtenerPorId(new AlertaDestinoPk(pidAlerta, pidDestino));
	}

	public AlertaDestino coreInsertar(AlertaDestino bean) {
		// TODO AlertaDestino.Insertar Datos

		this.registrar(bean);
		return bean;
	}

	public AlertaDestino coreInsertar(SeguridadUsuarioActual usuarioActual, AlertaDestino bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setCreacionTerminal(usuarioActual.getDireccionIp());
		bean.setCreacionFecha(new Date());
		bean.setCreacionUsuario(usuarioActual.getUsuario());
		this.registrar(bean);
		return bean;
	}

	public AlertaDestino coreActualizar(SeguridadUsuarioActual usuarioActual, AlertaDestino bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setModificacionTerminal(usuarioActual.getDireccionIp());
		bean.setModificacionFecha(new Date());
		bean.setModificacionUsuario(usuarioActual.getUsuario());
		this.actualizar(bean);
		return bean;
	}

	public AlertaDestino coreActualizar(AlertaDestino bean) {
		this.actualizar(bean);
		return bean;
	}

	public List<AlertaDestino> listarPorIdAlerta(Integer idAlerta) {
		/*JDK17 OK*/
		/*org.hibernate.Criteria cri = this.getCriteria();
		cri.add(Restrictions.eq("pk.idAlerta", idAlerta));
		cri.add(Restrictions.eq("estado", "A"));
		return this.listarPorCriterios(cri);*/
		
		CriteriaBuilder cb = this.getCriteriaBuilder();
		CriteriaQuery cr = cb.createQuery(AlertaDestino.class);		
		Root root = cr.from(AlertaDestino.class);
		cr.select(root).where(  
				cb.equal(root.get("pk").get("idAlerta"), idAlerta) ,
				cb.equal(root.get("estado"), "A")
				);					
		
		List<AlertaDestino> lst = this.listarPorCriteriaQuery(cr);
		
		return lst;
	}
	
	public List<AlertaDestino> listarPorIdAlertaSentencia(Integer idAlerta) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_idAlerta", Integer.class, idAlerta));
		List datos = this.listarPorQuery(AlertaDestino.class, "alertadestino.listarPorIdAlertaSentencia",parametros);
		return datos;
	}
}
