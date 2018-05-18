package com.eBolivar.domain.administradorCuenta;

import com.eBolivar.domain.Persona;

import javax.persistence.*;


@Entity
@Table(name = "PERSONAS_ASOCIADAS")
public class PersonaAsociada {

    @Id
    @Column(name = "PAS_ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PER_ID")
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "USU_USERNAME")
    private AdministradorCuenta administradorCuenta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public AdministradorCuenta getAdministradorCuenta() {
        return administradorCuenta;
    }

    public void setAdministradorCuenta(AdministradorCuenta administradorCuenta) {
        this.administradorCuenta = administradorCuenta;
    }
}
