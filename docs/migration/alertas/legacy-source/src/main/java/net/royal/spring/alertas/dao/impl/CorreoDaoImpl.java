package net.royal.spring.alertas.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.annotation.Resource;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import net.royal.spring.alertas.dominio.Correo;
import net.royal.spring.alertas.dominio.CorreoPk;
import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.modelo.correo.EmailConfiguracion;
import net.royal.spring.framework.modelo.correo.EmailConstante;
import net.royal.spring.framework.modelo.correo.EmailTransaccion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;

@Repository
public class CorreoDaoImpl extends GenericoDaoImpl<Correo, CorreoPk> {

	private static final long serialVersionUID = 1L;
	public static final String PERFIL_POR_DEFECTO = "PORDEFECTO";
	
	public CorreoDaoImpl() {
		super("correo");
	}

	//@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public Correo obtenerPorId(Integer pidCorreo) {
		return obtenerPorId(new CorreoPk(pidCorreo));
	}

	public Correo coreInsertar(Correo bean) {
		// TODO Correo.Insertar Datos
		this.registrar(bean);
		return bean;
	}

	public Correo coreInsertar(SeguridadUsuarioActual usuarioActual, Correo bean, String estado) {
		if (UString.estaVacio(estado))
			estado = "PEN";
		bean.setEstado(estado);
		bean.setCreacionTerminal(usuarioActual.getDireccionIp());
		bean.setCreacionFecha(new Date());
		bean.setCreacionUsuario(usuarioActual.getUsuario());
		this.registrar(bean);
		return bean;
	}

	public Correo coreActualizar(SeguridadUsuarioActual usuarioActual, Correo bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setModificacionTerminal(usuarioActual.getDireccionIp());
		bean.setModificacionFecha(new Date());
		bean.setModificacionUsuario(usuarioActual.getUsuario());
		this.actualizar(bean);
		return bean;
	}

	public Correo coreActualizar(Correo bean) {
		this.actualizar(bean);
		return bean;
	}

	public Correo registrar(Integer idReglaNegocio, String asunto, String resumenLogAlerta, Integer idAlerta,
			Integer idCorreo, SeguridadUsuarioActual usuarioActual) {
		Correo correo = new Correo();

		correo.getPk().setIdCorreo(idCorreo);
		correo.setAsunto(asunto);
		correo.setIdAlerta(idAlerta);
		correo.setResumenLogAlerta(resumenLogAlerta);
		correo.setIdReglaNegocio(idReglaNegocio);
		correo.setEstado("PEN");
		correo.setCreacionFecha(new Date());
		correo.setCreacionTerminal(usuarioActual.getUsuario());
		correo.setCreacionUsuario(usuarioActual.getDireccionIp());

		this.registrar(correo);

		return correo;
	}

	public List<DtoTabla> listarCorreoPendienteEnvio(Integer idReglaNegocio, Integer cantidadCorreosEnviar) {
		/*JDK17 OK*/
		/*org.hibernate.Criteria cri = this.getCriteria();
		cri.add(Restrictions.eq("estado", "PEN"));
		cri.add(Restrictions.eq("idReglaNegocio", idReglaNegocio));
		if (cantidadCorreosEnviar == null) {
			cri.setMaxResults(10);
		} else {
			if (cantidadCorreosEnviar == 0)
				cri.setMaxResults(10);
			else
				cri.setMaxResults(cantidadCorreosEnviar);
		}
		return this.listarPorCriterios(cri);*/
		Integer resultadosMaximo = null;
//		CriteriaBuilder cb = this.getCriteriaBuilder();
//		CriteriaQuery cr = cb.createQuery(Correo.class);		
//		Root root = cr.from(Correo.class);
//		cr.select(root).where(  
//					cb.equal(root.get("estado"), "PEN"),
//					cb.equal(root.get("idReglaNegocio"), idReglaNegocio)
//				);
		if (cantidadCorreosEnviar == null) 
		{
			resultadosMaximo = 10;
		}
		else
		{
			if (cantidadCorreosEnviar == 0)
			{
				resultadosMaximo = 10;
			}
			else
			{
				resultadosMaximo = cantidadCorreosEnviar;
			}
		}
//		List<Correo> lst = this.listarPorCriteriaQuery(cr, resultadosMaximo);
		
//		return lst;
		
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_idReglaNegocio", Integer.class, idReglaNegocio));
		parametros.add(new DominioParametroPersistencia("p_cantidad", Integer.class, resultadosMaximo));
		List lista = this.listarPorQuery(DtoTabla.class, "correo.listarCorreoPendienteEnvio", parametros);
		return lista;
	}
	public List<DtoTabla> listarCorreoPendienteEnvioGenerico(Integer cantidadCorreosEnviar) {
		/*JDK17 OK*/
		/*org.hibernate.Criteria cri = this.getCriteria();
		cri.add(Restrictions.eq("estado", "PEN"));
		
		Criterion usernameCriterion = Restrictions.isNull("idReglaNegocio");
		Criterion mailCriterion = Restrictions.eq("idReglaNegocio", 0);
		cri.add(Restrictions.or(usernameCriterion, mailCriterion));
				
		if (cantidadCorreosEnviar == null) {
			cri.setMaxResults(10);
		} else {
			if (cantidadCorreosEnviar == 0)
				cri.setMaxResults(10);
			else
				cri.setMaxResults(cantidadCorreosEnviar);
		}
		return this.listarPorCriterios(cri);*/
		
		
		/*Integer resultadosMaximo = null;
		CriteriaBuilder cb = this.getCriteriaBuilder();
		CriteriaQuery cr = this.getCriteriaQuery(Correo.class);
		Root root = cr.from(Correo.class);
		cr.select(root).where(
					cb.equal(root.get("estado"), "PEN"),
					cb.isNull(root.get("idReglaNegocio")),
					cb.equal(root.get("idReglaNegocio"), 0)
				);*/
		
		Integer resultadosMaximo = null;
//		CriteriaBuilder cb = this.getCriteriaBuilder();
//		CriteriaQuery cr = cb.createQuery(Correo.class);
//		Root root = cr.from(Correo.class);
//		Predicate condicion1 = cb.equal(root.get("estado"), "PEN");		
//	    Predicate condicion2 = cb.isNull(root.get("idReglaNegocio"));
//	    Predicate condicion3 = cb.equal(root.get("idReglaNegocio"), 0);	    
//	    Predicate condicionOr = cb.or(condicion2, condicion3);
//	    Predicate whereClause = cb.and(condicion1, condicionOr);
//		cr.select(root).where(
//				whereClause
//				);
		
		if (cantidadCorreosEnviar == null) 
		{
			resultadosMaximo = 10;
		}
		else
		{
			if (cantidadCorreosEnviar == 0)
			{
				resultadosMaximo = 10;
			}
			else
			{
				resultadosMaximo = cantidadCorreosEnviar;
			}
		}
//		List<Correo> lst = this.listarPorCriteriaQuery(cr, resultadosMaximo);
//		
//		return lst;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_cantidad", Integer.class, resultadosMaximo));
		List lista = this.listarPorQuery(DtoTabla.class, "correo.listarCorreoPendienteEnvioGenerico", parametros);
		return lista;
	}
	
	/*public Integer contar() {
	CriteriaBuilder cb = this.getCriteriaBuilder();
	CriteriaQuery cq = this.getCriteriaQuery(Integer.class);		
	Root root = cq.from(Correo.class);
	cq.select(cb.max(root.get("pk").get("idCorreo")));
	return this.incrementarInteger(cq);
	}*/
	
	public List obtenerPerfilesCorreo() throws Exception {		
		List lista = this.listarPorQuery(DtoTabla.class, "correo.obtenerPerfilesCorreo");
		return lista;
	}
	public List obtenerParametroCorreo() throws Exception {		
		List lista = this.listarPorQuery(DtoTabla.class, "correo.obtenerParametroCorreo");
		return lista;
	}
	public List obtenerParametroCorreoSyPerfilCorreo(String perfilCorreoId) throws Exception {		
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_perfilorreoid", String.class, perfilCorreoId));
		List lista = this.listarPorQuery(DtoTabla.class, "correo.obtenerParametroCorreoSyPerfilCorreo",parametros);
		return lista;
	}
	
	public EmailConfiguracion obtenerConfiguracionBd(EmailTransaccion email,String serverNombre,String perfilCorreoId) throws Exception {

		if (email == null)
			email = new EmailTransaccion();
		
		if (UString.esNuloVacio(serverNombre))
			this.leerPropiedades();

		EmailConfiguracion config = new EmailConfiguracion();
		
		List<DtoTabla> lst = null;
		if (UString.esNuloVacio(perfilCorreoId)) {
			lst = this.obtenerParametroCorreo();
		}			
		else {
			//logger.debug("Buscar Parametros de Correo de Perfil : " + perfilCorreoId);
			lst = this.obtenerParametroCorreoSyPerfilCorreo(perfilCorreoId);
		}
			
		
		if (lst == null)
			lst = new ArrayList();
		//logger.info("obtenerConfiguracionBd:"+lst.size());
		for (DtoTabla dto : lst) {
			//logger.info("dto.getCodigo():"+dto.getCodigo());
			//logger.info("dto.getNombre():"+dto.getNombre());
			
			// no se usa el directorio raiz de archivos como temporal:DARIO
			/*if (dto.getCodigo().trim().equals("DIRFILE"))
				config.setRutaRaizTemporal(dto.getNombre());*/
			if (dto.getCodigo().trim().equals("MAILCUENTA"))
				config.setEmailCuenta(dto.getNombre());
			if (dto.getCodigo().trim().equals("MAILREMITE"))
				config.setEmailRemitente(dto.getNombre());
			if (dto.getCodigo().trim().equals("MAILCLAVE"))
				config.setEmailClave(dto.getNombre());
			if (dto.getCodigo().trim().equals("MAILPUERTO"))
				config.setEmailPuerto(dto.getNombre());
			if (dto.getCodigo().trim().equals("MAILHOST"))
				config.setEmailServidor(dto.getNombre());
			if (dto.getCodigo().trim().equals("MAILFLGSSL"))
				config.setEmailFlgSsl(dto.getNombre());
			if (dto.getCodigo().trim().equals("MAILFLGPRU"))
				config.setFlgCorreoPrueba(dto.getNombre());
			if (dto.getCodigo().trim().equals("MAILPRUEBA"))
				config.setCorreoPrueba(dto.getNombre());
			if (dto.getCodigo().trim().equals("MAILRUTATM"))
				config.setRutaRaizAdjuntos(dto.getNombre());
			if (dto.getCodigo().trim().equals("MAIFLGCRED"))
				config.setEmailFlgCredenciales(dto.getNombre());			
			if (dto.getCodigo().trim().equals("TRACEFLG"))
				config.setTraceFlg(dto.getNombre());			
			if (dto.getCodigo().trim().equals("MAICOPIOCU"))
				config.setEmailCorreoCopiaOculta(dto.getNombre());	
			/****/
			if (dto.getCodigo().trim().equals("MAILFLG2SL"))
				config.setEmailFlg2Ssl(dto.getNombre());
			if (dto.getCodigo().trim().equals("MAILDEBUG"))
				config.setEmailDebug(dto.getNombre());
			if (dto.getCodigo().trim().equals("MAILTRPROT"))
				config.setEmailProtocol(dto.getNombre());
			if (dto.getCodigo().trim().equals("MAILOCALHO"))
				config.setEmailLocalHost(dto.getNombre());
			if (dto.getCodigo().trim().equals("MAILCNTIME"))
				config.setEmailConnectiontimeout(dto.getNombre());
			if (dto.getCodigo().trim().equals("MAILTIMEOU"))
				config.setEmailTimeout(dto.getNombre());			
			if (dto.getCodigo().trim().equals("MAILWRTIME"))
				config.setEmailWritetimeout(dto.getNombre());			
			if (dto.getCodigo().trim().equals("MAILSSLPRO"))
				config.setEmailSslProtocol(dto.getNombre());	
			
			
			config.setTraceServidor(serverNombre); 
		}
		
		if (UString.esNuloVacio(config.getEmailRemitente()))
			config.setEmailRemitente(email.getRemitenteCorreo());
		
		config.setTipoConfiguracion(EmailConstante.CORREO_TIPO_CONFIGURACION_CLASE);
		return config.prepararConfiguracion();
	}
	
	
	public Map perfilesCorreoArmarMap(EmailTransaccion email,String serverNombre) throws Exception {
		Map<String,EmailConfiguracion> myMap = new HashMap<String,EmailConfiguracion>();
		List<DtoTabla> lst = this.obtenerPerfilesCorreo();
		if (lst == null)
			lst = new ArrayList();

		for (DtoTabla dto : lst) {
			EmailConfiguracion cfg = obtenerConfiguracionBd(email,serverNombre,dto.getCodigo());
			myMap.put(dto.getCodigo(), cfg);
		}
		return myMap;
	}
	public EmailConfiguracion perfilesCorreoObtener(Map map,String perfilCorreoId) throws Exception {		
		EmailConfiguracion e = null;
		
		if (UString.esNuloVacio(perfilCorreoId))
			perfilCorreoId=PERFIL_POR_DEFECTO;
		
		e = (EmailConfiguracion)map.get(perfilCorreoId);
		
		logger.error("===> Perfil no encontrad ===> " + perfilCorreoId);
		
		logger.debug(e);
		
		return e;
	}
	
	public List<DtoTabla> listarAlertasPendientesGenerarCorreo() {
		List lista = this.listarPorQuery(DtoTabla.class, "correo.listarAlertasPendientesGenerarCorreo");
		return lista;
	}
}
