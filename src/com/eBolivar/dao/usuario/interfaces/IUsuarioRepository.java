package com.eBolivar.dao.usuario.interfaces;

import com.eBolivar.domain.DeclaracionJurada;
import com.eBolivar.domain.administradorCuenta.AdministradorCuenta;
import com.eBolivar.domain.usuario.User;
import com.eBolivar.domain.usuario.Usuario;
import com.eBolivar.domain.usuario.UsuarioLocalidad;

import java.util.List;


public interface IUsuarioRepository {
    User get(String username);

    Usuario save(Usuario usuario);

    UsuarioLocalidad save(UsuarioLocalidad usuario);

    List<Usuario> findAll();

    List<User> findPageable(Integer pageNumber);

    List<AdministradorCuenta> findAdministradorCuenta(String valor, Integer pageNumber);

    List<AdministradorCuenta> findAllAdministradoresDeCuenta();

    void updateLikeAdministradorDeCuenta(String username);

    AdministradorCuenta getAdministradorDeCuenta(String username);

    List<UsuarioLocalidad> getLocalidades(Usuario usuario);
}
