package net.royal.spring.alertas.dominio.dto;

public class CuerpoCorreodto implements
java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private byte[] cuerpoCorreo;
	private String estado;

	// Auxiliar para mostrar el cuerpo byte[] en cadena
	private String cuerpoCadena;

	public byte[] getCuerpoCorreo() {
		return cuerpoCorreo;
	}

	public void setCuerpoCorreo(byte[] cuerpoCorreo) {
		this.cuerpoCorreo = cuerpoCorreo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

 

	public String getCuerpoCadena() {
		return cuerpoCadena;
	}

	public void setCuerpoCadena(String cuerpoCadena) {
		this.cuerpoCadena = cuerpoCadena;
	}
	
	
	
	
}
