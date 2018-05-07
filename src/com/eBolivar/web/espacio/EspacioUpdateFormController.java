/** @author FedeBeron * @version 1.0 */

package com.eBolivar.web.espacio;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.eBolivar.domain.Espacio;
import com.eBolivar.service.EspacioServiceImpl;
import com.eBolivar.service.TipoEspacioServiceImpl;

public class EspacioUpdateFormController extends SimpleFormController {

	private EspacioServiceImpl espacioService;
	private TipoEspacioServiceImpl tipoEspacioService;
	
	
	 

	public void setTipoEspacioService(TipoEspacioServiceImpl tipoEspacioService) {
		this.tipoEspacioService = tipoEspacioService;
	}

	public void setEspacioService(EspacioServiceImpl espacioService) {
		this.espacioService = espacioService;
	}

	public EspacioUpdateFormController() {
		setCommandName("espacio");
		setCommandClass(Espacio.class);
		setFormView("espacio/update");
		setSuccessView("redirect:list");
	}

	protected ModelAndView onSubmit(Object command) throws Exception {
		Espacio espacio = (Espacio) command;
		
		
		try {
			espacioService.guardarImagen(espacio);
			espacioService.saveObject(espacio);
		} catch (org.hibernate.exception.ConstraintViolationException e) {
			e.printStackTrace();
			ModelAndView mav = new ModelAndView(getFormView());
			mav.addObject("espacio",espacio);
			mav.addObject("errorMessage", "No se puede repetir el id de Espacio");
			return mav;
		}

		ModelAndView mav = new ModelAndView(getSuccessView());
		return mav;
	}



	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map  data = new HashMap();
		data.put("tipoEspacio", tipoEspacioService.getObjects());
		return data;
	}

	protected Object formBackingObject(HttpServletRequest req) throws Exception {
		Espacio espacio;
				
		int id;
		try {
			id = Integer.parseInt(req.getParameter("idEspacio"));
		} catch (NullPointerException e) { id = 0;}
		espacio = (Espacio) espacioService.getObject(id);
		return espacio;
	}
}
