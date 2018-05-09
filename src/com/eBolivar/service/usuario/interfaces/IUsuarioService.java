package com.eBolivar.service.usuario.interfaces;


import com.eBolivar.domain.usuario.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface IUsuarioService extends UserDetailsService {
    Usuario save(Usuario usuario);
}
