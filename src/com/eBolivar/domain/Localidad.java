package com.eBolivar.domain;

import javax.persistence.*;

/**
 * Created by Lucas Cortés on 14/02/2019.
 */

@Entity
@Table(name = "LOCALIDADES")
public class Localidad {

    @Id
    @Column(name = "LOC_ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "LOC_NOMBRE")
    private String nombre;

    @Column(name = "DEP_ID")
    private Long depId;

    public Localidad (){
    }

    public Localidad (String nombre){
        this.nombre = nombre;
    }

    public Long getId(){
        return id;
    }

    public String getNombre(){
        return nombre;
    }

    public Long getDepId(){
        return depId;
    }

    @Override
    public boolean equals(Object o) {
        Localidad localidad = (Localidad) o;

        return id != null ? id.equals(localidad.id) : localidad.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
