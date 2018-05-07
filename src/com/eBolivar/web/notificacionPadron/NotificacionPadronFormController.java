/** @author Fede Beron * @version 1.0 */ 

package com.eBolivar.web.notificacionPadron;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.eBolivar.domain.NotificacionPadron;
import com.eBolivar.service.NotificacionPadronServiceImpl;
import com.eBolivar.service.TipoImpuestoServiceImpl;
import com.eBolivar.validator.NotificacionPadronValidator;

public class NotificacionPadronFormController extends SimpleFormController {

	private NotificacionPadronServiceImpl notificacionPadronService;
	private TipoImpuestoServiceImpl tipoImpuestoService;
	
	

	public void setTipoImpuestoService(TipoImpuestoServiceImpl tipoImpuestoService) {
		this.tipoImpuestoService = tipoImpuestoService;
	}

	public void setNotificacionPadronService(NotificacionPadronServiceImpl notificacionPadronService) {
		this.notificacionPadronService = notificacionPadronService;
	}

	public NotificacionPadronFormController() {
		setCommandName("notificacionPadron");
		setCommandClass(NotificacionPadron.class);
		setFormView("notificacionPadron/create");
		setSuccessView("redirect:list");
		setValidator(new  NotificacionPadronValidator());
	}

	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map  data = new HashMap();
		if (request.getParameter("nroPadron")!=null && !request.getParameter("nroPadron").equalsIgnoreCase("0")){
			data.put("nroPadron", request.getParameter("nroPadron"));
		}

		data.put("tipos", tipoImpuestoService.getObjects());
		
		return data;
		
	}

	
	@Override
	protected ModelAndView onSubmit(Object command) throws Exception {
		NotificacionPadron notificacionPadron = (NotificacionPadron) command;
		ModelAndView mav = new ModelAndView();
		try {
			notificacionPadron.setFechaAlta(new Date());
			notificacionPadronService.saveObject(notificacionPadron);
			mav.addObject("mensaje", "Su solicitud ha sido procesada. Un operador se pondra en contacto con Ud y luego el tramite estara finalizado");

		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("notificacionPadron",notificacionPadron);
			mav.addObject("mensaje", "No se pudo guardar. Intente de nuevo.");
			return mav;
		}
		mav.setViewName("notificacionPadron/notificaciones");
		return mav ;
	}
	
	 
}

