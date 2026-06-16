package net.royal.spring.alertas.dominio.dto;

import java.util.ArrayList;
import java.util.List;

import net.royal.spring.framework.core.UValidador;
import net.royal.spring.framework.modelo.correo.EmailDestino.tipo_destino;

public class CorreoCore {
	private String correoTipoConfiguracion;
	private String remitente;
	private String asunto;
	private byte[] cuerpoCorreo;
	private List<CorreoDestinoCore> listaCorreoDestino;
	private List<CorreoAdjuntoCore> listaCorreoAdjunto;
	
	private String correoDestinoPara;
	private String correoDestinoCopia;
	private String correoDestinoCopiaOculta;
	private Boolean flgCorreoPrueba=Boolean.FALSE;
	
	public CorreoCore() {
		listaCorreoDestino = new ArrayList<CorreoDestinoCore>();
		listaCorreoAdjunto = new ArrayList<CorreoAdjuntoCore>();
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public byte[] getCuerpoCorreo() {
		return cuerpoCorreo;
	}

	public void setCuerpoCorreo(byte[] cuerpoCorreo) {
		this.cuerpoCorreo = cuerpoCorreo;
	}

	public List<CorreoDestinoCore> getListaCorreoDestino() {
		return listaCorreoDestino;
	}

	public void setListaCorreoDestino(List<CorreoDestinoCore> listaCorreoDestino) {
		this.listaCorreoDestino = listaCorreoDestino;
	}

	public List<CorreoAdjuntoCore> getListaCorreoAdjunto() {
		if (UValidador.esListaVacia(listaCorreoAdjunto)) {
			listaCorreoAdjunto = new ArrayList<CorreoAdjuntoCore>();
		}
		return listaCorreoAdjunto;
	}

	public void setListaCorreoAdjunto(List<CorreoAdjuntoCore> listaCorreoAdjunto) {
		this.listaCorreoAdjunto = listaCorreoAdjunto;
	}

	public String getRemitente() {
		return remitente;
	}

	public void setRemitente(String remitente) {
		this.remitente = remitente;
	}
	
	public void evaluarCorreos(){
		try {
			correoDestinoPara="";
			correoDestinoCopia="";
			correoDestinoCopiaOculta="";
			
			int contadorInternoTO=0,contadorInternoCC=0,contadorInternoBCC=0;
			if (listaCorreoDestino==null)
				return;
			for (CorreoDestinoCore correoDestino : listaCorreoDestino) {
				if (correoDestino.getDestino() == null)
					correoDestino.setDestino(tipo_destino.TO);

				if (correoDestino.getDestino().equals(tipo_destino.TO)) {
					if (contadorInternoTO==0)
						correoDestinoPara=correoDestino.getCorreoDestino();
					else
						correoDestinoPara=correoDestinoPara+";"+correoDestino.getCorreoDestino();
					contadorInternoTO++;
				}
				if (correoDestino.getDestino().equals(tipo_destino.CC)) {
					if (contadorInternoCC==0)
						correoDestinoCopia = correoDestino.getCorreoDestino();
					else
						correoDestinoCopia = correoDestinoCopia +";"+ correoDestino.getCorreoDestino();
					contadorInternoCC++;
				}
				if (correoDestino.getDestino().equals(tipo_destino.BCC)) {
					if (contadorInternoBCC==0)
						correoDestinoCopiaOculta=correoDestino.getCorreoDestino();
					else
						correoDestinoCopiaOculta=correoDestinoCopiaOculta+";"+correoDestino.getCorreoDestino();
					contadorInternoBCC++;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}		
	}
	public String getCorreoDestinoPara() {		
		return correoDestinoPara;
	}

	public void setCorreoDestinoPara(String correoDestinoPara) {
		this.correoDestinoPara = correoDestinoPara;
	}

	public String getCorreoDestinoCopia() {
		return correoDestinoCopia;
	}

	public void setCorreoDestinoCopia(String correoDestinoCopia) {
		this.correoDestinoCopia = correoDestinoCopia;
	}

	public String getCorreoDestinoCopiaOculta() {
		return correoDestinoCopiaOculta;
	}

	public void setCorreoDestinoCopiaOculta(String correoDestinoCopiaOculta) {
		this.correoDestinoCopiaOculta = correoDestinoCopiaOculta;
	}

	public Boolean getFlgCorreoPrueba() {
		return flgCorreoPrueba;
	}

	public void setFlgCorreoPrueba(Boolean flgCorreoPrueba) {
		this.flgCorreoPrueba = flgCorreoPrueba;
	}

	public String getCorreoTipoConfiguracion() {
		return correoTipoConfiguracion;
	}

	public void setCorreoTipoConfiguracion(String correoTipoConfiguracion) {
		this.correoTipoConfiguracion = correoTipoConfiguracion;
	}
		
}
