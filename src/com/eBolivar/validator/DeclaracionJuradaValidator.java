package com.eBolivar.validator;

import com.eBolivar.domain.DeclaracionJurada;
import com.eBolivar.domain.Tasa;
import com.eBolivar.domain.TasaAsociada;
import com.eBolivar.service.impuesto.interfaces.IImpuestoService;
import com.eBolivar.service.padron.interfaces.IPadronService;
import com.eBolivar.service.persona.interfaces.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.stream.IntStream;

/**
 * Created by Fede Beron on 10/7/2017.
 */
@Component
public class DeclaracionJuradaValidator implements Validator {

    @Autowired
    private IPersonaService personaService;

    @Autowired
    private IImpuestoService impuestoService;

    @Autowired
    private IPadronService padronService;


    @Override
    public boolean supports(Class<?> clazz) {
        return DeclaracionJurada.class.equals(clazz);
    }


    @Override
    public void validate(Object target, Errors errors) {
        DeclaracionJurada declaracionJurada = (DeclaracionJurada) target;

        if(declaracionJurada.getTasas().size() == 0){
            errors.rejectValue("tasas[0].tasa.id", "DeclaracionJurada.TasaAsociada.listaVacia","Error verifique nuevamente");
        }
        else {
            declaracionJurada.getTasas().forEach(tasaAsociada -> {
                if(tasaAsociada.getBaseImponible() < 1){
                    errors.rejectValue("baseImponible", "DeclaracionJurada.baseImponible.importesNoValidos","Error verifique nuevamente");
                }
            });
        }

        if(padronService.getByNumero(declaracionJurada.getPadron().getNumero()) == null){
            errors.rejectValue("padron.numero", "Padron.numero.noEncontrado","Error verifique nuevamente");
        }


        try {
            String  cuit = declaracionJurada.getPersona().getIdPersona().toString();

            if(personaService.getByCUIT(cuit) == null ){
                errors.rejectValue("persona.idPersona", "Persona.idPersona.noEncontrado","Error verifique nuevamente");
            }
        }
        catch (Exception e){
            errors.rejectValue("persona.idPersona", "Persona.idPersona.error","Error verifique nuevamente");
        }
    }
}
