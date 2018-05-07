/** @author FedeBeron * @version 1.0 */ 

package com.eBolivar.web.pasoGuiaTramite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.eBolivar.common.SearchObject;
import com.eBolivar.domain.PasoGuiaTramite;
import com.eBolivar.service.PasoGuiaTramiteServiceImpl;
//import com.eBolivar.web.excel.ReportePasoGuiaTramiteExcelView;

public class PasoGuiaTramiteController  extends MultiActionController {

	private PasoGuiaTramiteServiceImpl pasoGuiaTramiteService;
	
	public PasoGuiaTramiteController() {
		setSupportedMethods(new String[] { METHOD_GET });
	}

	public void setPasoGuiaTramiteService(PasoGuiaTramiteServiceImpl pasoGuiaTramiteService) {
		this.pasoGuiaTramiteService = pasoGuiaTramiteService;
	}
	
	public ModelAndView list(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mav = new ModelAndView("redirect:search?mostrar=:true");
		return mav;
	}
	
	public ModelAndView search(HttpServletRequest req, HttpServletResponse res) {
		SearchObject search = new SearchObject();
		ModelAndView mav = new ModelAndView("pasoGuiaTramite/list");
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
				//mav.setView(new ReportePasoGuiaTramiteExcelView());
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
		
		List pasoGuiaTramites = pasoGuiaTramiteService.searchObjects(search);
		if (pasoGuiaTramites.size()<20)
			mav.addObject("noMorePages",1);
		
		mav.addObject("pasoGuiaTramites", pasoGuiaTramites );
		return mav;
	}
	
	public ModelAndView show(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mav = new ModelAndView("pasoGuiaTramite/show");
		
		int id;
		try {
			id = Integer.parseInt(req.getParameter("idPasoGuiaTramite"));
		} catch (NullPointerException e) { id = 0;}
		
		mav.addObject("pasoGuiaTramite",  pasoGuiaTramiteService.getObject(id));
		return mav;
	}
	
	public ModelAndView delete(HttpServletRequest req, HttpServletResponse res) {
		
		int id;
		try {
			id = Integer.parseInt(req.getParameter("idPasoGuiaTramite"));
		} catch (NullPointerException e) { id = 0;}
		
		
		try {
			pasoGuiaTramiteService.removeObject(id);
		} catch (org.springframework.dao.DataIntegrityViolationException e) {
			PasoGuiaTramite pasoGuiaTramite = (PasoGuiaTramite) pasoGuiaTramiteService.getObject(id);
			ModelAndView mav = new ModelAndView("pasoGuiaTramite/update");
			mav.addObject("pasoGuiaTramite", pasoGuiaTramite);
			mav.addObject("errorMessage", "No se puede borrar");
			return mav;
		}
		ModelAndView mav = new ModelAndView("redirect:list");
		return mav;
	}
}
