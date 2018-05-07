package com.eBolivar.service.padron.interfaces;

import com.eBolivar.domain.Padron;

/**
 * Created by Fede Beron on 11/7/2017.
 */
public interface IPadronService {
    Padron save(Padron padron);

    Padron get(Integer id);

    Padron getByNumero(String id);

    Padron getPadron(String numero);
}
