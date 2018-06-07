package com.eBolivar.dao.interfaces;

import com.eBolivar.domain.PadronAsociado;
import com.eBolivar.domain.Persona;

import java.util.List;

public interface ICuitPorTasaRepository {
    boolean isCuitAsociadoAPadron(String padron);

    PadronAsociado save(PadronAsociado padronAsociado);

    boolean exist(PadronAsociado padronAsociado);

    PadronAsociado get(Integer id);

    List<PadronAsociado> byPersona(Persona persona);
}
