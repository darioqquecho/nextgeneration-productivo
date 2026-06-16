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
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.alertas.dominio.Alerta;
import net.royal.spring.alertas.dominio.AlertaDestino;
import net.royal.spring.alertas.dominio.Correo;
import net.royal.spring.alertas.dominio.CorreoDestino;
import net.royal.spring.alertas.dominio.CorreoDestinoPk;
import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;

@Repository
public class CorreoDestinoDaoImpl extends GenericoDaoImpl<CorreoDestino, CorreoDestinoPk> {

	private static final long serialVersionUID = 1L;

	public CorreoDestinoDaoImpl() {
		super("correodestino");
	}

	//@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public CorreoDestino obtenerPorId(Integer pidCorreo,String pcorreoDestino) {
		return obtenerPorId(new CorreoDestinoPk( pidCorreo, pcorreoDestino));
	}

	public CorreoDestino coreInsertar(CorreoDestino bean) {
		// TODO CorreoDestino.Insertar Datos
		
		this.registrar(bean);
		return bean;
	}

	public CorreoDestino coreInsertar(SeguridadUsuarioActual usuarioActual, CorreoDestino bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setCreacionTerminal(usuarioActual.getDireccionIp());
		bean.setCreacionFecha(new Date());
		bean.setCreacionUsuario(usuarioActual.getUsuario());
		this.registrar(bean);
		return bean;
	}

	public CorreoDestino coreActualizar(SeguridadUsuarioActual usuarioActual, CorreoDestino bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setModificacionTerminal(usuarioActual.getDireccionIp());
		bean.setModificacionFecha(new Date());
		bean.setModificacionUsuario(usuarioActual.getUsuario());
		this.actualizar(bean);
		return bean;
	}

	public CorreoDestino coreActualizar(CorreoDestino bean) {
		this.actualizar(bean);
		return bean;
	}

	
	
	public CorreoDestino registrar(Correo correo, AlertaDestino alertaDestino) {
		CorreoDestino correoDestino = new CorreoDestino();
		
		correoDestino.getPk().setIdCorreo(correo.getPk().getIdCorreo());
		correoDestino.getPk().setCorreoDestino(alertaDestino.getCorreoDestino());
		
		correoDestino.setIdPersona(alertaDestino.getIdPersona());
		correoDestino.setIdTipoDestino(alertaDestino.getIdTipoDestino());
		correoDestino.setNombrePersona(alertaDestino.getNombrePersona());
		
		correoDestino.setEstado("A");
		correoDestino.setCreacionFecha(correo.getCreacionFecha());
		correoDestino.setCreacionTerminal(correo.getCreacionTerminal());
		correoDestino.setCreacionUsuario(correo.getCreacionUsuario());
		
		
		this.registrar(correoDestino);
		return correoDestino;
	}
	
	public List<CorreoDestino> listarPorIdCorreo(Integer idCorreo) {
		/*JDK17 OK*/
		/*org.hibernate.Criteria cri = this.getCriteria();
		cri.add(Restrictions.eq("pk.idCorreo", idCorreo));
		cri.add(Restrictions.eq("estado", "A"));
		return this.listarPorCriterios(cri);*/
		
		CriteriaBuilder cb = this.getCriteriaBuilder();
		CriteriaQuery cr = cb.createQuery(CorreoDestino.class);		
		Root root = cr.from(CorreoDestino.class);
		cr.select(root).where(  
				cb.equal(root.get("pk").get("idCorreo"), idCorreo),
				cb.equal(root.get("estado"), "A")
		);
		List<CorreoDestino> lst = this.listarPorCriteriaQuery(cr);
		
		return lst;
	}
	
	public List<CorreoDestino> listarPorIdCorreoTodos(Integer idCorreo) {
		/*JDK17 OK*/
		/*org.hibernate.Criteria cri = this.getCriteria();
		cri.add(Restrictions.eq("pk.idCorreo", idCorreo));
		return this.listarPorCriterios(cri);*/		
		
		CriteriaBuilder cb = this.getCriteriaBuilder();
		CriteriaQuery cr = cb.createQuery(CorreoDestino.class);		
		Root root = cr.from(CorreoDestino.class);
		cr.select(root).where(  
				cb.equal(root.get("pk").get("idCorreo"), idCorreo)
		);
		List<CorreoDestino> lst = this.listarPorCriteriaQuery(cr);
		
		return lst;
	}
	
	@Transactional
	public void eliminarPorIdCorreo(Integer idCorreo) {
		/*JDK17 OK*/
		/*org.hibernate.Criteria cri = this.getCriteria();
		cri.add(Restrictions.eq("pk.idCorreo", idCorreo));
		List<CorreoDestino> lst = this.listarPorCriterios(cri);
		for (CorreoDestino entity : lst) {
			this.eliminar(entity);
		}*/
		
		CriteriaBuilder cb = this.getCriteriaBuilder();
		CriteriaQuery cr = cb.createQuery(CorreoDestino.class);		
		Root root = cr.from(CorreoDestino.class);
		cr.select(root).where(  
				cb.equal(root.get("pk").get("idCorreo"), idCorreo)
		);
		List<CorreoDestino> lst = this.listarPorCriteriaQuery(cr);
		
		for (CorreoDestino entity : lst) {
			this.eliminar(entity);
		}
		
	}

	@Transactional
	public void eliminarPorIdCorreoSql(Integer idAlerta) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_idAlerta", Integer.class, idAlerta));
		this.ejecutarPorQuery("correodestino.eliminarPorIdCorreoSql", parametros);
	}
	
}
