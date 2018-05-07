package com.eBolivar.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.eBolivar.domain.Espacio;
import com.eBolivar.service.EspacioServiceImpl;

/**
 * Servlet implementation class JsonServlet
 */

public class JsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EspacioServiceImpl  espacioService;
	private ApplicationContext context = null;
	 
	 
	 private void obtenerContextoYServicios() {
	    	if (context != null) return;
	    	context = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
	        if (context != null) {
	        	espacioService = (EspacioServiceImpl) context.getBean("espacioService");
	        }
	    }

    public JsonServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();	
		obtenerContextoYServicios();
		
		JSONObject jLugar =  new JSONObject();
		JSONArray jLugares = new JSONArray();

		jLugar.put("title", "LUGARES");
		jLugar.put("hideCheckbox", true );
		jLugar.put("expanded", true );
		
		jLugar.put("icon", "/img/logo.ico");
		
		JSONObject jObra = espacioService.obtenerLugares(Espacio.OBRAS);
		jLugares.add(jObra);


		JSONObject jActividad = espacioService.obtenerLugares(Espacio.ACTIVIDAD);
		jLugares.add(jActividad);
		
		jLugar.put("children", jLugares);
		
		response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;UTF-8");
        mapper.writeValue(response.getOutputStream(), jLugar );
		
	}
	
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
