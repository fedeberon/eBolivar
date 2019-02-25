package com.eBolivar.domain;

import com.eBolivar.domain.Dependencia;
import com.eBolivar.domain.Domicilio;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
        name = "datosGenerales"
)
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(
        name = "PERSONAS"
)
public class Persona implements Serializable {
    public static String CUIT_REPRESENTADA_FEDE_BERON = "20285640661";
    public static String CUIT_REPRESENTADA_MUNICIPALIDAD_BOLIVAR = "30999058392";
    @Id
    @Column(
            name = "PER_ID"
    )
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Integer id;
    @XmlElement
    @Column(
            name = "PER_NOMBRE"
    )
    private String nombre;
    @XmlElement
    @Column(
            name = "PER_APELLIDO"
    )
    private String apellido;
    @XmlElement
    @Transient
    private Long[] claveInactivaAsociada;
    @XmlElement(
            name = "dependencia"
    )
    @Transient
    private Dependencia dependencia;
    @XmlElement
    @Column(
            name = "PER_DESCRIPCION_ACTIVIDAD_PRINCIPAL"
    )
    private String descripcionActividadPrincipal;
    @XmlElement(
            name = "domicilioFiscal"
    )
    @OneToMany(
            cascade = {CascadeType.PERSIST},
            mappedBy = "persona",
            fetch = FetchType.LAZY
    )
    private Set<Domicilio> domicilio;
    @XmlElement
    @Transient
    private String estadoClave;
    @XmlElement
    @Column(
            name = "PER_ID_PERSONA"
    )
    private Long idPersona;
    @XmlElement
    @Column(
            name = "PER_NUMERO_DOCUMENTO"
    )
    private String numeroDocumento;

    public Persona() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Long[] getClaveInactivaAsociada() {
        return this.claveInactivaAsociada;
    }

    public void setClaveInactivaAsociada(Long[] claveInactivaAsociada) {
        this.claveInactivaAsociada = claveInactivaAsociada;
    }

    public Dependencia getDependencia() {
        return this.dependencia;
    }

    public void setDependencia(Dependencia dependencia) {
        this.dependencia = dependencia;
    }

    public String getDescripcionActividadPrincipal() {
        return this.descripcionActividadPrincipal;
    }

    public void setDescripcionActividadPrincipal(String descripcionActividadPrincipal) {
        this.descripcionActividadPrincipal = descripcionActividadPrincipal;
    }

    public Set<Domicilio> getDomicilio() {
        return this.domicilio;
    }

    public void setDomicilio(Set<Domicilio> domicilio) {
        this.domicilio = domicilio;
    }

    public String getEstadoClave() {
        return this.estadoClave;
    }

    public void setEstadoClave(String estadoClave) {
        this.estadoClave = estadoClave;
    }

    public Long getIdPersona() {
        return this.idPersona;
    }

    public void setIdPersona(Long idPersona) {
        this.idPersona = idPersona;
    }

    public String getNumeroDocumento() {
        return this.numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public boolean equals(Persona p) {
        return p == null?false:p.id == this.id;
    }
}
