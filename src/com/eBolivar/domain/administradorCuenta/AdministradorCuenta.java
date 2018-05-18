package com.eBolivar.domain.administradorCuenta;

import com.eBolivar.domain.usuario.User;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@DiscriminatorValue(value="ADMINISTRADOR_DE_CUENTA")
public class AdministradorCuenta extends User {

    @OneToMany(mappedBy = "administradorCuenta")
    private List<PersonaAsociada> personasAsociadas;

    public List<PersonaAsociada> getPersonasAsociadas() {
        return personasAsociadas;
    }

    public void setPersonasAsociadas(List<PersonaAsociada> personasAsociadas) {
        this.personasAsociadas = personasAsociadas;
    }


    @Override
    public String toString() {
        return "Administrador de cuenta" + " - Rol." + getRol().getNombre();
    }
}
