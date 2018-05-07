/** @author FedeBeron * @version 1.0 */

package com.eBolivar.web.guiaTramite;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.eBolivar.domain.GuiaTramite;
import com.eBolivar.service.GuiaTramiteServiceImpl;

public class GuiaTramiteUpdateFormController extends SimpleFormController {

	private GuiaTramiteServiceImpl guiaTramiteService;

	public void setGuiaTramiteService(GuiaTramiteServiceImpl guiaTramiteService) {
		this.guiaTramiteService = guiaTramiteService;
	}

	public GuiaTramiteUpdateFormController() {
		setCommandName("guiaTramite");
		setCommandClass(GuiaTramite.class);
		setFormView("guiaTramite/update");
		setSuccessView("redirect:list");
	}

	protected ModelAndView onSubmit(Object command) throws Exception {
		GuiaTramite guiaTramite = (GuiaTramite) command;

		try {
			guiaTramiteService.saveObject(guiaTramite);
		} catch (org.hibernate.exception.ConstraintViolationException e) {
			e.printStackTrace();
			ModelAndView mav = new ModelAndView(getFormView());
			mav.addObject("guiaTramite",guiaTramite);
			mav.addObject("errorMessage", "No se puede repetir el id de GuiaTramite");
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
		GuiaTramite guiaTramite;
				
		int id;
		try {
			id = Integer.parseInt(req.getParameter("idGuiaTramite"));
		} catch (NullPointerException e) { id = 0;}
		guiaTramite = (GuiaTramite) guiaTramiteService.getObject(id);
		return guiaTramite;
	}
}
