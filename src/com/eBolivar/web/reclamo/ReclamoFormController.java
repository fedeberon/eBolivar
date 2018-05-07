package com.eBolivar.web.reclamo;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.eBolivar.domain.Reclamos;
import com.eBolivar.service.ConsultasServiceImpl;
import com.eBolivar.service.DepartamentosServiceImpl;
import com.eBolivar.service.ReclamoServicesImpl;
import com.eBolivar.service.TipoImpuestoServiceImpl;
import com.eBolivar.validator.ReclamoValidator;

public class ReclamoFormController extends SimpleFormController{
	
	DepartamentosServiceImpl departamentosService;
	TipoImpuestoServiceImpl tipoImpuestoService;
	ReclamoServicesImpl reclamoServices;
	

	public void setReclamoServices(ReclamoServicesImpl reclamoServices) {
		this.reclamoServices = reclamoServices;
	}

	public void setTipoImpuestoService(TipoImpuestoServiceImpl tipoImpuestoService) {
		this.tipoImpuestoService = tipoImpuestoService;
	}

	public void setDepartamentosService(
			DepartamentosServiceImpl departamentosService) {
		this.departamentosService = departamentosService;
	}

	public ReclamoFormController() {
		setCommandName("reclamo");
		setCommandClass(Reclamos.class);
		setFormView("touch/nuevoReclamo");
		setSuccessView("redirect:inicio");
		setValidator(new ReclamoValidator());
	}

	@SuppressWarnings("unchecked")
	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map  data = new HashMap();
		//data.put("entities", entityService.getObjects());
		data.put("isScreenTouch", true);
		data.put("departamentos", departamentosService.obtenerListaOrdenadaDeCiudades());
		data.put("tipos", tipoImpuestoService.getObjects());
		return data;
	}

 	protected ModelAndView onSubmit(Object command) throws Exception {
		Reclamos reclamo = (Reclamos) command;
			ModelAndView mav = new ModelAndView("touch/notificaciones");

			reclamo = reclamoServices.saveReclamos(reclamo);
			
			if(reclamo != null){
			mav.addObject("mensaje", "Su solicitud ha sido procesada.Su numero de Reclamo es el " + reclamo.getCodigo() + "."
					+ " Un operador se pondra en contacto con Ud y luego el tramite estara finalizado.");
			}
			else{		
			mav.addObject("mensaje", "No se pudo Guardar el Reclamo");
			}
			
			
			mav.addObject("isScreenTouch", true);
			
			return mav;
		}
 


}

