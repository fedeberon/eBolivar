package com.eBolivar.service.usuario.interfaces;


import com.eBolivar.domain.Localidad;
import com.eBolivar.domain.administradorCuenta.AdministradorCuenta;
import com.eBolivar.domain.usuario.User;
import com.eBolivar.domain.usuario.Usuario;
import com.eBolivar.domain.usuario.UsuarioLocalidad;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface IUsuarioService extends UserDetailsService {
    Usuario save(Usuario usuario);

    UsuarioLocalidad save(UsuarioLocalidad usuario);

    List<Usuario> findAll();

    List<User> findPageable(Integer pageNumber);

    User get(String username);

    List<AdministradorCuenta> findAllAdministradoresDeCuenta();

    void updateLikeAdministradorDeCuenta(String username);

    User getAutenticate();

    List<AdministradorCuenta> findAdministradorCuenta(String valor, Integer pageNumber);

    AdministradorCuenta getAdministrador(String username);

    List<UsuarioLocalidad> getLocalidades(Usuario usuario);
}
