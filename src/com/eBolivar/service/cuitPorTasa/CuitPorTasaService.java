package com.eBolivar.service.cuitPorTasa;

import com.eBolivar.dao.interfaces.ICuitPorTasaRepository;
import com.eBolivar.domain.PadronAsociado;
import com.eBolivar.domain.Persona;
import com.eBolivar.service.cuitPorTasa.interfaces.ICuitPorTasaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuitPorTasaService implements ICuitPorTasaService {

    @Autowired
    private ICuitPorTasaRepository dao;

    @Override
    public boolean isCuitAsociadoAPadron(String padron){
        return dao.isCuitAsociadoAPadron(padron);
    }

    @Override
    public PadronAsociado save(PadronAsociado padronAsociado) {
        return dao.save(padronAsociado);
    }

    @Override
    public boolean exist(PadronAsociado padronAsociado) {
        return dao.exist(padronAsociado);
    }

    @Override
    public PadronAsociado get(Integer id) {
        return dao.get(id);
    }

    @Override
    public List<PadronAsociado> byPersona(Persona persona) {
        return dao.byPersona(persona);
    }

}
