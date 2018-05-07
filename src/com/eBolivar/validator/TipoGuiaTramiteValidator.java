/** @author FedeBeron * @version 1.0 */

package com.eBolivar.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.eBolivar.domain.TipoGuiaTramite;

public class TipoGuiaTramiteValidator implements Validator {
	public boolean supports(Class clazz) {
		return TipoGuiaTramite.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "Id", "Id.req",
				"Debe ingresar un Id");
	}
}
