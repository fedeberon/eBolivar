package com.eBolivar.domain;

import com.eBolivar.domain.Padron;
import com.eBolivar.domain.Persona;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(
        name = "CUIT_POR_TASAS"
)
public class PadronAsociado {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    @Column(
            name = "CPT_ID"
    )
    private Integer id;
    @ManyToOne(
            cascade = {CascadeType.DETACH}
    )
    @JoinColumn(
            name = "PAD_PER_ID"
    )
    private Persona persona;
    @ManyToOne(
            cascade = {CascadeType.DETACH}
    )
    @JoinColumn(
            name = "PAD_ID"
    )
    private Padron padron;
    public static String LEYENDA_DE_PADRON_GENERICO = "TASA MUNICIPAL";

    public PadronAsociado() {
    }

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
}