/** @author FedeBeron * @version 1.0 */

package com.eBolivar.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GuiaTramite implements Serializable {
	
	private static final long serialVersionUID = 1L;
    private Integer codigo;
    private String titulo;
    private String descripcion;
    private String detalle;
    private TipoGuiaTramite tipo = new TipoGuiaTramite();
    private List<PasoGuiaTramite> pasos;

	public List<PasoGuiaTramite> getPasos() {
		return pasos;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public TipoGuiaTramite getTipo() {
		return tipo;
	}

	public void setTipo(TipoGuiaTramite tipo) {
		this.tipo = tipo;
	}

	public void setPasos(List<PasoGuiaTramite> pasos) {
		this.pasos = pasos;
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

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	} 

    
    
    
    
}
