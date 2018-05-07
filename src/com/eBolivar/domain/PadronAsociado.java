package com.eBolivar.domain;


public class PadronAsociado {

    private Integer id;
    private String padron;
    private Persona persona;
    private String leyendaDelTributo;

    public static String LEYENDA_DE_PADRON_GENERICO = "TASA MUNICIPAL";


    public PadronAsociado() {
    }

    public PadronAsociado(Persona persona, String padron, String leyendaDelTributo) {
        this.persona = persona;
        this.padron = padron;
        this.leyendaDelTributo = leyendaDelTributo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getPadron() {
        return padron;
    }

    public void setPadron(String padron) {
        this.padron = padron;
    }

    public String getLeyendaDelTributo() {
        return leyendaDelTributo;
    }

    public void setLeyendaDelTributo(String leyendaDelTributo) {
        this.leyendaDelTributo = leyendaDelTributo;
    }
}
