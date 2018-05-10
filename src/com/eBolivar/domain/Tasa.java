package com.eBolivar.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TASAS")
public class Tasa {

    @Id
    @Column(name = "TAS_ID")
    private Integer id;

    @Column(name = "TAS_CODIGO")
    private Long codigo;

    @Column(name = "TAS_CONSEPTO")
    private String concepto;

    @Column(name = "TAS_ALICUOTA")
    private Float alicuta;

    @Column(name = "TAS_IMPORTE")
    private Double importe = 0.0;

    @Column(name = "TAS_ANIO")
    private String anio;

    public static Integer SIN_DATOS = -1;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Float getAlicuta() {
        return alicuta;
    }

    public void setAlicuta(Float alicuta) {
        this.alicuta = alicuta;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    @Override
    public String toString() {
        return (codigo == null ? "" : codigo + " - ") + concepto + (alicuta != null ? " - " + alicuta + "% por mil" : "");
    }
}
