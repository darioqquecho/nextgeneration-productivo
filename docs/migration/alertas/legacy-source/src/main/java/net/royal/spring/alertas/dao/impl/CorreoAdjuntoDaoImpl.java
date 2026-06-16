package net.royal.spring.alertas.dao.impl;

import java.util.Date;
import java.util.List;

import jakarta.annotation.Resource;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import net.royal.spring.alertas.dominio.CorreoAdjunto;
import net.royal.spring.alertas.dominio.CorreoAdjuntoPk;
import net.royal.spring.alertas.dominio.CorreoDestino;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;

@Repository
public class CorreoAdjuntoDaoImpl extends GenericoDaoImpl<CorreoAdjunto, CorreoAdjuntoPk> {

	private static final long serialVersionUID = 1L;

	public CorreoAdjuntoDaoImpl() {
		super("correoadjunto");
	}

	//@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public CorreoAdjunto obtenerPorId(Integer pidCorreo,Integer pidAdjunto) {
		return obtenerPorId(new CorreoAdjuntoPk( pidCorreo, pidAdjunto));
	}

	public CorreoAdjunto coreInsertar(CorreoAdjunto bean) {
		// TODO CorreoAdjunto.Insertar Datos
		
		this.registrar(bean);
		return bean;
	}

	public CorreoAdjunto coreInsertar(SeguridadUsuarioActual usuarioActual, CorreoAdjunto bean) {
		bean.setCreacionTerminal(usuarioActual.getDireccionIp());
		bean.setCreacionFecha(new Date());
		bean.setCreacionUsuario(usuarioActual.getUsuario());
		this.registrar(bean);
		return bean;
	}

	public CorreoAdjunto coreActualizar(SeguridadUsuarioActual usuarioActual, CorreoAdjunto bean) {
		bean.setModificacionTerminal(usuarioActual.getDireccionIp());
		bean.setModificacionFecha(new Date());
		bean.setModificacionUsuario(usuarioActual.getUsuario());
		this.actualizar(bean);
		return bean;
	}

	public CorreoAdjunto coreActualizar(CorreoAdjunto bean) {
		this.actualizar(bean);
		return bean;
	}
	public List<CorreoAdjunto> listarPorIdCorreo(Integer idCorreo) {
		/*JDK17 OK*/
		/*org.hibernate.Criteria cri = this.getCriteria();
		cri.add(Restrictions.eq("pk.idCorreo", idCorreo));
		return this.listarPorCriterios(cri);*/
		
		CriteriaBuilder cb = this.getCriteriaBuilder();
		CriteriaQuery cr = cb.createQuery(CorreoAdjunto.class);		
		Root root = cr.from(CorreoAdjunto.class);
		cr.select(root).where(  cb.equal(root.get("pk").get("idCorreo"), idCorreo)  );
		List<CorreoAdjunto> lst = this.listarPorCriteriaQuery(cr);
		
		return lst;
	}
}
