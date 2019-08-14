package com.eBolivar.service.personaAsociada;


import com.eBolivar.dao.personaAsociada.interfaces.IPersonaAsociadaRepository;
import com.eBolivar.dao.usuario.interfaces.IUsuarioRepository;
import com.eBolivar.domain.Padron;
import com.eBolivar.domain.administradorCuenta.PersonaAsociada;
import com.eBolivar.service.personaAsociada.interfaces.IPersonaAsociadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaAsociadaService implements IPersonaAsociadaService{

    @Autowired
    private IPersonaAsociadaRepository dao;

    @Override
    public PersonaAsociada save(PersonaAsociada personaAsociada) {
        return dao.save(personaAsociada);
    }

    @Override
    public List<PersonaAsociada> findAll(String username) {
        return dao.findAll(username);
    }
}
