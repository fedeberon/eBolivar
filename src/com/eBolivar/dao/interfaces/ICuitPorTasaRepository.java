package com.eBolivar.dao.interfaces;

import com.eBolivar.domain.Padron;
import com.eBolivar.domain.PadronAsociado;
import com.eBolivar.domain.Persona;
import java.util.List;

public abstract interface ICuitPorTasaRepository
{
    public abstract boolean isCuitAsociadoAPadron(String paramString);

    public abstract PadronAsociado save(PadronAsociado paramPadronAsociado);

    public abstract boolean exist(PadronAsociado paramPadronAsociado);

    public abstract PadronAsociado get(Integer paramInteger);

    public abstract Padron get(String paramString);

    public abstract List<PadronAsociado> byPersona(Persona paramPersona);

    public abstract void remove(Integer paramInteger);
}