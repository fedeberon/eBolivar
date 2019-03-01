package com.eBolivar.service.localidad;

import com.eBolivar.dao.localidad.interfaces.ILocalidadRepository;
import com.eBolivar.domain.Localidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Lucas Cortés on 22/02/2019.
 */
@Service
public class LocalidadService implements ILocalidadService {

    private ILocalidadRepository dao;

    @Autowired
    public LocalidadService(ILocalidadRepository dao) {
        this.dao = dao;
    }

    @Override
    public List<Localidad> findAll(){
        return dao.findAll();
    }
}
