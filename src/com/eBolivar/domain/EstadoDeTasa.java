package com.eBolivar.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ESTADOS_TASA")
public class EstadoDeTasa {

    @Id
    @Column(name = "EST_ID")
    private Integer id;

    @Column(name = "EST_DESCRIPCION")
    private String descripcion;

    public static String VENCIDO = "Fuera de fecha de Vencimiento";
    public static  Integer ACTIVO = 1;
    public static  Integer INACTIVO = 2;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
