/** @author JoseAlv * @version 1.0 */ 

package com.eBolivar.web.pasoGuiaTramite;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.eBolivar.domain.PasoGuiaTramite;
import com.eBolivar.service.PasoGuiaTramiteServiceImpl;

public class PasoGuiaTramiteFormController extends SimpleFormController {

	private PasoGuiaTramiteServiceImpl pasoGuiaTramiteService;

	public void setPasoGuiaTramiteService(PasoGuiaTramiteServiceImpl pasoGuiaTramiteService) {
		this.pasoGuiaTramiteService = pasoGuiaTramiteService;
	}

	public PasoGuiaTramiteFormController() {
		setCommandName("pasoGuiaTramite");
		setCommandClass(PasoGuiaTramite.class);
		setFormView("pasoGuiaTramite/create");
		setSuccessView("redirect:list");
	}

	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map  data = new HashMap();
		//data.put("entities", entityService.getObjects());
		return data;
	}

	protected ModelAndView onSubmit(Object command) throws Exception {
		PasoGuiaTramite pasoGuiaTramite = (PasoGuiaTramite) command;

		try {
			pasoGuiaTramiteService.saveObject(pasoGuiaTramite);
		} catch (org.hibernate.exception.ConstraintViolationException e) {
			e.printStackTrace();
			ModelAndView mav = new ModelAndView(getFormView());
			mav.addObject("pasoGuiaTramite",pasoGuiaTramite);
			mav.addObject("errorMessage", "No se puede repetir el id de PasoGuiaTramite");
			return mav;
		}
		ModelAndView mav = new ModelAndView(getSuccessView());
		return mav;
	}
}

