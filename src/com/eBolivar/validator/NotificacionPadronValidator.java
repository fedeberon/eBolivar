/** @author FedeBeron * @version 1.0 */

package com.eBolivar.validator;

import com.eBolivar.domain.NotificacionPadron;
import com.eBolivar.domain.Padron;
import com.eBolivar.service.padron.PadronService;
import com.eBolivar.service.padron.interfaces.IPadronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class NotificacionPadronValidator implements Validator {

    @Autowired
    private PadronService padronService;

	@Override
	public boolean supports(Class clazz) {
		return NotificacionPadron.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		NotificacionPadron notif = (NotificacionPadron) target;

        Padron padron = padronService.getByNumero(notif.getPadron());

        if(padron == null){
            errors.rejectValue("padron", "padron.req","No existe el padron solicitado.");
        }
		
		 ValidationUtils.rejectIfEmpty(errors, "direccionEnvio", "direccionEnvio.req","Ingrese una direccion de mail.");
		 
		 ValidationUtils.rejectIfEmpty(errors, "nombreApellido", "nombreApellido.req","Ingrese Nombre y Apellido.");
		 
		 ValidationUtils.rejectIfEmpty(errors, "dni", "dni.req","Ingrese su D.N.I.");
		 
		 ValidationUtils.rejectIfEmpty(errors, "padron", "padron.req","Ingrese un Numero de Padron");

		 if(notif.getTelefono()!= null && notif.getTelefono().length() < 5)  errors.rejectValue("telefono", "telefono.req","No es telefono valido.");
		 
		 ValidationUtils.rejectIfEmpty(errors, "telefono", "telefono.req","Ingrese un Numero de Telefono");
	}
	
}
