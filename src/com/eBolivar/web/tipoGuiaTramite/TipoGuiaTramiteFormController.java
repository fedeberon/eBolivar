/** @author JoseAlv * @version 1.0 */ 

package com.eBolivar.web.tipoGuiaTramite;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.eBolivar.domain.TipoGuiaTramite;
import com.eBolivar.service.TipoGuiaTramiteServiceImpl;

public class TipoGuiaTramiteFormController extends SimpleFormController {

	private TipoGuiaTramiteServiceImpl tipoGuiaTramiteService;

	public void setTipoGuiaTramiteService(TipoGuiaTramiteServiceImpl tipoGuiaTramiteService) {
		this.tipoGuiaTramiteService = tipoGuiaTramiteService;
	}

	public TipoGuiaTramiteFormController() {
		setCommandName("tipoGuiaTramite");
		setCommandClass(TipoGuiaTramite.class);
		setFormView("tipoGuiaTramite/create");
		setSuccessView("redirect:list");
	}

	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map  data = new HashMap();
		//data.put("entities", entityService.getObjects());
		return data;
	}

	protected ModelAndView onSubmit(Object command) throws Exception {
		TipoGuiaTramite tipoGuiaTramite = (TipoGuiaTramite) command;

		try {
			tipoGuiaTramiteService.saveObject(tipoGuiaTramite);
		} catch (org.hibernate.exception.ConstraintViolationException e) {
			e.printStackTrace();
			ModelAndView mav = new ModelAndView(getFormView());
			mav.addObject("tipoGuiaTramite",tipoGuiaTramite);
			mav.addObject("errorMessage", "No se puede repetir el id de TipoGuiaTramite");
			return mav;
		}
		ModelAndView mav = new ModelAndView(getSuccessView());
		return mav;
	}
}

