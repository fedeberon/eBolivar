package com.eBolivar.service.usuario.interfaces;


import com.eBolivar.domain.administradorCuenta.AdministradorCuenta;
import com.eBolivar.domain.usuario.User;
import com.eBolivar.domain.usuario.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface IUsuarioService extends UserDetailsService {
    Usuario save(Usuario usuario);

    List<User> findAll();

    User get(String username);

    List<AdministradorCuenta> findAllAdministradoresDeCuenta();

    void updateLikeAdministradorDeCuenta(String username);
}
