package com.eBolivar.service.usuarioLocalidad;

import com.eBolivar.domain.Localidad;
import com.eBolivar.domain.usuario.UsuarioLocalidad;

import java.util.List;

/**
 * Created by Damian Gallego on 28/3/2019.
 */
public interface IUsuarioLocalidadService {
    List<UsuarioLocalidad> findAllByLocalidad(Localidad localidad);
}
