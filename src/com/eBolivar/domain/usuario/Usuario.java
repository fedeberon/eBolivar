package com.eBolivar.domain.usuario;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="USUARIO_DE_SISTEMA")
public class Usuario extends User{



    @Override
    public String toString() {
        return "Usuario de Sistema" + " - Rol." + getRol().getNombre();
    }
}
