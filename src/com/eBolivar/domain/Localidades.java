/** @author FedeBeron * @version 1.0 */

package com.eBolivar.domain;

import java.io.Serializable;
import java.util.List;

public class Localidades implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer codigo;
	private String nombre;
	private List<Barrios> barrios;
	
	private Departamentos departamento = new Departamentos();

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

	public Departamentos getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamentos departamento) {
		this.departamento = departamento;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Barrios> getBarrios() {
		return barrios;
	}

	public void setBarrios(List<Barrios> barrios) {
		this.barrios = barrios;
	}

 
	
	
}
