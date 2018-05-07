/** @author Fede Beron * @version 1.0 */

package com.eBolivar.service;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

import com.eBolivar.common.SearchObject;
import com.eBolivar.dao.LocalidadesDaoHibernateImpl;
import com.eBolivar.domain.Localidades;

public class LocalidadesServiceImpl {

	private LocalidadesDaoHibernateImpl dao = null;

    public LocalidadesServiceImpl() {
    	dao = (LocalidadesDaoHibernateImpl) PersistenceContextSingleton.getInstance().getBean("localidadesDao");
    }

	public Localidades getObject(Integer id) {
		return dao.getObject(id);
	}

	public List <Localidades> getObjects() {
		return  dao.getObjects();
	}

	public void removeObject(Integer id) {
		dao.removeObject(id);
	}

	public void saveObject(Localidades object) throws ConstraintViolationException {
		dao.saveObject(object);
	}

	public List searchObjects(SearchObject search) {
		return dao.searchObjects(search);
	}
}
