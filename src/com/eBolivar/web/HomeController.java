package com.eBolivar.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class HomeController extends AbstractController {


	
 	public ModelAndView handleRequestInternal(HttpServletRequest req, HttpServletResponse res) throws Exception {

		ModelAndView mav;
		mav = new ModelAndView("redirect:tasas/homeWeb");
 	
		return mav;

	}
	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	@ResponseBody
	public String accessDeniedPage(ModelMap model) {

		return "Access Denied";
	}

	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true));
	}

}