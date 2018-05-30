package com.eBolivar.service.cuitPorTasa.interfaces;

import com.eBolivar.domain.PadronAsociado;
import com.eBolivar.domain.Persona;

import java.util.List;

/**
 * Created by Fede Beron on 27/2/2017.
 */
public interface ICuitPorTasaService {
    boolean isCuitAsociadoAPadron(String padron);

    PadronAsociado save(PadronAsociado padronAsociado);

    boolean exist(PadronAsociado padronAsociado);

    PadronAsociado get(Integer id);

    List<PadronAsociado> byPersona(Persona persona);
}
