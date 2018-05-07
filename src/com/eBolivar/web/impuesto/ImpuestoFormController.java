/** @author Fede Beron * @version 1.0 */ 

package com.eBolivar.web.impuesto;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.eBolivar.domain.Impuesto;
import com.eBolivar.service.ImpuestoServiceImpl;

public class ImpuestoFormController extends SimpleFormController {

	private ImpuestoServiceImpl impuestoService;

	public void setImpuestosService(ImpuestoServiceImpl impuestoService) {
		this.impuestoService = impuestoService;
	}

	public ImpuestoFormController() {
		setCommandName("impuesto");
		setCommandClass(Impuesto.class);
		setFormView("impuesto/create");
		setSuccessView("redirect:list");
	}

	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map  data = new HashMap();
		//data.put("entities", entityService.getObjects());
		return data;
	}

	protected ModelAndView onSubmit(Object command) throws Exception {
		Impuesto impuesto = (Impuesto) command;

		try {
			impuestoService.saveObject(impuesto);
		} catch (org.hibernate.exception.ConstraintViolationException e) {
			e.printStackTrace();
			ModelAndView mav = new ModelAndView(getFormView());
			mav.addObject("impuesto",impuesto);
			mav.addObject("errorMessage", "No se puede repetir el id de Impuestos");
			return mav;
		}
		ModelAndView mav = new ModelAndView(getSuccessView());
		return mav;
	}
}

