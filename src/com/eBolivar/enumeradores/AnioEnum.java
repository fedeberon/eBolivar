package com.eBolivar.enumeradores;

/**
 * Created by Damian Saez on 9/5/2018.
 */
public enum AnioEnum{

    A_2018("2018"),
    A_2017("2017"),
    A_2016("2016");

    private String descripcion;

    AnioEnum(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
