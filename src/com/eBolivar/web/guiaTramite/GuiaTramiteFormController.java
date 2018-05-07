/** @author JoseAlv * @version 1.0 */ 

package com.eBolivar.web.guiaTramite;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.eBolivar.domain.GuiaTramite;
import com.eBolivar.domain.PasoGuiaTramite;
import com.eBolivar.service.BannerServiceImpl;
import com.eBolivar.service.GuiaTramiteServiceImpl;
import com.eBolivar.service.TipoGuiaTramiteServiceImpl;
import com.eBolivar.service.TipoImpuestoServiceImpl;

public class GuiaTramiteFormController extends SimpleFormController {

	private GuiaTramiteServiceImpl guiaTramiteService;
	private TipoGuiaTramiteServiceImpl tipoGuiaTramiteService;
	private TipoImpuestoServiceImpl tipoImpuestoService;
	
	

	public void setTipoImpuestoService(TipoImpuestoServiceImpl tipoImpuestoService) {
		this.tipoImpuestoService = tipoImpuestoService;
	}

	public void setTipoGuiaTramiteService(
			TipoGuiaTramiteServiceImpl tipoGuiaTramiteService) {
		this.tipoGuiaTramiteService = tipoGuiaTramiteService;
	}

	public void setGuiaTramiteService(GuiaTramiteServiceImpl guiaTramiteService) {
		this.guiaTramiteService = guiaTramiteService;
	}

	public GuiaTramiteFormController() {
		setCommandName("guiaTramite");
		setCommandClass(GuiaTramite.class);
		setFormView("guiaTramite/create");
		setSuccessView("redirect:list");
	}

	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map  data = new HashMap();
		//data.put("entities", entityService.getObjects());
		data.put("tiposGuias", tipoGuiaTramiteService.getObjects());
		data.put("tipos", tipoImpuestoService.getObjects());
		return data;
	}

	protected ModelAndView onSubmit(HttpServletRequest req,
            HttpServletResponse response, Object command, BindException errors) throws Exception {
		GuiaTramite guiaTramite = (GuiaTramite) command;

		try {

			Enumeration<String> funciones = req.getParameterNames();
			List<PasoGuiaTramite> pasos = new ArrayList<PasoGuiaTramite>();
			
			/**
			while (funciones.hasMoreElements()) {

				String funcion = funciones.nextElement();
				System.out.println(funcion);
				System.out.println(req.getParameter(funcion));

				if (funcion.startsWith("PASO_")) {
					PasoGuiaTramite paso = new PasoGuiaTramite();
					pasos.add(paso);
				}
			}*/
			
			int pasosIndex = Integer.parseInt(req.getParameter("pasosIndex"));
			
			for (int i = 1; i < (pasosIndex + 1); i++) {
				
				PasoGuiaTramite paso = new PasoGuiaTramite();
				paso.setNombre(req.getParameter("PASO"+i));
				paso.setDescripcion(req.getParameter("DESCRIPCION"+i));
				paso.setUrlImg(req.getParameter("urlImg"+i));
				
				pasos.add(paso);
			}
			
			guiaTramite.setPasos(pasos);

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
}

