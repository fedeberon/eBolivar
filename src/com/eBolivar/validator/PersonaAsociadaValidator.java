package com.eBolivar.validator;

import com.eBolivar.domain.Persona;
import com.eBolivar.domain.administradorCuenta.PersonaAsociada;
import com.eBolivar.service.persona.interfaces.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by Damian Gallego on 16/5/2018.
 */
@Component
public class PersonaAsociadaValidator  implements Validator {

    @Autowired
    private IPersonaService personaService;

    @Override
    public boolean supports(Class<?> aClass) {
        return PersonaAsociada.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        PersonaAsociada personaAsociada = (PersonaAsociada) o;
        Persona persona = personaService.getByCUIT(personaAsociada.getPersona().getIdPersona().toString());
        if(persona == null){
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "persona.idPersona", "persona.req", "No encontramos el numero de CUIT.");
        }
    }
}
