package com.eBolivar.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.eBolivar.common.ValidarEmail;
import com.eBolivar.domain.Reclamos;

public class ReclamoValidator implements Validator {
	
		public boolean supports(Class clazz) {
			return Reclamos.class.isAssignableFrom(clazz);
		}

		public void validate(Object target, Errors errors) {
			Reclamos reclamo = (Reclamos) target;
			

			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "nombre.req", "Debe ingresar un Nombre");

			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "apellido", "nombre.req", "Debe ingresar un Apellido");


			 if(reclamo.geteMail()!=null && !reclamo.geteMail().equalsIgnoreCase("") && !ValidarEmail.validateEmail(reclamo.geteMail())){  
				 
				 errors.rejectValue("eMail", "email.req","El eMail no es valido.");
			 
			 }
			
			 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telefonoCelular", "telefonoCelular.req", "Debe ingresar un numero de Telefono.");

			 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dni", "dni.req", "Debe ingresar un numero de DNI.");

			 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ubicacion", "ubicacion.req", "Debe ingresar una ubicacion.");

			 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numero", "numero.req", "Debe ingresar una numero.");

			 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "reclamo", "reclamo.req", "Debe ingresar una descripcion del Reclamo.");

		 
		}
	
	
}
