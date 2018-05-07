package com.eBolivar.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OracleBD {

    private Connection conexion = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultado = null;
    
    public OracleBD() {

    }
    
    public OracleBD conectar() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            String BaseDeDatos = "jdbc:oracle:thin:@localhost:1521:XE";
            conexion = DriverManager.getConnection(BaseDeDatos, "SIR", "SIR");
 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public boolean ejecutar(String sql) {
        try {
        	conectar();
        	preparedStatement = getConexion().prepareStatement(sql);
        	preparedStatement.executeUpdate(sql);
            getConexion().commit();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        finally{
        	cerrarConexion();
        }
        
        return true;
    }

    public ResultSet consultar(String query) {
        
        try {
        	conectar();
        	
			preparedStatement = getConexion().prepareStatement(query);

			resultado = preparedStatement.executeQuery(query);
        
            getConexion().commit();

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
	   finally{
	        	cerrarConexion();
	        }
	        
        return resultado;
    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }
    
    public void cerrarConexion(){
    	try{
    		if(conexion != null && !conexion.isClosed()){
	    		conexion.close();
	    	}
	    	
	    	if(preparedStatement!= null && !preparedStatement.isClosed()){
	    		preparedStatement.close();
	    	}
    	}
    	catch(SQLException e){
    		e.printStackTrace();
    	}
    	
    }

}
