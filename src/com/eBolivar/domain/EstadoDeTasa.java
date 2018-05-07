package com.eBolivar.domain;

public class EstadoDeTasa {

    private Integer id;
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
