package com.eBolivar.service.usuarioLocalidad;

import com.eBolivar.dao.usuarioLocalidad.IUsuarioLocalidadRepository;
import com.eBolivar.domain.Localidad;
import com.eBolivar.domain.usuario.UsuarioLocalidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Damian Gallego on 28/3/2019.
 */
@Service
public class UsuarioLocalidadService implements  IUsuarioLocalidadService {

    @Autowired
    private IUsuarioLocalidadRepository dao;


    @Override
    public List<UsuarioLocalidad> findAllByLocalidad(Localidad localidad){
        return dao.findAllByLocalidad(localidad);
    }



}
