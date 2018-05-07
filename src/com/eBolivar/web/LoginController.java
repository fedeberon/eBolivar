/** @author Fede Beron* @version 1.0 */ 
package com.eBolivar.web;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class LoginController extends MultiActionController {

	public LoginController() {
		setSupportedMethods(new String[] { METHOD_GET });
		Locale.setDefault(new Locale("es", "ES"));
	}
	
	public ModelAndView login (HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("login_error", req.getParameter("login_error"));
		return mav;
	}
	
	public ModelAndView logout (HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mav = new ModelAndView("redirect:/webapp/");
		HttpSession session = req.getSession(true);
		session.invalidate();
		return mav;
	}
}