/**
 * Metadata.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.eBolivar.domain;

import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Component
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "metadata")
public class Metadata  implements java.io.Serializable {

    private Integer id;
    @XmlElement
    private java.util.Calendar fechaHora;
    @XmlElement
    private java.lang.String servidor;

    private PersonaReturn personaReturn;

    public Metadata() { }

    public Metadata(
           java.util.Calendar fechaHora,
           java.lang.String servidor) {
           this.fechaHora = fechaHora;
           this.servidor = servidor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public java.util.Calendar getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(java.util.Calendar fechaHora) {
        this.fechaHora = fechaHora;
    }

    public java.lang.String getServidor() {
        return servidor;
    }

    public void setServidor(java.lang.String servidor) {
        this.servidor = servidor;
    }

    public PersonaReturn getPersonaReturn() {
        return personaReturn;
    }

    public void setPersonaReturn(PersonaReturn personaReturn) {
        this.personaReturn = personaReturn;
    }
}
