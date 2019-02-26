package com.eBolivar.dao.tasa.interfaces;

import com.eBolivar.domain.Persona;
import com.eBolivar.domain.Tasa;
import com.eBolivar.domain.TasaPersonaPadron;
import com.eBolivar.enumeradores.AnioEnum;

import java.util.Collection;
import java.util.List;


public interface ITasaRepository {
    List<Tasa> findAll();

    List<Tasa> findAllAnio(String anio);

    List<Tasa> findByTasaPersonaPadron(Collection<Long> codigos, AnioEnum anio);
}
