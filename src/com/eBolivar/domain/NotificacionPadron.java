/**
 * @author FedeBeron * @version 1.0
 */

package com.eBolivar.domain;

import java.io.Serializable;
import java.util.Date;

public class NotificacionPadron implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String padron;
    private String direccionEnvio;
    private String estado;
    private Date fechaAlta;
    private String confirmado;
    private String tasa;
    private String nombreApellido;
    private String dni;
    private String telefono;


    public NotificacionPadron(){}

    public NotificacionPadron(String padron, String direccionEnvio, String estado, Date fechaAlta, String confirmado, String idFactura) {
        this.padron = padron;
        this.direccionEnvio = direccionEnvio;
        this.estado = estado;
        this.fechaAlta = fechaAlta;
        this.confirmado = confirmado;
        this.idFactura = idFactura;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    //aux
    private String idFactura;

    public String getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(String idFactura) {
        this.idFactura = idFactura;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPadron() {
        return padron;
    }

    public void setPadron(String padron) {
        this.padron = padron;
    }

    public String getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(String direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getConfirmado() {
        return confirmado;
    }

    public void setConfirmado(String confirmado) {
        this.confirmado = confirmado;
    }

    public String getTasa() {
        return tasa;
    }

    public void setTasa(String tasa) {
        this.tasa = tasa;
    }

    public String getNombreApellido() {
        return nombreApellido;
    }

    public void setNombreApellido(String nombreApellido) {
        this.nombreApellido = nombreApellido;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
