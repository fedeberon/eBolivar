package com.eBolivar.service.tasa.interfaces;

import com.eBolivar.domain.Tasa;
import com.eBolivar.domain.TasaPersonaPadron;
import com.eBolivar.enumeradores.AnioEnum;

import java.util.Collection;
import java.util.List;

/**
 * Created by Fede Beron on 30/5/2017.
 */
public interface ITasaService {
    List<Tasa> findAll();

    List<Tasa> findAllAnio(String anio);

    List<Tasa> findByTasaPersonaPadron(List<TasaPersonaPadron> tasasPorPadronPorPesona, AnioEnum anio);
}
