package com.eBolivar.dao.usuario.interfaces;

import com.eBolivar.domain.administradorCuenta.AdministradorCuenta;
import com.eBolivar.domain.usuario.User;
import com.eBolivar.domain.usuario.Usuario;

import java.util.List;


public interface IUsuarioRepository {
    User get(String username);

    Usuario save(Usuario usuario);

    List<User> findAll();

    List<AdministradorCuenta> findAdministradorCuenta(String valor, Integer pageNumber);

    List<AdministradorCuenta> findAllAdministradoresDeCuenta();

    void updateLikeAdministradorDeCuenta(String username);

}
