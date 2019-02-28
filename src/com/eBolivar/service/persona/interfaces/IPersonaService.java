package com.eBolivar.service.persona.interfaces;

import com.eBolivar.common.SearchObject;
import com.eBolivar.domain.Padron;
import com.eBolivar.domain.PadronAsociado;
import com.eBolivar.domain.Persona;
import java.util.List;

public abstract interface IPersonaService
{
    public abstract Persona create_PersonaRequest(String paramString1, String paramString2, String paramString3, String paramString4)
            throws Exception;

    public abstract Persona get(Integer paramInteger);

    public abstract Persona getByCUIT(String paramString);

    public abstract Persona getByIdPersona(String paramString);

    public abstract List<Persona> getByNombreYApellido(String paramString1, String paramString2);

    public abstract List<PadronAsociado> getByPadron(Padron paramPadron);

    public abstract Persona save(Persona paramPersona);

    public abstract List<Persona> search(SearchObject paramSearchObject);
}