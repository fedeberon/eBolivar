package com.eBolivar.domain.usuario;

import com.eBolivar.domain.administradorCuenta.AdministradorCuenta;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.function.Function;

@Entity
@DiscriminatorValue(value="USUARIO_DE_SISTEMA")
public class Usuario extends User{

    @Override
    public String toString() {
        return "Usuario de Sistema" + " - Rol." + getRol().getNombre();
    }

    @Override
    public <T> T clasificar(Function<AdministradorCuenta, ? extends T> administradorCuenta, Function<Usuario, ? extends T> usuario) {
        return usuario.apply(this);
    }
}
