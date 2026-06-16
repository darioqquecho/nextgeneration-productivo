package net.royal.spring.alertas.dao.impl;

import java.util.Date;

import jakarta.annotation.Resource;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import org.hibernate.SessionFactory;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.alertas.dominio.AreaNegocio;
import net.royal.spring.alertas.dominio.AreaNegocioPk;
import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;

@Repository
public class AreaNegocioDaoImpl extends GenericoDaoImpl<AreaNegocio, AreaNegocioPk> {

	private static final long serialVersionUID = 1L;

	public AreaNegocioDaoImpl() {
		super("areanegocio");
	}

	//@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public AreaNegocio obtenerPorId(Integer pidAreaNegocio) {
		return obtenerPorId(new AreaNegocioPk( pidAreaNegocio));
	}

	public AreaNegocio coreInsertar(AreaNegocio bean) {
		// TODO AreaNegocio.Insertar Datos
		
		this.registrar(bean);
		return bean;
	}

	public AreaNegocio coreInsertar(SeguridadUsuarioActual usuarioActual, AreaNegocio bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setCreacionTerminal(usuarioActual.getDireccionIp());
		bean.setCreacionFecha(new Date());
		bean.setCreacionUsuario(usuarioActual.getUsuario());
		this.registrar(bean);
		return bean;
	}

	public AreaNegocio coreActualizar(SeguridadUsuarioActual usuarioActual, AreaNegocio bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setModificacionTerminal(usuarioActual.getDireccionIp());
		bean.setModificacionFecha(new Date());
		bean.setModificacionUsuario(usuarioActual.getUsuario());
		this.actualizar(bean);
		return bean;
	}

	public AreaNegocio coreActualizar(AreaNegocio bean) {
		
		this.actualizar(bean);
		return bean;
	}

	@Transactional
    public Integer generarSecuencia() {
		/*JDK17 OK*/
        /*Criteria c = this.getCriteria()
                .setProjection(Projections.projectionList().add(Projections.max("pk.idAreaNegocio")));
        return this.incrementarInteger(c);*/
		
		CriteriaBuilder cb = this.getCriteriaBuilder();
		CriteriaQuery cq = cb.createQuery(Integer.class);		
		Root root = cq.from(AreaNegocio.class);
		cq.select(cb.max(root.get("pk").get("idAreaNegocio")));
		
		return this.incrementarInteger(cq);
    }
}
