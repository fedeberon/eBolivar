/** @author JoseAlv * @version 1.0 */

package com.eBolivar.service;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

import com.eBolivar.common.SearchObject;
import com.eBolivar.dao.DepartamentosDaoHibernateImpl;
import com.eBolivar.domain.Barrios;
import com.eBolivar.domain.Departamentos;
import com.eBolivar.domain.Localidades;

public class DepartamentosServiceImpl {

	private DepartamentosDaoHibernateImpl dao;
	LocalidadesServiceImpl localidadesService;
	BarriosServiceImpl barriosService;
	CallesServiceImpl callesService;
	
	List<Departamentos>  departamentos;
	List<Localidades> localidades;
	List<Barrios> barrios;
	
	

    public void setLocalidadesService(LocalidadesServiceImpl localidadesService) {
		this.localidadesService = localidadesService;
	}

	public void setBarriosService(BarriosServiceImpl barriosService) {
		this.barriosService = barriosService;
	}

	public void setCallesService(CallesServiceImpl callesService) {
		this.callesService = callesService;
	}

	public DepartamentosServiceImpl() {
    	dao = (DepartamentosDaoHibernateImpl) PersistenceContextSingleton.getInstance().getBean("departamentosDao");
    }

	public Departamentos getObject(Integer id) {
		return dao.getObject(id);
	}

	public List <Departamentos> getObjects() {
		return  dao.getObjects();
	}

	public void removeObject(Integer id) {
		dao.removeObject(id);
	}

	public void saveObject(Departamentos object) throws ConstraintViolationException {
		dao.saveObject(object);
	}

	public List searchObjects(SearchObject search) {
		return dao.searchObjects(search);
	}
	
	public List<Departamentos> obtenerListaOrdenadaDeCiudades(){
		this.departamentos = getObjects();
		this.localidades = localidadesService.getObjects();
		this.barrios = barriosService.getObjects();
		
			return persistirLocalidadesEnDepartamento();
	}
	
	
	
	private List<Departamentos> persistirLocalidadesEnDepartamento(){
		

			for(Departamentos departamento : this.departamentos){
			
				persistirBarriosEnLocalidad(departamento.getLocalidades());

			}
				

			return departamentos;
			
	}
		
 

	private void persistirBarriosEnLocalidad(List<Localidades> localidades) {
		for(Localidades localidad : localidades){
		
				for(Localidades unaLocalidad :  this.localidades){
					
						if( unaLocalidad.getCodigo().compareTo(localidad.getCodigo()) == 0  ){
		
							localidad.setBarrios(unaLocalidad.getBarrios());
							
							persistirCallesEnBarrio(localidad.getBarrios());
							
							break;
						}
					}
		}
		
	}
	
	private void persistirCallesEnBarrio(List<Barrios> barrios){
		for(Barrios barrio : barrios){

			for(Barrios unBarrio : this.barrios){
					
				if(unBarrio.getCodigo().compareTo(barrio.getCodigo()) == 0 ){
						
					barrio.setCalles(unBarrio.getCalles());
						
					break;
					
				}
				
			}
		}
	}
}
