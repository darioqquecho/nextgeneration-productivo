package net.royal.spring.alertas.dominio.dto;

import java.math.BigDecimal;

public class DtoCorreoAdjunto {

		private BigDecimal idCorreo;
		private BigDecimal idAdjunto;
		private String nombreArchivo;
		private String rutaCompleta;
		private byte[] cuerpoAdjunto;
		private String creacionUsuario;
		private java.util.Date creacionFecha;
		private String creacionTerminal;
		private String modificacionUsuario;
		private java.util.Date modificacionFecha;
		private String modificacionTerminal;
		
		private String cuerpoAdjuntoString;
		
		
		public String getCuerpoAdjuntoString() {
			return cuerpoAdjuntoString;
		}
		public void setCuerpoAdjuntoString(String cuerpoAdjuntoString) {
			this.cuerpoAdjuntoString = cuerpoAdjuntoString;
		}
		public BigDecimal getIdCorreo() {
			return idCorreo;
		}
		public void setIdCorreo(BigDecimal idCorreo) {
			this.idCorreo = idCorreo;
		}
		public BigDecimal getIdAdjunto() {
			return idAdjunto;
		}
		public void setIdAdjunto(BigDecimal idAdjunto) {
			this.idAdjunto = idAdjunto;
		}
		public String getNombreArchivo() {
			return nombreArchivo;
		}
		public void setNombreArchivo(String nombreArchivo) {
			this.nombreArchivo = nombreArchivo;
		}
		public String getRutaCompleta() {
			return rutaCompleta;
		}
		public void setRutaCompleta(String rutaCompleta) {
			this.rutaCompleta = rutaCompleta;
		}
		public byte[] getCuerpoAdjunto() {
			return cuerpoAdjunto;
		}
		public void setCuerpoAdjunto(byte[] cuerpoAdjunto) {
			this.cuerpoAdjunto = cuerpoAdjunto;
		}
		public String getCreacionUsuario() {
			return creacionUsuario;
		}
		public void setCreacionUsuario(String creacionUsuario) {
			this.creacionUsuario = creacionUsuario;
		}
		public java.util.Date getCreacionFecha() {
			return creacionFecha;
		}
		public void setCreacionFecha(java.util.Date creacionFecha) {
			this.creacionFecha = creacionFecha;
		}
		public String getCreacionTerminal() {
			return creacionTerminal;
		}
		public void setCreacionTerminal(String creacionTerminal) {
			this.creacionTerminal = creacionTerminal;
		}
		public String getModificacionUsuario() {
			return modificacionUsuario;
		}
		public void setModificacionUsuario(String modificacionUsuario) {
			this.modificacionUsuario = modificacionUsuario;
		}
		public java.util.Date getModificacionFecha() {
			return modificacionFecha;
		}
		public void setModificacionFecha(java.util.Date modificacionFecha) {
			this.modificacionFecha = modificacionFecha;
		}
		public String getModificacionTerminal() {
			return modificacionTerminal;
		}
		public void setModificacionTerminal(String modificacionTerminal) {
			this.modificacionTerminal = modificacionTerminal;
		}

		
		
}
