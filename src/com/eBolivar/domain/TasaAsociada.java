package com.eBolivar.domain;


import javax.persistence.*;

@Entity
@Table(name = "TASA_ASOCIADAS")
public class TasaAsociada {

    @Id
    @Column(name = "TASA_ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "DEC_ID")
    private DeclaracionJurada declaracionJurada;

    @OneToOne
    @JoinColumn(name = "TAS_ID")
    private Tasa tasa;

    @Column(name = "TASA_IMP_CALCULADO")
    private Double importeCalculadoSobreBaseImponible = 0.0;

    @Column(name = "TASA_DEDUC_ART_89")
    private Double deduccionArticulo89 = 0.0;

    @Column(name = "TASA_DEDUC_ART_90")
    private Double deduccionArticulo90 = 0.0;

    @Column(name = "TASA_BASE_IMPONIBLE", precision = 10, scale = 2)
    private Double baseImponible = 0.0;

    @Column(name = "TASA_PUESTOS_ATENCION_BANCARIA")
    private Integer puestoAtencionBancaria = 0;

    @Column(name = "TASA_PERSONAL_CONTRATADO")
    private Integer personalContratado = 0;

    @Column(name = "TASA_CANT_CAJEROS_AUTOMATICOS")
    private Integer cajerosAutomaticos = 0;

    @Column(name = "TASA_CANT_CAJEROS_AUTOMATICOS_INDEPENDIENTE")
    private Integer cajerosAutomaticosIndependiente = 0;

    @Column(name = "TASA_CALCULO_MINIMO")
    private Double importeCalculoMinimo;

    @Column(name = "TASA_TOTAL_PUESTOS_ATENCION_BANCARIA")
    private Double totalPuestoAtencionBancaria;

    @Column(name = "TASA_TOTAL_PERSONAL_CONTRATADO")
    private Double totalPersonalContratado;

    @Column(name = "TASA_TOTAL_CANT_CAJEROS_AUTOMATICOS")
    private Double totalCajerosAutomaticos;

    @Column(name = "TASA_TOTAL_CAJEROS_AUTOMATICOS_INDEPENDIENTE")
    private Double totalCajerosAutomaticosIndependiente;

    private String baseImponibleView;

    public DeclaracionJurada getDeclaracionJurada() {
        return declaracionJurada;
    }

    public void setDeclaracionJurada(DeclaracionJurada declaracionJurada) {
        this.declaracionJurada = declaracionJurada;
    }

    public Tasa getTasa() {
        return tasa;
    }

    public void setTasa(Tasa tasa) {
        this.tasa = tasa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getImporteCalculadoSobreBaseImponible() {
        return importeCalculadoSobreBaseImponible;
    }

    public void setImporteCalculadoSobreBaseImponible(Double importeCalculadoSobreBaseImponible) {
        this.importeCalculadoSobreBaseImponible = importeCalculadoSobreBaseImponible;
    }

    public Double getBaseImponible() {
        return baseImponible;
    }

    public void setBaseImponible(Double baseImponible) {
        this.baseImponible = baseImponible;
    }

    public Double getDeduccionArticulo89() {
        return deduccionArticulo89;
    }

    public void setDeduccionArticulo89(Double deduccionArticulo89) {
        this.deduccionArticulo89 = deduccionArticulo89;
    }

    public Double getDeduccionArticulo90() {
        return deduccionArticulo90;
    }

    public void setDeduccionArticulo90(Double deduccionArticulo90) {
        this.deduccionArticulo90 = deduccionArticulo90;
    }

    public Integer getPuestoAtencionBancaria() {
        return puestoAtencionBancaria;
    }

    public void setPuestoAtencionBancaria(Integer puestoAtencionBancaria) {
        this.puestoAtencionBancaria = puestoAtencionBancaria;
    }

    public Integer getPersonalContratado() {
        return personalContratado;
    }

    public void setPersonalContratado(Integer personalContratado) {
        this.personalContratado = personalContratado;
    }

    public Integer getCajerosAutomaticos() {
        return cajerosAutomaticos;
    }

    public void setCajerosAutomaticos(Integer cajerosAutomaticos) {
        this.cajerosAutomaticos = cajerosAutomaticos;
    }

    public Integer getCajerosAutomaticosIndependiente() {
        return cajerosAutomaticosIndependiente;
    }

    public void setCajerosAutomaticosIndependiente(Integer cajerosAutomaticosIndependiente) {
        this.cajerosAutomaticosIndependiente = cajerosAutomaticosIndependiente;
    }

    public Double getImporteCalculoMinimo() {
        return importeCalculoMinimo;
    }

    public void setImporteCalculoMinimo(Double importeCalculoMinimo) {
        this.importeCalculoMinimo = importeCalculoMinimo;
    }

    public Double getTotalPuestoAtencionBancaria() {
        return totalPuestoAtencionBancaria;
    }

    public void setTotalPuestoAtencionBancaria(Double totalPuestoAtencionBancaria) {
        this.totalPuestoAtencionBancaria = totalPuestoAtencionBancaria;
    }

    public Double getTotalPersonalContratado() {
        return totalPersonalContratado;
    }

    public void setTotalPersonalContratado(Double totalPersonalContratado) {
        this.totalPersonalContratado = totalPersonalContratado;
    }

    public Double getTotalCajerosAutomaticos() {
        return totalCajerosAutomaticos;
    }

    public void setTotalCajerosAutomaticos(Double totalCajerosAutomaticos) {
        this.totalCajerosAutomaticos = totalCajerosAutomaticos;
    }

    public Double getTotalCajerosAutomaticosIndependiente() {
        return totalCajerosAutomaticosIndependiente;
    }

    public void setTotalCajerosAutomaticosIndependiente(Double totalCajerosAutomaticosIndependiente) {
        this.totalCajerosAutomaticosIndependiente = totalCajerosAutomaticosIndependiente;
    }

    public String getBaseImponibleView() {
        return baseImponibleView;
    }

    public void setBaseImponibleView(String baseImponibleView) {
        this.baseImponibleView = baseImponibleView;
    }
}
