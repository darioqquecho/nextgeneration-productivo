package net.royal.spring.alertas.dominio.dto;

public class CorreoAdjuntoCore {
	
	private String nombreArchivo;
	
	private byte[] archivoAdjunto;

	public CorreoAdjuntoCore() {
	}
	public CorreoAdjuntoCore(byte[] archivoAdjunto) {
		this.archivoAdjunto = archivoAdjunto;
	}
	public CorreoAdjuntoCore(String nombreArchivo, byte[] archivoAdjunto) {
		this.nombreArchivo=nombreArchivo;
		this.archivoAdjunto = archivoAdjunto;
	}

	public byte[] getArchivoAdjunto() {
		return archivoAdjunto;
	}

	public void setArchivoAdjunto(byte[] archivoAdjunto) {
		this.archivoAdjunto = archivoAdjunto;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
}

