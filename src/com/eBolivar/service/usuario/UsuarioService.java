package com.eBolivar.service.usuario;

import com.eBolivar.dao.usuario.interfaces.IUsuarioRepository;
import com.eBolivar.domain.rol.Rol;
import com.eBolivar.domain.usuario.Usuario;
import com.eBolivar.service.usuario.interfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private IUsuarioRepository dao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        return dao.get(username);
    }

    @Override
    public Usuario save(Usuario usuario){
        usuario.setRol(new Rol(Rol.ROL_CONTRIBUYENTE));
        return dao.save(usuario);
    }
}
