package net.royal.spring.alertas.dominio.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.util.UString;

public class DtoBdObject extends DominioTransaccion{
	public static String FILTRO_TODOS="TODOS";
	public static String FILTRO_IGUAL="IGUAL";
	public static String FILTRO_DESIGUAL="DESIGUAL";
	public static String FILTRO_NO_ENCONTRADO="NO ENCONTRADO";
	public static String FILTRO_NOT_EQUALS="NOT EQUALS";
	
	public static String COMPARAR_IGUAL="IGUAL";
	public static String COMPARAR_NO_ENCONTRADO="NO ENCONTRADO";
	public static String COMPARAR_ENCONTRADO="ENCONTRADO";
	public static String COMPARAR_DESIGUAL="DESIGUAL";
	
	private String esquema="";
	private String objeto;
	private int cantidadColumnas;
	private int cantidadColumnasIguales;
	private int cantidadColumnasDestino;	
	private String comparacion=COMPARAR_IGUAL;
	private List<DtoBdCampo> columnas=new ArrayList<DtoBdCampo>();
	
	public DtoBdObject() {}
	public DtoBdObject(String esquema,String objeto) {
		this.esquema=esquema;
		this.objeto=objeto;
	}
	
	public String getEsquema() {
		return esquema;
	}
	public void setEsquema(String esquema) {
		this.esquema = esquema;
	}
	public String getObjeto() {
		return objeto;
	}
	public void setObjeto(String objeto) {
		this.objeto = objeto;
	}
	public String getComparacion() {
		return comparacion;
	}
	public void setComparacion(String comparacion) {
		this.comparacion = comparacion;
	}
	
	public List<DtoBdCampo> getColumnas() {
		return columnas;
	}
	public void setColumnas(List<DtoBdCampo> columnas) {
		this.columnas = columnas;
	}	
	public int getCantidadColumnas() {
		return cantidadColumnas;
	}
	public void setCantidadColumnas(int cantidadColumnas) {
		this.cantidadColumnas = cantidadColumnas;
	}
	public int getCantidadColumnasIguales() {
		return cantidadColumnasIguales;
	}
	public void setCantidadColumnasIguales(int cantidadColumnasIguales) {
		this.cantidadColumnasIguales = cantidadColumnasIguales;
	}	
	public int getCantidadColumnasDestino() {
		return cantidadColumnasDestino;
	}
	public void setCantidadColumnasDestino(int cantidadColumnasDestino) {
		this.cantidadColumnasDestino = cantidadColumnasDestino;
	}
	public DtoBdObject buscarEnLista(List<DtoBdObject> lst) {
		DtoBdObject search=null;
		if (lst==null)
			lst=new ArrayList<DtoBdObject>();		
		search=lst.stream()
				.filter(p -> p.getEsquema().equals(esquema))
				.filter(p -> p.getObjeto().equals(objeto))
				.findFirst().orElse(null);		
		return search;
	}
	public static void print(List<DtoBdObject> lstResultado) {
		if (lstResultado==null)
			lstResultado=new ArrayList<DtoBdObject>();
		for (DtoBdObject dtoObj : lstResultado) {
			System.out.println(dtoObj.getObjeto()+":"+dtoObj.getComparacion());
			System.out.println("    CantidadColumnas:" + dtoObj.getCantidadColumnas());
			System.out.println("    Cant Colum igual:"+dtoObj.getCantidadColumnasIguales());
			System.out.println("    Cant Colum desti:"+dtoObj.getCantidadColumnasDestino());
			for (DtoBdCampo col : dtoObj.getColumnas()) {
				System.out.println("    	columna:"+col.getColumna() +"="+col.getComparacion());	
			}
		}
	}
	public static List<DtoBdObject> comparar(List<DtoBdObject> origenObjetos,List<DtoBdCampo> origenColumnas,
			List<DtoBdObject> destinoObjetos,List<DtoBdCampo> destinoColumnas) {
		return comparar(origenObjetos,origenColumnas,destinoObjetos,destinoColumnas,null);
	}
	public static List<DtoBdObject> comparar(List<DtoBdObject> origenObjetos,List<DtoBdCampo> origenColumnas,
			List<DtoBdObject> destinoObjetos,List<DtoBdCampo> destinoColumnas,String filtro) {
		if (UString.estaVacio(filtro))
			filtro=FILTRO_NOT_EQUALS;
		List<DtoBdObject> lstResultado=new ArrayList<>();
		for (DtoBdObject dtoObj : origenObjetos) {
			DtoBdObject obj = dtoObj.buscarEnLista(destinoObjetos);
			if (obj==null) {
				dtoObj.setComparacion(DtoBdObject.COMPARAR_NO_ENCONTRADO);
				lstResultado.add(dtoObj);
			}else {
				dtoObj.setComparacion(DtoBdObject.COMPARAR_NO_ENCONTRADO);				
				List<DtoBdCampo> colOrigen = origenColumnas.stream()
						.filter(p -> p.getEsquema().equals(dtoObj.getEsquema()))
						.filter(p -> p.getObjeto().equals(dtoObj.getObjeto()))
						.collect(Collectors.toList());				
				dtoObj.setCantidadColumnas(colOrigen.size());
				int cantColIguales=0;
				for (DtoBdCampo c1 : colOrigen) {
					DtoBdCampo search = destinoColumnas.stream()
							.filter(p -> p.getEsquema().equals(c1.getEsquema()))
							.filter(p -> p.getObjeto().equals(c1.getObjeto()))
							.filter(p -> p.getColumna().equals(c1.getColumna()))
							.findFirst().orElse(null);
					if (search==null) {
						c1.setComparacion(DtoBdObject.COMPARAR_NO_ENCONTRADO);
					}						
					else {
						
						if (c1.getTipoDato().equals(search.getTipoDato()) &&
								c1.getLongitud().equals(search.getLongitud()) ) {
							c1.setComparacion(DtoBdObject.COMPARAR_IGUAL);
							cantColIguales++;	
						}else {
							c1.setComparacion(DtoBdObject.COMPARAR_DESIGUAL);		
						}
					}						
				}
				/********/
				long ccc = destinoColumnas.stream()
						.filter(p -> p.getEsquema().equals(dtoObj.getEsquema()))
						.filter(p -> p.getObjeto().equals(dtoObj.getObjeto()))
						.count();
				int cantidadColumnasDestino = Math.toIntExact(ccc);
				/*******/
				dtoObj.setCantidadColumnasIguales(cantColIguales);
				dtoObj.setCantidadColumnasDestino(cantidadColumnasDestino);
				dtoObj.setColumnas(colOrigen);
				if (dtoObj.getCantidadColumnas()!=dtoObj.getCantidadColumnasIguales()) {
					dtoObj.setComparacion(DtoBdObject.COMPARAR_DESIGUAL);
				}					
				else {
					dtoObj.setComparacion(DtoBdObject.COMPARAR_IGUAL);					
					if (dtoObj.getCantidadColumnas()!=cantidadColumnasDestino) {
						dtoObj.setComparacion(DtoBdObject.COMPARAR_DESIGUAL);
					}						
				}	
				lstResultado.add(dtoObj);
			}
		}
		if (filtro.equals(FILTRO_NOT_EQUALS)) {
			List<DtoBdObject> lstR = lstResultado.stream()
					.filter(p -> !p.getComparacion().equals(FILTRO_IGUAL))
					.collect(Collectors.toList());
			return lstR;
		}
		return lstResultado;
	}
		
}
