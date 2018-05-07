/** @author FedeBeron * @version 1.0 */

package com.eBolivar.domain;

import java.io.Serializable;

public class PasoGuiaTramite implements Serializable {
	
	private static final long serialVersionUID = 1L;
    private Integer codigo;
    private String nombre;
    private String descripcion;
    private String urlImg;
    private GuiaTramite guiaTramite = new GuiaTramite();
    
    
    
    
    
	public GuiaTramite getGuiaTramite() {
		return guiaTramite;
	}
	public void setGuiaTramite(GuiaTramite guiaTramite) {
		this.guiaTramite = guiaTramite;
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
	public String getUrlImg() {
		return urlImg;
	}
	public void setUrlImg(String urlImg) {
		this.urlImg = urlImg;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
  
    
    
}
