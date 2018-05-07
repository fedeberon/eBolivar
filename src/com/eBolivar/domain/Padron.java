package com.eBolivar.domain;

import javax.persistence.*;

@Entity
@Table(name = "PADRONES")
public class Padron {

    @Id
    @Column(name = "PAD_ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(name = "PAD_NUMERO")
    private String numero;

    @OneToOne
    @JoinColumn(name = "TI_ID")
    private TipoImpuesto tipoImpuesto;

    public Padron() { }

    public Padron(String numeroDePadron, TipoImpuesto tipoImpuesto) {
        this.numero = numeroDePadron;
        this.tipoImpuesto = tipoImpuesto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public TipoImpuesto getTipoImpuesto() {
        return tipoImpuesto;
    }

    public void setTipoImpuesto(TipoImpuesto tipoImpuesto) {
        this.tipoImpuesto = tipoImpuesto;
    }

    @Override
    public String toString() {
        return "Padron{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", tipoImpuesto=" + tipoImpuesto.getNombre() +
                '}';
    }
}
