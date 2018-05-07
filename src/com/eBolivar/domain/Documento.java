/** @author Santiago Scalzadonna * @version 1.0 */ 
package com.eBolivar.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class Documento implements Serializable{
	
	private static final long serialVersionUID = 8114214955015492274L;
	
	private MultipartFile file;
	private String observaciones;
	private String destino;
	private String originalFileName;
	private Date fecha;
	private String autor;
	private Integer categoria;
	private Integer modalidad;
	
	
 

	 public MultipartFile getFile() {
        return file;
    }

      public void setFile(MultipartFile file) {
        this.file = file;
    }

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Integer getCategoria() {
		return categoria;
	}

	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
	}

	public Integer getModalidad() {
		return modalidad;
	}

	public void setModalidad(Integer modalidad) {
		this.modalidad = modalidad;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
