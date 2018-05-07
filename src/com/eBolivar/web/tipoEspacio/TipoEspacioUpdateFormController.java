/** @author FedeBeron * @version 1.0 */

package com.eBolivar.web.tipoEspacio;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.eBolivar.domain.TipoEspacio;
import com.eBolivar.service.TipoEspacioServiceImpl;

public class TipoEspacioUpdateFormController extends SimpleFormController {

	private TipoEspacioServiceImpl tipoEspacioService;

	public void setTipoEspacioService(TipoEspacioServiceImpl tipoEspacioService) {
		this.tipoEspacioService = tipoEspacioService;
	}

	public TipoEspacioUpdateFormController() {
		setCommandName("tipoEspacio");
		setCommandClass(TipoEspacio.class);
		setFormView("tipoEspacio/update");
		setSuccessView("redirect:list");
	}

	protected ModelAndView onSubmit(Object command) throws Exception {
		TipoEspacio tipoEspacio = (TipoEspacio) command;

		try {
			tipoEspacioService.saveObject(tipoEspacio);
		} catch (org.hibernate.exception.ConstraintViolationException e) {
			e.printStackTrace();
			ModelAndView mav = new ModelAndView(getFormView());
			mav.addObject("tipoEspacio",tipoEspacio);
			mav.addObject("errorMessage", "No se puede repetir el id de TipoEspacio");
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
		TipoEspacio tipoEspacio;
				
		int id;
		try {
			id = Integer.parseInt(req.getParameter("idTipoEspacio"));
		} catch (NullPointerException e) { id = 0;}
		tipoEspacio = (TipoEspacio) tipoEspacioService.getObject(id);
		return tipoEspacio;
	}
}
