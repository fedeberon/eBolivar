/**
 * Persona.java
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
import java.util.List;
import java.util.Set;

@XmlRootElement( name = "datosGenerales" )
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name ="PERSONAS")
public class Persona  implements java.io.Serializable {

    public static String CUIT_REPRESENTADA_FEDE_BERON = "20285640661";

    public static String CUIT_REPRESENTADA_MUNICIPALIDAD_BOLIVAR = "30999058392";

    @Id
    @Column(name ="PER_ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @XmlElement
    @Column(name ="PER_NOMBRE")
    private java.lang.String nombre;

    @XmlElement
    @Column(name ="PER_APELLIDO")
    private java.lang.String apellido;

    @XmlElement
    @Transient
    private java.lang.Long[] claveInactivaAsociada;

    @XmlElement(name="dependencia")
    @Transient
    private Dependencia dependencia;

    @XmlElement
    @Column(name = "PER_DESCRIPCION_ACTIVIDAD_PRINCIPAL")
    private String descripcionActividadPrincipal;

    @XmlElement(name="domicilioFiscal")
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "persona", fetch = FetchType.LAZY)
    private Set<Domicilio> domicilio;

    @XmlElement
    @Transient
    private java.lang.String estadoClave;

    @XmlElement
    @Column(name = "PER_ID_PERSONA")
    private java.lang.Long idPersona;

    @XmlElement
    @Column(name = "PER_NUMERO_DOCUMENTO")
    private java.lang.String numeroDocumento;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Long[] getClaveInactivaAsociada() {
        return claveInactivaAsociada;
    }

    public void setClaveInactivaAsociada(Long[] claveInactivaAsociada) {
        this.claveInactivaAsociada = claveInactivaAsociada;
    }

    public Dependencia getDependencia() {
        return dependencia;
    }

    public void setDependencia(Dependencia dependencia) {
        this.dependencia = dependencia;
    }

    public String getDescripcionActividadPrincipal() {
        return descripcionActividadPrincipal;
    }

    public void setDescripcionActividadPrincipal(String descripcionActividadPrincipal) {
        this.descripcionActividadPrincipal = descripcionActividadPrincipal;
    }

    public Set<Domicilio> getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Set<Domicilio> domicilio) {
        this.domicilio = domicilio;
    }

    public String getEstadoClave() {
        return estadoClave;
    }

    public void setEstadoClave(String estadoClave) {
        this.estadoClave = estadoClave;
    }

    public Long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Long idPersona) {
        this.idPersona = idPersona;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }
}
