/** @author Federico Beron * @version 1.0 */ 
package com.eBolivar.service;

import java.io.Serializable;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

import com.eBolivar.common.IBusinessObject;
import com.eBolivar.common.SearchObject;
import com.eBolivar.dao.ParametroDaoHibernateImpl;
import com.eBolivar.domain.Parametro;

public class ParametroServiceImpl {
	
	private ParametroDaoHibernateImpl dao = null;

    public ParametroServiceImpl(){
    	dao = (ParametroDaoHibernateImpl) PersistenceContextSingleton.getInstance().getBean("parametroDao");
    }



	public IBusinessObject getObject(Serializable id) {
		return dao.getObject(id);
	}
	public List <IBusinessObject> getObjects() {
		return  dao.getObjects();
	}
	
	public void removeObject(Serializable id) {
		dao.removeObject(id);
		
	}
	public void saveObject(IBusinessObject object) throws ConstraintViolationException{
		dao.saveObject(object);
	}
	
	 
	public List <IBusinessObject> getObjects(String paramName) {
		return  dao.getObjectsRest(paramName);
	}
	
	 public List searchObjects(SearchObject search){
		return dao.searchObjects(search);

	}
	 public List getObjectsRest(String paramName){
		 
		 return dao.getObjectsRest(paramName);
	 }

	 public Parametro getName(String value){
	
		return dao.getName(value);
	}


}
