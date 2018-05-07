package com.eBolivar.web.servlet.json;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.eBolivar.dao.EspacioDaoHibernateImpl;
import com.eBolivar.service.PersistenceContextSingleton;

/**
 * Servlet implementation class MapasJsonServlet
 */
public class MapasJsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private EspacioDaoHibernateImpl  dao = null;

    /** @see HttpServlet#HttpServlet() */
    public MapasJsonServlet() {
        super();
    	dao = (EspacioDaoHibernateImpl) PersistenceContextSingleton.getInstance().getBean("espacioDao");
    }

 

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();	
        Object lista = null;

        Integer tipo = Integer.parseInt(request.getParameter("tipo"));
        
        if(request.getParameter("area") != null && !request.getParameter("area").equalsIgnoreCase("")){
        	String area = request.getParameter("area");
        	lista = dao.obtenerEspacios(tipo,area);
        }
        
        else lista = dao.obtenerEspacios(tipo);
        
        
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;UTF-8");
        mapper.writeValue(response.getOutputStream(), lista);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
