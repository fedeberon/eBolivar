package com.eBolivar.dao.interfaces;

import com.eBolivar.domain.Localidad;
import com.eBolivar.domain.Padron;
import com.eBolivar.domain.PadronAsociado;
import com.eBolivar.domain.Persona;

import java.util.List;

public interface ICuitPorTasaRepository
{
    boolean isCuitAsociadoAPadron(String paramString);

    PadronAsociado save(PadronAsociado paramPadronAsociado);

    boolean exist(PadronAsociado paramPadronAsociado);

    PadronAsociado get(Integer paramInteger);

    Padron get(String paramString);

    List<PadronAsociado> byPersona(Persona paramPersona);

    List<PadronAsociado> byPersona(Persona persona, List<Localidad> localidades);

    void remove(Integer paramInteger);
}