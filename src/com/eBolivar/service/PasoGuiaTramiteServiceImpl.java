/** @author FedeBeron * @version 1.0 */

package com.eBolivar.service;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

import com.eBolivar.common.SearchObject;
import com.eBolivar.dao.PasoGuiaTramiteDaoHibernateImpl;
import com.eBolivar.domain.PasoGuiaTramite;

public class PasoGuiaTramiteServiceImpl {

	private PasoGuiaTramiteDaoHibernateImpl dao = null;

    public PasoGuiaTramiteServiceImpl() {
    	dao = (PasoGuiaTramiteDaoHibernateImpl) PersistenceContextSingleton.getInstance().getBean("pasoGuiaTramiteDao");
    }

	public PasoGuiaTramite getObject(Integer id) {
		return dao.getObject(id);
	}

	public List <PasoGuiaTramite> getObjects() {
		return  dao.getObjects();
	}

	public void removeObject(Integer id) {
		dao.removeObject(id);
	}

	public void saveObject(PasoGuiaTramite object) throws ConstraintViolationException {
		dao.saveObject(object);
	}

	public List searchObjects(SearchObject search) {
		return dao.searchObjects(search);
	}
}
