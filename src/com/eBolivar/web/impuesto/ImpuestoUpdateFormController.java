/** @author FedeBeron * @version 1.0 */

package com.eBolivar.web.impuesto;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.eBolivar.domain.Impuesto;
import com.eBolivar.service.ImpuestoServiceImpl;

public class ImpuestoUpdateFormController extends SimpleFormController {

	private ImpuestoServiceImpl impuestoService;

	public void setImpuestosService(ImpuestoServiceImpl impuestoService) {
		this.impuestoService = impuestoService;
	}

	public ImpuestoUpdateFormController() {
		setCommandName("impuesto");
		setCommandClass(Impuesto.class);
		setFormView("impuesto/update");
		setSuccessView("redirect:list");
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

	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map  data = new HashMap();
		//data.put("entities", entityService.getObjects());
		return data;
	}

	protected Object formBackingObject(HttpServletRequest req) throws Exception {
		Impuesto impuesto;
				
		String id;
		try {
			id = req.getParameter("idImpuestos");
		} catch (NullPointerException e) { id = "";}
		impuesto = (Impuesto) impuestoService.getObject(id);
		return impuesto;
	}
}
