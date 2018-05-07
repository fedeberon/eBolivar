package com.eBolivar.service.tasa;

import com.eBolivar.dao.tasa.interfaces.ITasaRepository;
import com.eBolivar.domain.Tasa;
import com.eBolivar.service.tasa.interfaces.ITasaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TasaService implements ITasaService {

    @Autowired
    private ITasaRepository dao;

    @Override
    public List<Tasa> findAll(){
        return dao.findAll();
    }

}
