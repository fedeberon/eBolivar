package com.eBolivar.service.tasaPersonaPadron;

import com.eBolivar.dao.tasaPersonaPadron.interfaces.ITasaPersonaPadronRepository;
import com.eBolivar.domain.Padron;
import com.eBolivar.domain.Persona;
import com.eBolivar.domain.TasaPersonaPadron;
import com.eBolivar.service.tasaPersonaPadron.interfaces.ITasaPersonaPadronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TasaPersonaPadronService implements ITasaPersonaPadronService {

    @Autowired
    private ITasaPersonaPadronRepository dao;


    /**
     * Metodo para obtener las tasas asociadas a un CUIT
     *
     * @param persona
     * @param padron
     * @return List Tasa Por Persona por Padron
     */
    @Override
    public List<TasaPersonaPadron> findByPersonaPadron(Persona persona, Padron padron) {
        return dao.findByPersonaPadron(persona, padron);
    }
}
