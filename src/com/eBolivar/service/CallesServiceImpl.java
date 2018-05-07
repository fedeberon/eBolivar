/** @author Fede Beron * @version 1.0 */

package com.eBolivar.service;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

import com.eBolivar.common.SearchObject;
import com.eBolivar.dao.CallesDaoHibernateImpl;
import com.eBolivar.domain.Calles;

public class CallesServiceImpl {

	private CallesDaoHibernateImpl dao = null;

    public CallesServiceImpl() {
    	dao = (CallesDaoHibernateImpl) PersistenceContextSingleton.getInstance().getBean("callesDao");
    }

	public Calles getObject(Integer id) {
		return dao.getObject(id);
	}

	public List <Calles> getObjects() {
		return  dao.getObjects();
	}

	public void removeObject(Integer id) {
		dao.removeObject(id);
	}

	public void saveObject(Calles object) throws ConstraintViolationException {
		dao.saveObject(object);
	}

	public List searchObjects(SearchObject search) {
		return dao.searchObjects(search);
	}
}
