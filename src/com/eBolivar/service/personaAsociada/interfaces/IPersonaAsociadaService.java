package com.eBolivar.service.personaAsociada.interfaces;

import com.eBolivar.domain.administradorCuenta.PersonaAsociada;

import java.util.List;


public interface IPersonaAsociadaService {
    PersonaAsociada save(PersonaAsociada personaAsociada);

    List<PersonaAsociada> findAll(String username);
}
