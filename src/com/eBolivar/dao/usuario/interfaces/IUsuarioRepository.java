package com.eBolivar.dao.usuario.interfaces;

import com.eBolivar.domain.usuario.Usuario;

/**
 * Created by Damian Gallego on 7/5/2018.
 */
public interface IUsuarioRepository {
    Usuario get(String username);

    Usuario save(Usuario usuario);
}
