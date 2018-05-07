/** @author FedeBeron * @version 1.0 */ 

package com.eBolivar.web.banner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.eBolivar.common.SearchObject;
import com.eBolivar.domain.Banner;
import com.eBolivar.service.BannerServiceImpl;

public class BannerController  extends MultiActionController {

	private BannerServiceImpl bannerService;
	
	public BannerController() {
		setSupportedMethods(new String[] { METHOD_GET });
	}

	public void setBannerService(BannerServiceImpl bannerService) {
		this.bannerService = bannerService;
	}
	
	public ModelAndView list(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mav = new ModelAndView("redirect:search?mostrar=Leyenda_Principal:true,Leyenda_Secundaria:true,Observaciones:true");
		return mav;
	}
	
	public ModelAndView search(HttpServletRequest req, HttpServletResponse res) {
		SearchObject search = new SearchObject();
		ModelAndView mav = new ModelAndView("banner/list");
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
				//mav.setView(new ReporteBannerExcelView());
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
		
		List banners = bannerService.searchObjects(search);
		if (banners.size()<20)
			mav.addObject("noMorePages",1);
		
		mav.addObject("banners", banners );
		return mav;
	}
	
	public ModelAndView show(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mav = new ModelAndView("banner/show");
		Integer id = Integer.parseInt(req.getParameter("idBanner"));
		mav.addObject("banner",  bannerService.getObject(id));
		return mav;
	}
	
	public ModelAndView delete(HttpServletRequest req, HttpServletResponse res) {
		
		Integer id = Integer.parseInt(req.getParameter("idBanner"));
		try {
			bannerService.removeObject(id);
		} catch (org.springframework.dao.DataIntegrityViolationException e) {
			Banner banner = (Banner) bannerService.getObject(id);
			ModelAndView mav = new ModelAndView("banner/update");
			mav.addObject("banner", banner);
			mav.addObject("errorMessage", "No se puede borrar");
			return mav;
		}
		ModelAndView mav = new ModelAndView("redirect:list");
		return mav;
	}
}
