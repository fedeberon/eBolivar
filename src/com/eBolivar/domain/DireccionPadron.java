package com.eBolivar.domain;

import javax.persistence.*;

/**
 * Created by erwin on 13/3/2019.
 */

@Entity
@Table(name = "DIRECCIONES_PADRON")
public class DireccionPadron {

    @Id
    @Column(name = "DIR_ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long Id;

    @Column(name = "DIR_NUM_PADRON")
    private String numeroPadron;

    @Column(name =  "DIR_NOM_CALLE")
    private String nombreCalle;

    @Column(name =  "DIR_NUM_CALLE")
    private Integer numeroCalle;

        @OneToOne
    @JoinColumn(name = "DIR_LOC_ID")
    private Localidad localidad;

    @ManyToOne
    @JoinColumn(name = "DIR_CPT_ID")
    private PadronAsociado padron;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNumeroPadron() {
        return numeroPadron;
    }

    public void setNumeroPadron(String numeroPadron) {
        this.numeroPadron = numeroPadron;
    }

    public String getNombreCalle() {
        return nombreCalle;
    }

    public void setNombreCalle(String nombreCalle) {
        this.nombreCalle = nombreCalle;
    }

    public Integer getNumeroCalle() {
        return numeroCalle;
    }

    public void setNumeroCalle(Integer numeroCalle) {
        this.numeroCalle = numeroCalle;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public PadronAsociado getPadron() {
        return padron;
    }

    public void setPadron(PadronAsociado padron) {
        this.padron = padron;
    }

    @Override
    public String toString() {
        return "DeclaracionJurada: Id = " + Id + "Padron: [ " + numeroPadron + " ]" + "Direccion: [ " + nombreCalle +" , "+ numeroCalle +" ]";
    }
}

