/** @author FedeBeron * @version 1.0 */

package com.eBolivar.service;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

import com.eBolivar.common.SearchObject;
import com.eBolivar.dao.NotificacionPadronDaoHibernateImpl;
import com.eBolivar.domain.NotificacionPadron;
import org.springframework.stereotype.Service;

@Service
public class NotificacionPadronServiceImpl {

	private NotificacionPadronDaoHibernateImpl dao = null;

    public NotificacionPadronServiceImpl() {
    	dao = (NotificacionPadronDaoHibernateImpl) PersistenceContextSingleton.getInstance().getBean("notificacionPadronDao");
    }

	public NotificacionPadron getObject(Integer id) {
		return dao.getObject(id);
	}

	public List <NotificacionPadron> getObjects() {
		return  dao.getObjects();
	}

	public void removeObject(Integer id) {
		dao.removeObject(id);
	}

	public void saveObject(NotificacionPadron object) throws ConstraintViolationException {
		dao.saveObject(object);
	}
	
	public void updateObject(NotificacionPadron object) {
		dao.updateObject(object);		
	}

	public List searchObjects(SearchObject search) {
		return dao.searchObjects(search);
	}

	public NotificacionPadron getObjetByPadron(String nroPadron) {
		return dao.getObjetByPadron(nroPadron);
	}
	
	public Boolean exist(String nroPadron) {
		return dao.exist(nroPadron);
	}
	
	
	
}
