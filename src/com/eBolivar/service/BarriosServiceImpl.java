/** @author Fede Beron * @version 1.0 */

package com.eBolivar.service;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

import com.eBolivar.common.SearchObject;
import com.eBolivar.dao.BarriosDaoHibernateImpl;
import com.eBolivar.domain.Barrios;

public class BarriosServiceImpl {

	private BarriosDaoHibernateImpl dao = null;

    public BarriosServiceImpl() {
    	dao = (BarriosDaoHibernateImpl) PersistenceContextSingleton.getInstance().getBean("barriosDao");
    }

	public Barrios getObject(Integer id) {
		return dao.getObject(id);
	}

	public List <Barrios> getObjects() {
		return  dao.getObjects();
	}

	public void removeObject(Integer id) {
		dao.removeObject(id);
	}

	public void saveObject(Barrios object) throws ConstraintViolationException {
		dao.saveObject(object);
	}

	public List searchObjects(SearchObject search) {
		return dao.searchObjects(search);
	}
}
