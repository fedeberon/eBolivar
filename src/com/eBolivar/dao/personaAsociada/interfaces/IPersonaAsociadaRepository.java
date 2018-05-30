package com.eBolivar.dao.personaAsociada.interfaces;

import com.eBolivar.domain.administradorCuenta.PersonaAsociada;

import java.util.List;


public interface IPersonaAsociadaRepository {
    PersonaAsociada save(PersonaAsociada personaAsociada);

    List<PersonaAsociada> findAll(String username);

}
