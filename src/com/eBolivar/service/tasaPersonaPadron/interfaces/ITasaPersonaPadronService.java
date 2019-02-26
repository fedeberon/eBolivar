package com.eBolivar.service.tasaPersonaPadron.interfaces;

import com.eBolivar.domain.Padron;
import com.eBolivar.domain.Persona;
import com.eBolivar.domain.TasaPersonaPadron;

import java.util.List;

/**
 * Created by Damian Gallego on 24/5/2018.
 */
public interface ITasaPersonaPadronService {

    List<TasaPersonaPadron> findByPersonaPadron(Persona persona, Padron padron);
}
