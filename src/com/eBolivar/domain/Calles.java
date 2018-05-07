/** @author JoseAlv * @version 1.0 */

package com.eBolivar.domain;

import java.io.Serializable;

public class Calles implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer codigo;
	private String nombre;

	private Barrios barrio = new Barrios();

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

	public Barrios getBarrio() {
		return barrio;
	}

	public void setBarrio(Barrios barrio) {
		this.barrio = barrio;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
