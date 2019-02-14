/** @author Fede Beron * @version 1.0 */ 

package com.eBolivar.web.banner;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.eBolivar.domain.Banner;
import com.eBolivar.service.BannerServiceImpl;

public class BannerFormController extends SimpleFormController {

	private BannerServiceImpl bannerService;

	public void setBannerService(BannerServiceImpl bannerService) {
		this.bannerService = bannerService;
	}

	public BannerFormController() {
		setCommandName("banner");
		setCommandClass(Banner.class);
		setFormView("banner/create");
		setSuccessView("redirect:list");
	}

	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map  data = new HashMap();
		//data.put("entities", entityService.getObjects());
		return data;
	}

	@Override
	protected ModelAndView onSubmit(Object command) throws Exception {
		Banner banner = (Banner) command;

		  MultipartFile file = banner.getFile();
		  String destinationFolder = "/documentos/banners/";
	      File destination = new File(destinationFolder);
	        
	        if (!destination.exists()){
	        	destination.mkdirs();
	        }
	      
	      String ubicacion = destinationFolder+banner.getFile().getOriginalFilename();
	        
	      File destinationFile = new File(ubicacion); 
	        
	      int count = 1;
	        
	        while(destinationFile.exists()){
	        	
	        	 destinationFile = new File(ubicacion+"-Version_"+count);
	        	 count++;
	        }
	        
	        file.transferTo(destinationFile);
	        banner.setDireccion_img("/documentos/banners/"+destinationFile.getName());
		
		try {
			bannerService.saveObject(banner);
		} catch (org.hibernate.exception.ConstraintViolationException e) {
			e.printStackTrace();
			ModelAndView mav = new ModelAndView(getFormView());
			mav.addObject("banner",banner);
			mav.addObject("errorMessage", "No se puede repetir el  de Banner");
			return mav;
		}
		ModelAndView mav = new ModelAndView(getSuccessView());
		return mav;
	}
}

