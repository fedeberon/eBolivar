package com.eBolivar.validator;


import com.eBolivar.domain.PadronAsociado;
import com.eBolivar.domain.Persona;
import com.eBolivar.service.cuitPorTasa.interfaces.ICuitPorTasaService;
import com.eBolivar.service.impuesto.interfaces.IImpuestoService;
import com.eBolivar.service.persona.interfaces.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PadronAsociadoValidator implements Validator {

    @Autowired
    private IPersonaService personaService;

    @Autowired
    private IImpuestoService impuestoService;

    @Autowired
    private ICuitPorTasaService cuitPorTasaService;

    @Override
    public boolean supports(Class<?> clazz) {
        return PadronAsociado.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PadronAsociado padronAsociado = (PadronAsociado) target;

        if(!impuestoService.isUnPadron(padronAsociado.getPadron().getId().toString())){
            errors.rejectValue("padron", "Padron.numero.noEncontrado","Error verifique nuevamente");
        }

        try {
            String  cuit = padronAsociado.getPersona().getIdPersona().toString();
            Persona persona = personaService.getByCUIT(cuit);

            if(persona == null){
                errors.rejectValue("persona.idPersona", "Persona.idPersona.noEncontrado","Error verifique nuevamente");
            }
            else padronAsociado.setPersona(persona);

            if(cuitPorTasaService.exist(padronAsociado)){
                errors.rejectValue("padron", "PadronAsociado.numero.existe","Error verifique nuevamente");
            }
        }
        catch (Exception e){
            errors.rejectValue("persona.idPersona", "Persona.idPersona.error","Error verifique nuevamente");
        }
    }
}
