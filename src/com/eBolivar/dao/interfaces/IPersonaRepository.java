package com.eBolivar.dao.interfaces;

import com.eBolivar.domain.PadronAsociado;
import com.eBolivar.domain.Persona;

import java.util.List;

/**
 * Created by Fede Beron on 31/3/2017.
 */
public interface IPersonaRepository {
    Persona save(Persona persona);

    Persona get(Integer id);

    Persona getByCUIT(String cuit);

    List<Persona> getByNombreYApellido(String nombre, String apellido);

    List<PadronAsociado> getByPadron(String padron);
}
