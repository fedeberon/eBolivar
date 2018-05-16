package com.eBolivar.domain;

import com.eBolivar.enumeradores.AnioEnum;
import com.eBolivar.enumeradores.EstadoDeDeclaracionJurada;
import com.eBolivar.enumeradores.PeriodoEnum;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name ="DECLARACIONES_JURADAS")
public class DeclaracionJurada {

    @Id
    @Column(name = "DEC_ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long Id;

    @OneToOne
    @JoinColumn(name = "PER_ID")
    private Persona persona;

    @OneToOne
    @JoinColumn(name = "PAD_ID")
    private Padron padron;

    @Column(name = "DEC_FECHA")
    private LocalDateTime fecha;

    @Column(name = "DEC_BASE_IMPONIBLE")
    private Double baseImponible = 0.0;

    @Enumerated(EnumType.STRING)
    @Column(name = "DEC_PERIODO")
    private PeriodoEnum periodo;

    @Enumerated(EnumType.STRING)
    @Column(name = "DEC_ANIO")
    private AnioEnum anio;

    @Column(name = "DEC_OBSERVACIONES")
    private String observaciones;

    @OneToMany(mappedBy = "declaracionJurada", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<TasaAsociada> tasas;

    @Column(name = "DEC_TOTAL_CALCULADO")
    private Double totalCalculado = 0.0;

    @Enumerated(EnumType.STRING)
    @Column(name = "DEC_ESTADO")
    private EstadoDeDeclaracionJurada estadoDeDeclaracionJurada;

    @Column(name = "DEC_SALDO_A_FAVOR")
    private Double saldoAFavor = 0.0;

    @Column(name = "DEC_A_FAVOR_CONTRIBUYENTE")
    private Double saldoAFavorDelContribuyente = 0.0;

    @Column(name = "DEC_TOTAL_ANTICIPO")
    private Double totalAnticipo = 0.0;

    @Column(name = "DEC_TASA_ANUAL")
    private Double totalAnual = 0.0;

    public AnioEnum getAnio() {
        return anio;
    }

    public void setAnio(AnioEnum anio) {
        this.anio = anio;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public List<TasaAsociada> getTasas() {
        return tasas;
    }

    public void setTasas(List<TasaAsociada> tasas) {
        this.tasas = tasas;
    }

    public Double getBaseImponible() {
        return baseImponible;
    }

    public void setBaseImponible(Double baseImponible) {
        this.baseImponible = baseImponible;
    }

    public PeriodoEnum getPeriodo() {
        return periodo;
    }

    public void setPeriodo(PeriodoEnum periodo) {
        this.periodo = periodo;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Double getTotalCalculado() {
        return totalCalculado;
    }

    public void setTotalCalculado(Double totalCalculado) {
        this.totalCalculado = totalCalculado;
    }

    public EstadoDeDeclaracionJurada getEstadoDeDeclaracionJurada() {
        return estadoDeDeclaracionJurada;
    }

    public void setEstadoDeDeclaracionJurada(EstadoDeDeclaracionJurada estadoDeDeclaracionJurada) {
        this.estadoDeDeclaracionJurada = estadoDeDeclaracionJurada;
    }

    public Double getSaldoAFavor() {
        return saldoAFavor;
    }

    public void setSaldoAFavor(Double saldoAFavor) {
        this.saldoAFavor = saldoAFavor;
    }

    public Double getSaldoAFavorDelContribuyente() {
        return saldoAFavorDelContribuyente;
    }

    public void setSaldoAFavorDelContribuyente(Double saldoAFavorDelContribuyente) {
        this.saldoAFavorDelContribuyente = saldoAFavorDelContribuyente;
    }

    public Double getTotalAnticipo() {
        return totalAnticipo;
    }

    public void setTotalAnticipo(Double totalAnticipo) {
        this.totalAnticipo = totalAnticipo;
    }

    public Double getTotalAnual() {
        return totalAnual;
    }

    public void setTotalAnual(Double totalAnual) {
        this.totalAnual = totalAnual;
    }

    @Override
    public String toString() {
        return "DeclaracionJurada: Id = " + Id  + " Persona: [ " + persona + " ]" + "Padron: [ " + padron + " ]";
    }
}

