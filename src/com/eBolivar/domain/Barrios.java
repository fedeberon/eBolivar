/** @author FedeBeron * @version 1.0 */

package com.eBolivar.domain;

import java.io.Serializable;
import java.util.List;

public class Barrios implements Serializable {
	
	private static final long serialVersionUID = 1L;
    private Integer codigo;
    private String nombre;
    private Localidades localidad=new Localidades();
    private List<Calles> calles;
    
    
    
	public Localidades getLocalidad() {
		return localidad;
	}
	public void setLocalidad(Localidades localidad) {
		this.localidad = localidad;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<Calles> getCalles() {
		return calles;
	}
	public void setCalles(List<Calles> calles) {
		this.calles = calles;
	}
 
    
    
}
