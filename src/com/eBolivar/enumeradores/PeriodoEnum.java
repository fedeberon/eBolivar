package com.eBolivar.enumeradores;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Created by Fede Beron on 12/7/2017.
 */
public enum PeriodoEnum {

    PRIMER_BIMESTRE("1er Bimestre"),
    SEGUNDO_BIMESTRE("2do Bimestre"),
    TERCER_BIMESTRE("3er Bimestre"),
    CUARTO_BIMESTRE("4to Bimestre"),
    QUINTO_BIMESTRE("5to Bimestre"),
    SEXTO_BIMESTRE("6to Bimestre"),
    ANUAL("Anual");

    private String descripcion;

    PeriodoEnum(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static Stream<PeriodoEnum> stream() {
        return Arrays.stream(PeriodoEnum.values());
    }



}
