package com.eBolivar.domain.administradorCuenta;

import com.eBolivar.domain.usuario.User;
import com.eBolivar.domain.usuario.Usuario;
import com.eBolivar.domain.usuario.UsuarioLocalidad;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.function.Function;

@Entity
@DiscriminatorValue(value="ADMINISTRADOR_DE_CUENTA")
public class AdministradorCuenta extends User {

    @OneToMany(mappedBy = "administradorCuenta")
    private List<PersonaAsociada> personasAsociadas;

    @OneToMany(mappedBy = "administradorCuenta")
    private List<UsuarioLocalidad> usuarioLocalidad;

    public List<UsuarioLocalidad> getUsuarioLocalidad() {
        return usuarioLocalidad;
    }

    public void setUsuarioLocalidad(List<UsuarioLocalidad> usuarioLocalidad) {
        this.usuarioLocalidad = usuarioLocalidad;
    }

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

    @Override
    public <T> T clasificar(Function<AdministradorCuenta, ? extends T> administradorCuenta, Function<Usuario, ? extends T> usuario) {
        return administradorCuenta.apply(this);
    }
}
