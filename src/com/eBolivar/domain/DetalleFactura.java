package com.eBolivar.domain;

import java.io.Serializable;

public class DetalleFactura implements Serializable{

	
	private static final long serialVersionUID = 4746800303439117235L;
	private Integer codigo;
	private Integer tipoRegistro;
	private String idFactura;
	private Integer item;
	private String detalle;
	
	public DetalleFactura(){}

	public DetalleFactura(Integer tipoRegistro, String idFactura, Integer item, String detalle){
		
		this.tipoRegistro = tipoRegistro;
		this.idFactura = idFactura;
		this.item = item;
		this.detalle = detalle;
		
	}
	
	public Integer getTipoRegistro() {
		return tipoRegistro;
	}
	public void setTipoRegistro(Integer tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}
	public String getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(String idFactura) {
		this.idFactura = idFactura;
	}
	public Integer getItem() {
		return item;
	}
	public void setItem(Integer item) {
		this.item = item;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
}
