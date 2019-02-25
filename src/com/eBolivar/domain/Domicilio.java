/**
 * Domicilio.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.eBolivar.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement( name = "domicilioFiscal" )
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "DOMICILIO")
public class Domicilio  implements java.io.Serializable {

    @Id
    @Column(name = "DOM_ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @XmlElement
    @Column(name = "DOM_COD_POSTAL")
    private java.lang.String codPostal;

    @XmlElement
    private java.lang.String datoAdicional;

    @XmlElement
    private java.lang.String descripcionProvincia;

    @XmlElement
    @Column(name = "DOM_DIRECCION")
    private java.lang.String direccion;

    @XmlElement
    private java.lang.Integer idProvincia;

    @XmlElement
    @Column(name = "DOM_LOCALIDAD")
    private java.lang.String localidad;

    @XmlElement
    private java.lang.String tipoDatoAdicional;

    @XmlElement
    @Column(name = "DOM_TIPODOMICILIO")
    private java.lang.String tipoDomicilio;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "DOM_PER_ID")
    private Persona persona;

    public Domicilio() {}

    public Domicilio(Persona persona) {
        this.persona = persona;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
    }

    public String getDatoAdicional() {
        return datoAdicional;
    }

    public void setDatoAdicional(String datoAdicional) {
        this.datoAdicional = datoAdicional;
    }

    public String getDescripcionProvincia() {
        return descripcionProvincia;
    }

    public void setDescripcionProvincia(String descripcionProvincia) {
        this.descripcionProvincia = descripcionProvincia;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(Integer idProvincia) {
        this.idProvincia = idProvincia;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getTipoDatoAdicional() {
        return tipoDatoAdicional;
    }

    public void setTipoDatoAdicional(String tipoDatoAdicional) {
        this.tipoDatoAdicional = tipoDatoAdicional;
    }

    public String getTipoDomicilio() {
        return tipoDomicilio;
    }

    public void setTipoDomicilio(String tipoDomicilio) {
        this.tipoDomicilio = tipoDomicilio;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @Override
    public String toString() {
        return direccion;
    }
}
