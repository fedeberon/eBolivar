package com.eBolivar.bean;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Created by Fede Beron on 26/9/2017.
 */
public class FormatoUtil {

    public static Double formatearImporte(Double importeAFormatear){
        long factor = (long) Math.pow(10, 2);
        importeAFormatear = importeAFormatear * factor;
        long tmp = Math.round(importeAFormatear);
        return (double) tmp / factor;
    }
}
