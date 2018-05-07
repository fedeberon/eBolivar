package com.eBolivar.domain;

import java.io.Serializable;

public class NotificacionPorBien implements Serializable{
	private static final long serialVersionUID = 1030268992180848225L;
	private Integer codigo;
	private Integer tipoRegistro;
	private long codigoElectronicoPago;
	private Integer numeroNotificacion;
	private String notificacion;

	public NotificacionPorBien(){}

	public NotificacionPorBien(Integer tipoRegistro, long codigoElectronicoPago, Integer numeroNotificacion, String notificacion){
		this.tipoRegistro = tipoRegistro;
		this.codigoElectronicoPago = codigoElectronicoPago;
		this.numeroNotificacion = numeroNotificacion;
		this.notificacion = notificacion;
	}
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public Integer getTipoRegistro() {
		return tipoRegistro;
	}
	public void setTipoRegistro(Integer tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}
	public long getCodigoElectronicoPago() {
		return codigoElectronicoPago;
	}
	public void setCodigoElectronicoPago(long codigoElectronicoPago) {
		this.codigoElectronicoPago = codigoElectronicoPago;
	}
	public Integer getNumeroNotificacion() {
		return numeroNotificacion;
	}
	public void setNumeroNotificacion(Integer numeroNotificacion) {
		this.numeroNotificacion = numeroNotificacion;
	}
	public String getNotificacion() {
		return notificacion;
	}
	public void setNotificacion(String notificacion) {
		this.notificacion = notificacion;
	}
	

}
