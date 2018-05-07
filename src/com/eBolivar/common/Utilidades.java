
package com.eBolivar.common;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Utilidades {


	    public static Date getPrimerDiaDelMes() {
	        Calendar cal = Calendar.getInstance();
	        cal.set(cal.get(Calendar.YEAR), 
	                cal.get(Calendar.MONTH),
	                cal.getActualMinimum(Calendar.DAY_OF_MONTH),
	                cal.getMinimum(Calendar.HOUR_OF_DAY),
	                cal.getMinimum(Calendar.MINUTE),
	                cal.getMinimum(Calendar.SECOND));
	        return cal.getTime();
	    }

	    public static Date getUltimoDiaDelMes() {
	        Calendar cal = Calendar.getInstance();
	        cal.set(cal.get(Calendar.YEAR),
	                cal.get(Calendar.MONTH),
	                cal.getActualMaximum(Calendar.DAY_OF_MONTH),
	                cal.getMaximum(Calendar.HOUR_OF_DAY),
	                cal.getMaximum(Calendar.MINUTE),
	                cal.getMaximum(Calendar.SECOND));
	        return cal.getTime();
	    }

	    
	    public static Calendar hoy(){
	    	
	    	Calendar fecha = new GregorianCalendar();
	    	//Obtenemos el valor del a�o, mes, d�a,
	    	//hora, minuto y segundo del sistema
	    	//usando el m�todo get y el par�metro correspondiente
	    	int anio = fecha.get(Calendar.YEAR);
	    	int mes = fecha.get(Calendar.MONTH);
	    	int dia = fecha.get(Calendar.DAY_OF_MONTH);
	    	int hora = fecha.get(Calendar.HOUR_OF_DAY);
	    	int minuto = fecha.get(Calendar.MINUTE);
	    	int segundo = fecha.get(Calendar.SECOND);
	    	return fecha;	
	    	
	    }
	    public static String strHoy(){
	    	
	    	 Calendar fecha = new GregorianCalendar();
	         //Obtenemos el valor del a�o, mes, d�a,
	         //hora, minuto y segundo del sistema
	         //usando el m�todo get y el par�metro correspondiente
	         int anio = fecha.get(Calendar.YEAR);
	         int mes = fecha.get(Calendar.MONTH);
	         int dia = fecha.get(Calendar.DAY_OF_MONTH);
	         int hora = fecha.get(Calendar.HOUR_OF_DAY);
	         int minuto = fecha.get(Calendar.MINUTE);
	         int segundo = fecha.get(Calendar.SECOND);
	         String strHoy =  dia + "/" + (mes+1) + "/" + anio; 
	         return strHoy;
	    	
	    }

}
