package com.eBolivar.domain;


import javax.persistence.*;

@Entity
@Table(name = "CUIT_POR_TASAS")
public class PadronAsociado {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "CPT_ID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "PAD_PER_ID")
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "PAD_ID")
    private Padron padron;


    public static String LEYENDA_DE_PADRON_GENERICO = "TASA MUNICIPAL";


    public PadronAsociado() {
    }

    public PadronAsociado(Persona persona, Padron padron) {
        this.persona = persona;
        this.padron = padron;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
}
