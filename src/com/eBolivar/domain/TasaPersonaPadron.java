package com.eBolivar.domain;

import javax.persistence.*;

@Entity
@Table(name = "TASA_PERSONA_PADRON")
public class TasaPersonaPadron {

    @Id
    @Column(name = "TPP_ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "TPP_PER_ID_PERSONA")
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "TPP_PAD_ID")
    private Padron padron;

    @Column(name = "TPP_CODIGO")
    private Long codigo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Padron getPadron() {
        return padron;
    }

    public void setPadron(Padron padron) {
        this.padron = padron;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
}
