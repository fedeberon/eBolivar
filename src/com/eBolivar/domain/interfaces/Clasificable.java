package com.eBolivar.domain.interfaces;

import com.eBolivar.domain.administradorCuenta.AdministradorCuenta;
import com.eBolivar.domain.usuario.Usuario;

import java.util.function.Function;

/**
 * Created by Lucas Cortés on 14/02/2019.
 */
public interface Clasificable {

    <T> T clasificar(Function<AdministradorCuenta, ? extends T> administradorCuenta, Function<Usuario,? extends T > usuario);

}
