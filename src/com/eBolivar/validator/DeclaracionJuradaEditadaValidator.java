package com.eBolivar.validator;

import com.eBolivar.domain.DeclaracionJurada;
import com.eBolivar.domain.Persona;
import com.eBolivar.service.impuesto.interfaces.IImpuestoService;
import com.eBolivar.service.persona.interfaces.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by Fede Beron on 20/7/2017.
 */
@Component
public class DeclaracionJuradaEditadaValidator  implements Validator{

    @Autowired
    private IPersonaService personaService;

    @Autowired
    private IImpuestoService impuestoService;

    @Override
    public boolean supports(Class<?> clazz) {
        return DeclaracionJurada.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        DeclaracionJurada declaracionJurada = (DeclaracionJurada) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "padron.numero", "Padron.numero.noEncontrado", "Error verifique nuevamente");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "persona.idPersona", "Persona.idPersona.noEncontrado", "Error verifique nuevamente");

        if(!impuestoService.isUnPadron(declaracionJurada.getPadron().getNumero())){
            errors.rejectValue("padron.numero", "Padron.numero.noEncontrado","Error verifique nuevamente");
        }
        try {
            Persona persona = personaService.getByCUIT(declaracionJurada.getPersona().getIdPersona().toString());
            if(persona == null ){
                errors.rejectValue("persona.idPersona", "Persona.idPersona.noEncontrado","Error verifique nuevamente");
            }
        }
        catch (Exception e){
            errors.rejectValue("persona.idPersona", "Persona.idPersona.error","Error verifique nuevamente");
        }
    }
}
