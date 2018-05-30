package com.eBolivar.service.persona.interfaces;

import com.eBolivar.domain.Padron;
import com.eBolivar.domain.PadronAsociado;
import com.eBolivar.domain.Persona;

import java.util.List;

/**
 * Created by Fede Beron on 30/3/2017.
 */
public interface IPersonaService {
    Persona create_PersonaRequest(String token, String sign, String cuitRepresentada, String idPersona) throws Exception;

    Persona get(Integer id);

    Persona getByCUIT(String cuit);

    List<Persona> getByNombreYApellido(String nombre, String apellido);

    List<PadronAsociado> getByPadron(Padron padron);
}
