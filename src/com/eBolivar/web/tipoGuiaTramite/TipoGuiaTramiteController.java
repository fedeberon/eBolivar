/** @author FedeBeron * @version 1.0 */ 

package com.eBolivar.web.tipoGuiaTramite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.eBolivar.common.SearchObject;
import com.eBolivar.domain.TipoGuiaTramite;
import com.eBolivar.service.TipoGuiaTramiteServiceImpl;
//import com.eBolivar.web.excel.ReporteTipoGuiaTramiteExcelView;

public class TipoGuiaTramiteController  extends MultiActionController {

	private TipoGuiaTramiteServiceImpl tipoGuiaTramiteService;
	
	public TipoGuiaTramiteController() {
		setSupportedMethods(new String[] { METHOD_GET });
	}

	public void setTipoGuiaTramiteService(TipoGuiaTramiteServiceImpl tipoGuiaTramiteService) {
		this.tipoGuiaTramiteService = tipoGuiaTramiteService;
	}
	
	public ModelAndView list(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mav = new ModelAndView("redirect:search?mostrar=codigo:true,nombre:true");
		return mav;
	}
	
	public ModelAndView search(HttpServletRequest req, HttpServletResponse res) {
		SearchObject search = new SearchObject();
		ModelAndView mav = new ModelAndView("tipoGuiaTramite/list");
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
				//mav.setView(new ReporteTipoGuiaTramiteExcelView());
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
		
		List tipoGuiaTramites = tipoGuiaTramiteService.searchObjects(search);
		if (tipoGuiaTramites.size()<20)
			mav.addObject("noMorePages",1);
		
		mav.addObject("tipoGuiaTramites", tipoGuiaTramites );
		return mav;
	}
	
	public ModelAndView show(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mav = new ModelAndView("tipoGuiaTramite/show");
		
		int id;
		try {
			id = Integer.parseInt(req.getParameter("idTipoGuiaTramite"));
		} catch (NullPointerException e) { id = 0;}
		
		mav.addObject("tipoGuiaTramite",  tipoGuiaTramiteService.getObject(id));
		return mav;
	}
	
	public ModelAndView delete(HttpServletRequest req, HttpServletResponse res) {
		
		int id;
		try {
			id = Integer.parseInt(req.getParameter("idTipoGuiaTramite"));
		} catch (NullPointerException e) { id = 0;}
		
		
		try {
			tipoGuiaTramiteService.removeObject(id);
		} catch (org.springframework.dao.DataIntegrityViolationException e) {
			TipoGuiaTramite tipoGuiaTramite = (TipoGuiaTramite) tipoGuiaTramiteService.getObject(id);
			ModelAndView mav = new ModelAndView("tipoGuiaTramite/update");
			mav.addObject("tipoGuiaTramite", tipoGuiaTramite);
			mav.addObject("errorMessage", "No se puede borrar");
			return mav;
		}
		ModelAndView mav = new ModelAndView("redirect:list");
		return mav;
	}
}
