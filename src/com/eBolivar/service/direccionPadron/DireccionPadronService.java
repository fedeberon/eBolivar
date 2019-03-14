package com.eBolivar.service.direccionPadron;

import com.eBolivar.dao.direccionPadron.interfaces.IDireccionPadronRespository;
import com.eBolivar.domain.DireccionPadron;
import com.eBolivar.service.direccionPadron.interfaces.IDireccionPadronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by erwin on 13/3/2019.
 */
@Service
public class DireccionPadronService implements IDireccionPadronService {


    @Autowired
    private IDireccionPadronRespository dao;


    public List<DireccionPadron> getByNumeroDePadron(String padron){
        return dao.getByNumeroDePadron(padron);
    }

}
