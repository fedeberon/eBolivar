package com.eBolivar.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CUIT_POR_TASAS")
public class PadronAsociado {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CPT_ID")
    private Integer id;

    @ManyToOne(cascade = {CascadeType.DETACH})
    @JoinColumn(name = "PAD_PER_ID")
    private Persona persona;

    @ManyToOne(cascade = {CascadeType.DETACH})
    @JoinColumn(name = "PAD_ID")
    private Padron padron;

    @OneToMany(mappedBy = "padronAsociado", fetch = FetchType.EAGER)
    private List<DireccionPadron> direccionesDelPadron;

    public PadronAsociado() {}

    public PadronAsociado(Persona persona, Padron padron) {
        this.persona = persona;
        this.padron = padron;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Persona getPersona() {
        return this.persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Padron getPadron() {
        return this.padron;
    }

    public void setPadron(Padron padron) {
        this.padron = padron;
    }

    public List<DireccionPadron> getDireccionesDelPadron() {
        return direccionesDelPadron;
    }

    public void setDireccionesDelPadron(List<DireccionPadron> direccionesDelPadron) {
        this.direccionesDelPadron = direccionesDelPadron;
    }
}