package com.eBolivar.dao.padron.interfaces;

import com.eBolivar.domain.Padron;

/**
 * Created by Fede Beron on 11/7/2017.
 */
public interface IPadronRepository {
    Padron save(Padron padron);

    Padron get(Integer id);

    Padron getByNumero(String numero);
}
