package com.eBolivar.domain.usuario;

import com.eBolivar.domain.administradorCuenta.AdministradorCuenta;
import com.eBolivar.web.UsuarioController;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;
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
