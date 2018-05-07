package com.eBolivar.enumeradores;

/**
 * Created by Fede Beron on 12/7/2017.
 */
public enum ImporteBaseEnum {

    IMPORTE_BASE_TASA_SEGURIDAD_E_HIGIENE(350.00);

    private Double importe;


    ImporteBaseEnum(Double importe) {
        this.importe = importe;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }
}
