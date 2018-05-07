/** @author FedeBeron * @version 1.0 */

package com.eBolivar.domain;

import java.io.Serializable;

public class TipoGuiaTramite implements Serializable {
	
	private static final long serialVersionUID = 1L;
    private Integer codigo;
    private String nombre;
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
  
    
    
}
