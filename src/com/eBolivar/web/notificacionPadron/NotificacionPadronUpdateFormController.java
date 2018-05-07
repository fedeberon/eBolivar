/** @author FedeBeron * @version 1.0 */

package com.eBolivar.web.notificacionPadron;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.eBolivar.domain.NotificacionPadron;
import com.eBolivar.service.ConsultasServiceImpl;
import com.eBolivar.service.NotificacionPadronServiceImpl;

public class NotificacionPadronUpdateFormController extends SimpleFormController {

	private NotificacionPadronServiceImpl notificacionPadronService;

	public void setNotificacionPadronService(NotificacionPadronServiceImpl notificacionPadronService) {
		this.notificacionPadronService = notificacionPadronService;
	}

	public NotificacionPadronUpdateFormController() {
		setCommandName("notificacionPadron");
		setCommandClass(NotificacionPadron.class);
		setFormView("notificacionPadron/update");
		setSuccessView("redirect:list");
	}

	@Override
	protected ModelAndView onSubmit(Object command) throws Exception {
		NotificacionPadron notificacionPadron = (NotificacionPadron) command;
		ModelAndView mav = new ModelAndView();
		ConsultasServiceImpl consultasServ = new ConsultasServiceImpl();
		try {
			notificacionPadronService.updateObject(notificacionPadron);
			mav.addObject("mensaje", "Su solicitud ha sido procesada. Un operador se pondra en contacto con Ud y luego el tramite estara finalizado");
 				
		} catch (org.hibernate.exception.ConstraintViolationException e) {
			e.printStackTrace();
			mav = new ModelAndView(getFormView());
			mav.addObject("notificacionPadron",notificacionPadron);
			mav.addObject("mensaje", "No se ha podido guardar la Inscripcion. Intente de Nuevo");
			return mav;
		}

		return new ModelAndView("redirect:show?idNotificacionPadron="+notificacionPadron.getId());
	}

	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map  data = new HashMap();
		//data.put("entities", entityService.getObjects());
		return data;
	}

	@Override
	protected Object formBackingObject(HttpServletRequest req) throws Exception {
		NotificacionPadron notificacionPadron;
				
		int id;
		try {
			id = Integer.parseInt(req.getParameter("idNotificacionPadron"));
		} catch (NullPointerException e) { id = 0;}
		notificacionPadron = notificacionPadronService.getObject(id);
		return notificacionPadron;
	}
	
	@Override
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("dd/MM/yyyy"), true));
	}
}
