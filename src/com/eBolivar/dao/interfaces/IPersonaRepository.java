package com.eBolivar.dao.interfaces;

import com.eBolivar.common.SearchObject;
import com.eBolivar.domain.Padron;
import com.eBolivar.domain.PadronAsociado;
import com.eBolivar.domain.Persona;
import java.util.List;

public abstract interface IPersonaRepository
{
    public abstract Persona save(Persona paramPersona);

    public abstract Persona get(Integer paramInteger);

    public abstract Persona getByCUIT(String paramString);

    public abstract List<Persona> getByNombreYApellido(String paramString1, String paramString2);

    public abstract List<PadronAsociado> getByPadron(Padron paramPadron);

    public abstract List<Persona> search(SearchObject paramSearchObject);
}