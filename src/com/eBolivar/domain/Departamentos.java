/** @author FedeBeron * @version 1.0 */

package com.eBolivar.domain;

import java.io.Serializable;
import java.util.List;

public class Departamentos implements Serializable {
	
	private static final long serialVersionUID = 1L;
    private Integer codigo;
    private String nombre;
    private List<Localidades> localidades;
    
    
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
	public List<Localidades> getLocalidades() {
		return localidades;
	}
	public void setLocalidades(List<Localidades> localidades) {
		this.localidades = localidades;
	}
 
	
	
    
  
}
