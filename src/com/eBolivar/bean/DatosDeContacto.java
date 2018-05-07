package com.eBolivar.bean;

import org.springframework.stereotype.Component;

/**
 * Created by Fede Beron on 19/12/2016.
 */
@Component
public class DatosDeContacto {

//    @NotEmpty(message = "Ingrese un nombre")
    private String nombre;
//    @NotEmpty(message = "Ingrese un apellido")
    private String apellido;
//    @NotEmpty(message = "Ingrese un mail para ponernos en contacto")
//    @Email(message = "El email es incorrecto")
    private String email;

    private String tasa;
//    @NotEmpty(message = "Ingrese un comentario")
    private String comentarios;

    public DatosDeContacto() {}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTasa() {
        return tasa;
    }

    public void setTasa(String tasa) {
        this.tasa = tasa;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
}
