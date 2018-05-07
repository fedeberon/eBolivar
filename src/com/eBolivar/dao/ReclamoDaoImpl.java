package com.eBolivar.dao;

import com.eBolivar.common.OracleBD;
import com.eBolivar.domain.Reclamos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ReclamoDaoImpl {

	OracleBD baseDeDatos;
	PreparedStatement preparedStatement = null;
	ResultSet resultado = null;
	Reclamos reclamo;
	List<Reclamos> reclamos;
	
	public void ejecutarQuery(String query) {

		this.baseDeDatos.ejecutar(query);

	}

	public Reclamos getReclamoById(Integer codigo) {
		String query = " SELECT RECLAMO.REC_ID , RECLAMO.REC_NOMBRE , RECLAMO.REC_APELLIDO , RECLAMO.REC_DNI , RECLAMO.REC_EMAIL , RECLAMO.REC_ESTADO , RECLAMO.REC_TIPO_SOLICI , RECLAMO.REC_FECHA_INGRESO , RECLAMO.REC_RECLAMO , CALLE.CA_NOMBRE , BARRIO.BR_NOMBRE , LOCALIDAD.LOC_NOMBRE , DEPARTAMENTO.DEP_NOMBRE " + 
					   " FROM RECLAMOS RECLAMO " +
					   " INNER JOIN CALLES CALLE ON RECLAMO.CA_ID = CALLE.CA_ID " +
					   " INNER JOIN BARRIOS BARRIO ON CALLE.BR_ID = BARRIO.BR_ID " +
					   " INNER JOIN LOCALIDADES LOCALIDAD ON BARRIO.LOC_ID = LOCALIDAD.LOC_ID " +
					   " INNER JOIN DEPARTAMENTOS DEPARTAMENTO ON LOCALIDAD.DEP_ID = DEPARTAMENTO.DEP_ID WHERE REC_ID = ? ";

		baseDeDatos = new OracleBD();
		try {
			baseDeDatos.conectar();
			
			preparedStatement = baseDeDatos.getConexion().prepareStatement(query);

			preparedStatement.setInt(1, codigo);
			
			resultado = preparedStatement.executeQuery();
			
			fillReclamo();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			baseDeDatos.cerrarConexion();
		}
		
		return reclamo;
	}
  
	 public List<Reclamos> getReclamos() {
		String query = " SELECT RECLAMO.REC_ID , RECLAMO.REC_NOMBRE , RECLAMO.REC_APELLIDO , RECLAMO.REC_DNI , RECLAMO.REC_EMAIL , RECLAMO.REC_ESTADO , RECLAMO.REC_TIPO_SOLICI , RECLAMO.REC_FECHA_INGRESO , RECLAMO.REC_FECHA_INGRESO , CALLE.CA_NOMBRE , BARRIO.BR_NOMBRE , LOCALIDAD.LOC_NOMBRE , DEPARTAMENTO.DEP_NOMBRE " + 
					   " FROM RECLAMOS RECLAMO " +
					   " INNER JOIN CALLES CALLE ON RECLAMO.CA_ID = CALLE.CA_ID " +
					   " INNER JOIN BARRIOS BARRIO ON CALLE.BR_ID = BARRIO.BR_ID " +
					   " INNER JOIN LOCALIDADES LOCALIDAD ON BARRIO.LOC_ID = LOCALIDAD.LOC_ID " +
					   " INNER JOIN DEPARTAMENTOS DEPARTAMENTO ON LOCALIDAD.DEP_ID = DEPARTAMENTO.DEP_ID";

		try {
			baseDeDatos.conectar();

			preparedStatement = baseDeDatos.getConexion().prepareStatement(query);

			resultado = preparedStatement.executeQuery();

			fillReclamos();

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			baseDeDatos.cerrarConexion();
		}

		return reclamos;
	}

	private Reclamos fillReclamo() {
		reclamo = new Reclamos();

		try {
			while(this.resultado.next()){
			reclamo.setCodigo(this.resultado.getInt("REC_ID"));
			reclamo.setNombre(this.resultado.getString("REC_NOMBRE") != null ? this.resultado.getString("REC_NOMBRE") : null);
			reclamo.setApellido(this.resultado.getString("REC_APELLIDO") != null ? this.resultado.getString("REC_APELLIDO") : null);
			reclamo.setDni(this.resultado.getInt("REC_DNI"));
			reclamo.seteMail(this.resultado.getString("REC_EMAIL") != null ? this.resultado.getString("REC_EMAIL") : null);
			reclamo.setEstado(this.resultado.getString("REC_ESTADO") != null ? this.resultado.getString("REC_ESTADO") : null);
			reclamo.setTipoSolicitud(this.resultado.getString("REC_TIPO_SOLICI") != null ? this.resultado.getString("REC_TIPO_SOLICI") : null);
			reclamo.setFechaIngreso(this.resultado.getDate("REC_FECHA_INGRESO") != null ? this.resultado.getDate("REC_FECHA_INGRESO") : null);
			reclamo.setCalle(this.resultado.getString("CA_NOMBRE") != null ? this.resultado.getString("CA_NOMBRE") : null);
			reclamo.setBarrio(this.resultado.getString("BR_NOMBRE") != null ? this.resultado.getString("BR_NOMBRE") : null);
			reclamo.setLocalidad(this.resultado.getString("LOC_NOMBRE") != null ? this.resultado.getString("LOC_NOMBRE") : null);
			reclamo.setDepartamento(this.resultado.getString("DEP_NOMBRE") != null ? this.resultado.getString("DEP_NOMBRE") : null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return reclamo;
	}
	
	private List<Reclamos> fillReclamos() {
		reclamo = new Reclamos();

		try {

			while(resultado.next()){
			reclamo.setCodigo(this.resultado.getInt("REC_ID"));
			reclamo.setNombre(this.resultado.getString("REC_NOMBRE") != null ? this.resultado.getString("REC_NOMBRE") : null);
			reclamo.setApellido(this.resultado.getString("REC_APELLIDO") != null ? this.resultado.getString("REC_APELLIDO") : null);
			reclamo.setDni(this.resultado.getInt("REC_DNI"));
			reclamo.seteMail(this.resultado.getString("REC_EMAIL") != null ? this.resultado.getString("REC_EMAIL") : null);
			reclamo.setEstado(this.resultado.getString("REC_ESTADO") != null ? this.resultado.getString("REC_ESTADO") : null);
			reclamo.setTipoSolicitud(this.resultado.getString("REC_TIPO_SOLICI") != null ? this.resultado.getString("REC_TIPO_SOLICI") : null);
			reclamo.setFechaIngreso(this.resultado.getDate("REC_FECHA_INGRESO") != null ? this.resultado.getDate("REC_FECHA_INGRESO") : null);
			reclamo.setCalle(this.resultado.getString("CA_NOMBRE") != null ? this.resultado.getString("CA_NOMBRE") : null);
			reclamo.setBarrio(this.resultado.getString("BR_NOMBRE") != null ? this.resultado.getString("BR_NOMBRE") : null);
			reclamo.setLocalidad(this.resultado.getString("LOC_NOMBRE") != null ? this.resultado.getString("LOC_NOMBRE") : null);
			reclamo.setDepartamento(this.resultado.getString("DEP_NOMBRE") != null ? this.resultado.getString("DEP_NOMBRE") : null);
			reclamos.add(reclamo);
			}
			
		} catch (Exception e) {
			return null;
		}
		return reclamos;
	}

	
	public Reclamos saveReclamo(Reclamos reclamo){
		
		baseDeDatos = new OracleBD();
		try {
			
			String query = " insert into reclamos "
					+ " ( rec_id , rec_nombre , rec_apellido , rec_email , rec_reclamo , ca_id , sta_id , usu_login , rec_titulo , rec_fecha_ingreso ) " +
					" values (SEQ_RECLAMOS_ID.nextval , ?  , ?  , ? , ? , 403 , 547 , 'usuario_web' , 'Solicitud Web' , sysdate )";

			baseDeDatos.conectar();
			
			preparedStatement = baseDeDatos.getConexion().prepareStatement(query , new String[] { "REC_ID" });

			preparedStatement.setString(1, reclamo.getNombre());

			preparedStatement.setString(2, reclamo.getApellido());

			preparedStatement.setString(3, reclamo.geteMail());

			preparedStatement.setString(4, reclamo.getReclamo() + "\n Ubicacion: " + reclamo.getUbicacion());
			
			resultado = preparedStatement.executeQuery();
			
			reclamo.setCodigo( getCodigo() );
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			baseDeDatos.cerrarConexion();
		}
	
		return reclamo;
		
	}

	private Integer getCodigo() {
		try{
		 resultado = preparedStatement.getGeneratedKeys();
		 resultado.next();
		 return (int) resultado.getLong(1);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
