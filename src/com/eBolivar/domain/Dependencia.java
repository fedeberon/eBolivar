/**
 * Dependencia.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.eBolivar.domain;

import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Component
@XmlRootElement(name = "dependencia")
@XmlAccessorType(XmlAccessType.FIELD)
public class Dependencia  implements java.io.Serializable {


    private Integer id;
    @XmlAttribute
    private java.lang.String descripcionDependencia;
    @XmlAttribute
    private java.lang.Integer idDependencia;

    public Dependencia() {
    }

    public java.lang.String getDescripcionDependencia() {
        return descripcionDependencia;
    }

    public void setDescripcionDependencia(java.lang.String descripcionDependencia) {
        this.descripcionDependencia = descripcionDependencia;
    }

    public java.lang.Integer getIdDependencia() {
        return idDependencia;
    }

    public void setIdDependencia(java.lang.Integer idDependencia) {
        this.idDependencia = idDependencia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
