/** @author FedeBeron * @version 1.0 */

package com.eBolivar.domain;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class Banner implements Serializable {
	
	private static final long serialVersionUID = 1L;
    private Integer id;
    private String nombre_principal;
    private String nombre_secundario;
    private String direccion_img;
    private String observaciones;
    private MultipartFile file;
    private String estado;
    
    
    
    
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre_principal() {
		return nombre_principal;
	}
	public void setNombre_principal(String nombre_principal) {
		this.nombre_principal = nombre_principal;
	}
	public String getNombre_secundario() {
		return nombre_secundario;
	}
	public void setNombre_secundario(String nombre_secundario) {
		this.nombre_secundario = nombre_secundario;
	}
	public String getDireccion_img() {
		return direccion_img;
	}
	public void setDireccion_img(String direccion_img) {
		this.direccion_img = direccion_img;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
  
    
    
    
}
