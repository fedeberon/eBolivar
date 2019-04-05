package com.eBolivar.bean;

import java.text.DecimalFormat;
import java.util.Locale;

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

    public static String convertirBaseImponibleNotacion(Double val){
        Locale.setDefault(Locale.US);
        DecimalFormat num = new DecimalFormat("#,###.00");
        return num.format(val);
    }
}
