/** @author FedeBeron * @version 1.0 */ 

package com.eBolivar.web.notificacionPadron;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.eBolivar.common.SearchObject;
import com.eBolivar.domain.NotificacionPadron;
import com.eBolivar.service.NotificacionPadronServiceImpl;
import com.eBolivar.service.TipoImpuestoServiceImpl;

@Controller
public class NotificacionPadronController  extends MultiActionController {


	private NotificacionPadronServiceImpl notificacionPadronService;
	private TipoImpuestoServiceImpl tipoImpuestoService;


	public void setTipoImpuestoService(TipoImpuestoServiceImpl tipoImpuestoService) {
		this.tipoImpuestoService = tipoImpuestoService;
	}

	public NotificacionPadronController() {
		setSupportedMethods(new String[] { METHOD_GET });
	}

	public void setNotificacionPadronService(NotificacionPadronServiceImpl notificacionPadronService) {
		this.notificacionPadronService = notificacionPadronService;
	}

	
	public ModelAndView search(HttpServletRequest req, HttpServletResponse res) {
		SearchObject search = new SearchObject();
		ModelAndView mav = new ModelAndView("notificacionPadron/list");
		String mostrar="";
		if (req.getParameter("mostrar")!=null && !req.getParameter("mostrar").equalsIgnoreCase("0")){
			mostrar=req.getParameter("mostrar");
		}
		ArrayList<String> aMostrar = new ArrayList <String>();
		
		StringTokenizer st = new StringTokenizer (mostrar,",");
		while (st.hasMoreTokens()){
			String tk = st.nextToken();
			String key = tk.substring(0,tk.indexOf(":"));
			String value = tk.substring(tk.indexOf(":")+1);
			if (value.equalsIgnoreCase("true")){
				if (aMostrar.indexOf(key)==-1)
					aMostrar.add(key);
			} else {
				int ix = aMostrar.indexOf(key);
				while (ix > -1)	{
					aMostrar.remove(ix);
					ix = aMostrar.indexOf(key);
				}
			}
		}
		mostrar="";
		for (Iterator iterator = aMostrar.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			mav.addObject(string,"on");
			mostrar+=string+":true,";
		}
		
		mav.addObject("inputMostrar",mostrar);
		mav.addObject("mostrar",aMostrar);
		
		String campo;
		if (req.getParameter("campo")!=null && !req.getParameter("campo").equalsIgnoreCase("0")){
			campo=req.getParameter("campo");
			search.setCampo(campo);
			mav.addObject("campo", campo);
		}
		String ubicacion;
		if (req.getParameter("ubicacion")!=null && !req.getParameter("ubicacion").equalsIgnoreCase("0")){
			ubicacion=req.getParameter("ubicacion");
			search.setUbicacion(ubicacion);
			mav.addObject("ubicacion", ubicacion);
		}
		String valor;
		if (req.getParameter("valor")!=null && !req.getParameter("valor").equalsIgnoreCase("0")){
			valor=req.getParameter("valor");
			search.setValor(valor);
			mav.addObject("valor", valor);
		}

		if (req.getParameter("vista")!=null){
			if (req.getParameter("vista").toString().equalsIgnoreCase("EXCEL")){
				//mav.setView(new ReporteNotificacionPadronExcelView());
			}
			else{
				int page = (req.getParameter("page")!=null&&!req.getParameter("page").equalsIgnoreCase(""))?Integer.parseInt(req.getParameter("page")):1;
				search.setPage(page);
				mav.addObject("page",page);
			}
		}
		else {
			int page = (req.getParameter("page")!=null)?Integer.parseInt(req.getParameter("page")):1;
			search.setPage(page);
			mav.addObject("page",page);
		}
		
		List notificacionPadrons = notificacionPadronService.searchObjects(search);
		if (notificacionPadrons.size()<10)
			mav.addObject("noMorePages",1);
		
		mav.addObject("notificacionPadrons", notificacionPadrons );
		 
		mav.addObject("tipos", tipoImpuestoService.getObjects());
		return mav;
	}
	
	public ModelAndView show(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mav = new ModelAndView("notificacionPadron/show");
		
		int id;
		try {
			id = Integer.parseInt(req.getParameter("idNotificacionPadron"));
		} catch (NullPointerException e) { id = 0;}
		
		
		if (req.getParameter("mensaje")!=null && !req.getParameter("mensaje").equalsIgnoreCase("0")){
			mav.addObject("mensaje", req.getParameter("mensaje"));
		}
		
		mav.addObject("notificacionPadron",  notificacionPadronService.getObject(id));
		return mav;
	}
	
	public ModelAndView delete(HttpServletRequest req, HttpServletResponse res) {
		
		int id;
		try {
			id = Integer.parseInt(req.getParameter("idNotificacionPadron"));
		} catch (NullPointerException e) { id = 0;}
		
		
		try {
			notificacionPadronService.removeObject(id);
		} catch (org.springframework.dao.DataIntegrityViolationException e) {
			NotificacionPadron notificacionPadron = notificacionPadronService.getObject(id);
			ModelAndView mav = new ModelAndView("notificacionPadron/update");
			mav.addObject("notificacionPadron", notificacionPadron);
			mav.addObject("errorMessage", "No se puede borrar");
			return mav;
		}
		ModelAndView mav = new ModelAndView("redirect:list");
		return mav;
	}
	
	public ModelAndView validarPadron(HttpServletRequest req , HttpServletResponse resp){
		ModelAndView mav = new ModelAndView();

		if (req.getParameter("idFactura")!=null && !req.getParameter("idFactura").equalsIgnoreCase("0")){
			String nroPadron = req.getParameter("idFactura").substring(3,11);
			mav.addObject("nroPadron", nroPadron);

			if(notificacionPadronService.exist(nroPadron)){
				NotificacionPadron n = notificacionPadronService.getObjetByPadron(nroPadron); 
				mav.addObject("mensaje", "Ya existe una asociacion para este Padron");
				mav.addObject("idNotificacionPadron", n.getId());
				mav.setViewName("redirect:show");	   
			}
			else mav.setViewName("redirect:create");
		}
		return mav;
	}

	
}
