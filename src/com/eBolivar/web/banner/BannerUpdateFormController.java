/** @author FedeBeron * @version 1.0 */

package com.eBolivar.web.banner;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.eBolivar.domain.Banner;
import com.eBolivar.service.BannerServiceImpl;

public class BannerUpdateFormController extends SimpleFormController {

	private BannerServiceImpl bannerService;

	public void setBannerService(BannerServiceImpl bannerService) {
		this.bannerService = bannerService;
	}

	public BannerUpdateFormController() {
		setCommandName("banner");
		setCommandClass(Banner.class);
		setFormView("banner/update");
		setSuccessView("redirect:list");
	}

	protected ModelAndView onSubmit(Object command) throws Exception {
		Banner banner = (Banner) command;

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

	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map  data = new HashMap();
		//data.put("entities", entityService.getObjects());
		return data;
	}

	protected Object formBackingObject(HttpServletRequest req) throws Exception {
		Banner banner;
		
		Integer id = Integer.parseInt(req.getParameter("idBanner"));
				banner = (Banner) bannerService.getObject(id);
		return banner;
	}
}
