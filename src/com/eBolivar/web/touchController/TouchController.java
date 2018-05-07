package com.eBolivar.web.touchController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.eBolivar.domain.TipoImpuesto;
import com.eBolivar.service.BannerServiceImpl;
import com.eBolivar.service.DepartamentosServiceImpl;
import com.eBolivar.service.GuiaTramiteServiceImpl;
import com.eBolivar.service.TipoImpuestoServiceImpl;

public class TouchController extends MultiActionController {

	BannerServiceImpl bannerService ;
	TipoImpuestoServiceImpl tipoImpuestoService;
	DepartamentosServiceImpl departamentosService;
	private GuiaTramiteServiceImpl guiaTramiteService;
	
	public void setDepartamentosService(
			DepartamentosServiceImpl departamentosService) {
		this.departamentosService = departamentosService;
	}
	 
	public void setGuiaTramiteService(GuiaTramiteServiceImpl guiaTramiteService) {
		this.guiaTramiteService = guiaTramiteService;
	}

	public void setIsScreenTouch(Boolean isScreenTouch) {
		this.isScreenTouch = isScreenTouch;
	}

	Boolean isScreenTouch = true;
	
	public void setBannerService(BannerServiceImpl bannerService) {
		this.bannerService = bannerService;
	}

	public void setTipoImpuestoService(TipoImpuestoServiceImpl tipoImpuestoService) {
		this.tipoImpuestoService = tipoImpuestoService;
	}

	public TouchController() {
		setSupportedMethods(new String[] { METHOD_GET , METHOD_POST});
	}
	
	public ModelAndView menuImpuestos(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mav = new ModelAndView("touch/menuImpuestos");
		
		mav.addObject("tipos", tipoImpuestoService.getObjects());
		mav.addObject("banners", bannerService.getObjects());
		mav.addObject("isScreenTouch", this.isScreenTouch);
		
		return mav;
	}

	public ModelAndView inicio(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mav = new ModelAndView("touch/inicio");
		
		mav.addObject("isScreenTouch", this.isScreenTouch);
		
		return mav;
	}

	public ModelAndView consultaReclamo(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mav = new ModelAndView("touch/consultaReclamo");
		
		mav.addObject("isScreenTouch", this.isScreenTouch);
		
		return mav;
	}
	
	public ModelAndView menuGuiaDeTramites(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mav = new ModelAndView("touch/menuGuiaDeTramite");
		
		mav.addObject("isScreenTouch", this.isScreenTouch);

		mav.addObject("guiaTramites", guiaTramiteService.getObjects());
		mav.addObject("tiposGuiaTramites", guiaTramiteService.getTipoTramitesObjects());
		
		
		return mav;
	}
	
	public ModelAndView impuestos(HttpServletRequest req,
			HttpServletResponse res) {
		ModelAndView mav = new ModelAndView("touch/impuestos");

		String tipoImpuesto = req.getParameter("tipoImpuesto");

		List<TipoImpuesto> tiposDeImpuestos = tipoImpuestoService.getObjects();

		mav.addObject("tipos", tiposDeImpuestos);
		mav.addObject("isScreenTouch", true);

		int codigo = (tipoImpuesto != null && !tipoImpuesto.equalsIgnoreCase("") ? Integer.parseInt(tipoImpuesto) : 0);

		for (int i = 0; i < tiposDeImpuestos.size(); i++) {

			if (tiposDeImpuestos.get(i).getCodigo() == codigo) {
				mav.addObject("tipo", tiposDeImpuestos.get(i));
				break;
			}
		}
 
		return mav;

	}
	
}
