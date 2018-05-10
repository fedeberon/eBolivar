package com.eBolivar.dao.tasa.interfaces;

import com.eBolivar.domain.Tasa;

import java.util.List;

/**
 * Created by Fede Beron on 30/5/2017.
 */
public interface ITasaRepository {
    List<Tasa> findAll();

    List<Tasa> findAllAnio(String anio);
}
