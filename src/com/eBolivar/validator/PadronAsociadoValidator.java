package com.eBolivar.validator;

import com.eBolivar.domain.Padron;
import com.eBolivar.domain.PadronAsociado;
import com.eBolivar.service.padron.interfaces.IPadronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PadronAsociadoValidator implements Validator {
    @Autowired
    private IPadronService padronService;

    public PadronAsociadoValidator() {
    }

    public boolean supports(Class<?> clazz) {
        return PadronAsociado.class.equals(clazz);
    }

    public void validate(Object target, Errors errors) {
        PadronAsociado padronAsociado = (PadronAsociado)target;
        Padron padron = padronAsociado.getPadron().getNumero() != null?this.padronService.getByNumero(padronAsociado.getPadron().getNumero()):null;
        padronAsociado.setPadron(padron);
        if(padron == null) {
            errors.rejectValue("padron", "Padron.numero.noEncontrado", "El numero de padron no existe.");
        }

    }
}