package com.eBolivar.dao;

import com.eBolivar.common.Conexion;
import com.eBolivar.domain.NotificacionPadron;
import com.eBolivar.domain.Reclamos;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.apache.poi.ss.formula.eval.NotImplementedException;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class ConsultasDaoHinernateImpl extends HibernateDaoSupport {

    @Autowired
    private SessionFactory sessionFactory;

	public List obtenerReclamosPorDia() {

		String query = "SELECT  to_date(R.REC_FECHA_INGRESO,'dd/MM/yy') AS fecha ,COUNT (*) CONTADOR  "
				+ " FROM reclamos R  "
				+ " where R.REC_FECHA_INGRESO  is not null  "
				+ "  GROUP BY to_date(R.REC_FECHA_INGRESO,'dd/MM/yy')"
				+ "  ORDER BY FECHA  ASC ";
		Query q = getSession().createSQLQuery(query);
		return q.list();

	}

	 public List obtenerReclamosPorArea() {

		String query = "select count(*) as Contador , A.ARE_NOMBRE  from reclamos r , subtipos st , tipo_areas  ta , areas a "
				+ " where R.STA_ID = ST.ST_ID "
				+ " and ST.TSA_ID = TA.TA_ID "
				+ " and TA.ARE_ID = A.ARE_ID " + " group by A.ARE_NOMBRE";
		Query q = getSession().createSQLQuery(query);

		return q.list();
	}

	public List obtenerReclamosPorCalle() {

		String query = "select count(*) as contador  , C.CA_NOMBRE from reclamos r , barrios b  ,localidades l , calles c"
				+ " where B.LOC_ID = L.LOC_ID"
				+ " and C.BR_ID = B.BR_ID"
				+ " and R.CA_ID = C.CA_ID" + " group by C.CA_NOMBRE";
		Query q = getSession().createSQLQuery(query);

		return q.list();
	}

	public List obtenerReclamosPorUsuario() {

		String query = "select count(*) contador , U.USU_LOGIN  from reclamos  r, usuarios u "
				+ "  where R.USU_LOGIN=U.USU_LOGIN " + " group by U.USU_LOGIN ";
		Query q = getSession().createSQLQuery(query);

		return q.list();
	}

	public List obtenerReclamosPorParametrosDeFecha(String fechaInicial,
			String fechaFinal) {
		String query = "SELECT  to_date(R.REC_FECHA_INGRESO,'dd/MM/yyyy') AS fecha ,COUNT (*) CONTADOR  "
				+ " FROM reclamos R  "
				+ " where R.REC_FECHA_INGRESO  is not null  "
				+ " and R.REC_FECHA_INGRESO BETWEEN to_date('"
				+ fechaInicial
				+ "','dd/MM/yyyy') and to_date('"
				+ fechaFinal
				+ "','dd/MM/yyyy')   "
				+ "  GROUP BY to_date(R.REC_FECHA_INGRESO,'dd/MM/yyyy')"
				+ "  ORDER BY FECHA  ASC ";
		Query q = getSession().createSQLQuery(query);
		return q.list();

	}

	public List obtenerReclamosPorAreaPorFecha(Date fechaDesde, Date fechaHasta){

		String query = "select count(*) as Contador , A.ARE_NOMBRE  from reclamos r , subtipos st , tipo_areas  ta , areas a " +
				"  where R.STA_ID = ST.ST_ID and ST.TSA_ID = TA.TA_ID " +
				"  and TA.ARE_ID = A.ARE_ID  " +
				"  and R.REC_FECHA_INGRESO  between :fechaDesde and :fechaHasta " +
				"  group by A.ARE_NOMBRE";
                 
		Query q = getSession().createSQLQuery(query)
				.setDate("fechaDesde", fechaDesde)
				.setDate("fechaHasta", fechaHasta);

		return q.list();

	}

	public List obtenerReclamosPorBarrioPorFecha(String fechaDesde,String fechaHasta) {
		String query = " select count(*) as Contador , B.BR_NOMBRE  from reclamos r , calles c , barrios b  " +
				" where R.CA_ID=C.CA_ID " +
				" and B.BR_ID = C.BR_ID " +
				" and R.REC_FECHA_INGRESO  between  to_date('"+fechaDesde+"','dd/MM/yyyy') and to_date('"+fechaHasta+"','dd/MM/yyyy') " +
				" group by B.BR_NOMBRE";
		Query q = getSession().createSQLQuery(query);
		
		return q.list();
	}
	
	public List obtenerReclamosPorCallesPorFecha(String fechaDesde,String fechaHasta) {
		String query = " select count(*) as Contador , C.CA_NOMBRE  from reclamos r , calles c  " +
					   " where R.CA_ID=C.CA_ID " +
					   " and R.REC_FECHA_INGRESO  between  to_date('"+fechaDesde+"','dd/MM/yyyy') and to_date('"+fechaHasta+"','dd/MM/yyyy')  " +
					   " group by C.CA_NOMBRE";
		Query q = getSession().createSQLQuery(query);

		return q.list();
	}
	
	public Integer insertReclamo(String nombre,String apellido,String email,String descripcion) {
		throw  new NotImplementedException("metodo no implementado");
	}
	
	public Integer insertNotificacion(NotificacionPadron n) {
		throw new NotImplementedException("Metodo no implementado");
	}
	
	
	public int insertarReclamo(String nombre,String apellido , String email,String descripcion){
		nombre.toUpperCase().replace("DELETE", "").replace("UPDATE", "");
		apellido.toUpperCase().replace("DELETE", "").replace("UPDATE", "");
		email.toUpperCase().replace("DELETE", "").replace("UPDATE", "");
		descripcion.toUpperCase().replace("DELETE", "").replace("UPDATE", "");
 
		try{
		
		String query = " insert into reclamos "
				+ " ( rec_id , rec_nombre , rec_apellido , rec_email , rec_reclamo , ca_id , sta_id , usu_login , rec_titulo , rec_fecha_ingreso ) " +
				" values (SEQ_RECLAMOS_ID.nextval , '"+ nombre  +"'  , '"+ apellido +"'  , '"+  email +"'  , '"+ descripcion +"' , 403 , 547 , 'usuario_web' , 'Solicitud Web' , sysdate )";
		
		Conexion coneccion = new Conexion();

		coneccion.inserta(query);
		
		return 1;
		}
		
		catch(Exception e){
			
			return -1;
		}
		 
	}
	
	
	public void insertarReclamo(Reclamos reclamo) throws Exception{
		 
		reclamo.setNombre(reclamo.getNombre().replace("'", "").replace("-", ""));

		reclamo.setApellido(reclamo.getApellido().replace("'", "").replace("-", ""));

		reclamo.seteMail(reclamo.geteMail().replace("'", "").replace("-", ""));

		reclamo.setReclamo(reclamo.getReclamo().replace("'", "").replace("-", ""));
		
		reclamo.setReclamo(reclamo.getReclamo() + " -- Ubicacion del Reclamo" + reclamo.getUbicacion() );
		
		
		String query = " insert into reclamos "
				+ " ( rec_id , rec_nombre , rec_apellido , rec_email , rec_reclamo , ca_id , sta_id , usu_login , rec_titulo , rec_fecha_ingreso ) " +
				" values (SEQ_RECLAMOS_ID.nextval , '"+ reclamo.getNombre() +"'  , '"+ reclamo.getApellido()+"'  , '"+ reclamo.geteMail()+"'  , '"+ reclamo.getReclamo() +"' , 403 , 547 , 'usuario_web' , 'Solicitud Web' , sysdate )";
		
		Conexion coneccion = new Conexion();

		coneccion.inserta(query);
		 
	}
	
	
	public  JasperPrint crearPrint(JasperReport jr, Map<String , Object> mapa) throws JRException {
        try (CloseableSession session = new CloseableSession(sessionFactory.openSession())) {
            return JasperFillManager.fillReport(jr, mapa, session.delegate().connection());

        } catch (HibernateException e) {
            throw e;
        }
    }
}
