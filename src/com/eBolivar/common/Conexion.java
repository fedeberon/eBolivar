package com.eBolivar.common;


import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Properties;



/**
 * Ejemplo simple de uso de BasicDataSource.
 *
 * @author Chuidiang
 */
public class Conexion {

	private final Connection conn = null;
	private final Statement stmt = null;
	private final ResultSet rs = null;
	private PreparedStatement ps=null;
	public static String ARCHIVO_CONFIGURACION = "DBConfig.properties";

	
	
	private static String DB_USER;
	private static String DB_PASSWORD;
	private static String DB_SID;
	private static String DB_HOST;
	private static String DB_PORT;
	private static String DB_URL;
	
	private static Properties properties;
	
	
	public Connection getConexion() {
		return conn;
	}
    /**
     * @param args
     */
    public static void main(String[] args) {
        new Conexion();
    }

    /** Pool de conexiones */
    private DataSource dataSource;

    /**
     * Inicializa el pool de conexiones BasicDataSource y realiza una insercion
     * y una consulta
     */
    public Conexion() {
        inicializaDataSource("","","","","");
        //inserta();
        //realizaConsulta();
        //ArrayList resultado=sql("select * from eventos_sitios_diario");
        //System.out.println("Resultados: "+resultado.size());
    }
    
    public void conectarBase(String url,String puerto,String host,String user,String pass){
    	inicializaDataSource(url,puerto,host,user,pass);
    }
    
    public ArrayList sql(String sql){
    	Locale.setDefault(new Locale("es", "ES"));
    	return consultar(sql);
    }
    
    public void actualizar(String sql){
    	inserta(sql);
    }
    
    public void conectar(){
    	Locale.setDefault(new Locale("es", "ES"));
   
    	conectarBase(DB_HOST, DB_PORT, DB_SID, DB_USER, DB_PASSWORD);
    }

    /**
     * Inicializacion de BasicDataSource
     */
    private void inicializaDataSource(String url,String puerto,String bd,String user,String pass) {
        
    	BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        basicDataSource.setUsername("SIR");
        basicDataSource.setPassword("SIR");
        basicDataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");


        // Opcional. Sentencia SQL que le puede servir a BasicDataSource
        // para comprobar que la conexion es correcta.
        basicDataSource.setValidationQuery("select 1 from dual");

        dataSource = basicDataSource;
    }

    /**
     * Realiza una insercion, pidiendo una conexion al dataSource y cerrandola
     * inmediatamente despues, para liberarla.
     */
    public void inserta(String sql) {
        Connection conexion = null;
        try {
            // BasicDataSource nos reserva una conexion y nos la devuelve.
            conexion = dataSource.getConnection();

            // La insercion.
            Statement ps = conexion.createStatement();
            ps.executeUpdate(sql);

        } catch (SQLException e) {
            //e.printStackTrace();
        } finally {
            liberaConexion(conexion);
        }
    }

    /**
     * Cierra la conexion. Al provenir de BasicDataSource, en realidad no se
     * esta cerrando. La llamada a close() le indica al BasicDataSource que
     * hemos terminado con dicha conexion y que puede asignarsela a otro que la
     * pida.
     *
     * @param conexion
     */
    private void liberaConexion(Connection conexion) {
        try {
            if (null != conexion) {
                // En realidad no cierra, solo libera la conexion.
                conexion.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Realiza una consulta a la base de datos y muestra los resultados en
     * pantalla.
     */
    private void realizaConsulta() {
        Connection conexion = null;
        try {
            conexion = dataSource.getConnection();
            Statement sentencia = conexion.createStatement();
            ResultSet rs = sentencia.executeQuery("select * from eventos_sitios_diario");

            // La tabla tiene cuatro campos.
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // En el finally, para asegurar que se ejecuta, se cierra la
            // conexion.
            liberaConexion(conexion);
        }
    }
    
    private ArrayList consultar(String sql) {
        Connection conexion = null;
        ResultSet rs=null;
        try {
        	conexion = dataSource.getConnection();
            Statement sentencia = conexion.createStatement();
             rs = sentencia.executeQuery(sql);
            // System.out.println("Consulta Conexion -->" + sql);
             ResultSetMetaData rsmd=null;
             rsmd=rs.getMetaData();
             int cols=rsmd.getColumnCount();
             ArrayList fila=new ArrayList();
             ArrayList aux=new ArrayList();
             for(int i=1;i<=cols;i++) {fila.add(rsmd.getColumnName(i));}
			 
             aux.add(fila);

			while(rs.next())
			{
				fila=new ArrayList();
				for(int i=1;i<=cols;i++){
					if(rs.getString(i)==null) fila.add("");
					else fila.add(rs.getString(i));
				}
				aux.add(fila);
			}
              
             return aux;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            // En el finally, para asegurar que se ejecuta, se cierra la
            // conexion.
            liberaConexion(conexion);
        }
    }
   
    public PreparedStatement preparar(String sql)
	{
		try
		{
			ps = conn.prepareStatement(sql);
		}
		catch (SQLException e)
		{
		}
		return ps;
	}
    
}

