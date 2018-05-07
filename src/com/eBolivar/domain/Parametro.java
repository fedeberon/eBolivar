/** @author Santiago Scalzadonna * @version 1.0 */ 
package com.eBolivar.domain;

import com.eBolivar.common.IBusinessObject;

public class Parametro implements IBusinessObject{

	private String id;
	private String nombre;
	private String valor;
	private String descripcion;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	

}
