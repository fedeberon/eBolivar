package com.eBolivar.validator;

import com.eBolivar.domain.Persona;
import com.eBolivar.service.persona.interfaces.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PersonaValidator implements Validator {

    @Autowired
    private IPersonaService personaService;

    public PersonaValidator() {}

    public boolean supports(Class<?> clazz)
    {
        return Persona.class.equals(clazz);
    }

    public void validate(Object target, Errors errors)
    {
        Persona persona = (Persona)target;

        if(persona.getId() == null){
            if ((persona.getIdPersona() != null) && (personaService.getByIdPersona(persona.getIdPersona().toString()) != null)) {
                errors.rejectValue("idPersona", "Persona.idPersona", "el numero de CUIT ya existe.");
            }
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "Persona.nombre", "Ingrese un nombre");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "apellido", "Persona.apellido", "Ingrese un apellido");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idPersona", "Persona.idPersona", "Ingrese un CUIT");
    }
}