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

    public String getBaseImponibleView() {
        return baseImponibleView;
    }

    public void setBaseImponibleView(String baseImponibleView) {
        this.baseImponibleView = baseImponibleView;
    }
}
