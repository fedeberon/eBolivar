package com.eBolivar.service;

import com.eBolivar.dao.ConsultasDaoHinernateImpl;
import com.eBolivar.domain.NotificacionPadron;
import com.eBolivar.domain.Reclamos;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("consultasService")
public class ConsultasServiceImpl {

	ConsultasDaoHinernateImpl dao = null;

	public ConsultasServiceImpl() {
		dao = (ConsultasDaoHinernateImpl) PersistenceContextSingleton.getInstance().getBean("consultaDao");
	}

	public List obtenerReclamosPorDia() {
		return dao.obtenerReclamosPorDia();
	}

	public List obtenerReclamosPorArea() {
		return dao.obtenerReclamosPorArea();
	}

	public List obtenerReclamosPorCalle() {
		return dao.obtenerReclamosPorCalle();
	}

	public List obtenerReclamosPorUsuario() {
		return dao.obtenerReclamosPorUsuario();
	}

	public List obtenerReclamosPorParametrosDeFecha(String fechaInicial,
			String fechaFinal) {
		return dao
				.obtenerReclamosPorParametrosDeFecha(fechaInicial, fechaFinal);
	}

	public List obtenerReclamosPorAreaPorFecha(Date fechaDesde, Date fechaHasta) {
		return dao.obtenerReclamosPorAreaPorFecha(fechaDesde, fechaHasta);
	}

	public List obtenerReclamosPorBarrioPorFecha(String fechaDesde,
			String fechaHasta) {
		return dao.obtenerReclamosPorBarrioPorFecha(fechaDesde, fechaHasta);
	}

	public List obtenerReclamosPorCallesPorFecha(String fechaDesde,
			String fechaHasta) {
		return dao.obtenerReclamosPorCallesPorFecha(fechaDesde, fechaHasta);
	}
	
	public JasperPrint crearPrint(JasperReport jr, Map<String , Object> mapa){
		try {
            return dao.crearPrint(jr, mapa);
        }
        catch (JRException e){
            e.printStackTrace();
            return null;
        }
	}
	
	public int insertarReclamo(String nombre,String apellido , String email,String descripcion){
		 return dao.insertarReclamo( nombre, apellido , email , descripcion );
	}
	
	public void insertarReclamo(Reclamos reclamo) throws Exception{
		 dao.insertarReclamo(reclamo);
	}
	
	public Integer insertNotificacion(NotificacionPadron n){
		return dao.insertNotificacion(n);
	}


}
