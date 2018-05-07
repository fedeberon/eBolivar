/** @author FedeBeron * @version 1.0 */

package com.eBolivar.service;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

import com.eBolivar.common.SearchObject;
import com.eBolivar.dao.TipoGuiaTramiteDaoHibernateImpl;
import com.eBolivar.domain.TipoGuiaTramite;

public class TipoGuiaTramiteServiceImpl {

	private TipoGuiaTramiteDaoHibernateImpl dao = null;

    public TipoGuiaTramiteServiceImpl() {
    	dao = (TipoGuiaTramiteDaoHibernateImpl) PersistenceContextSingleton.getInstance().getBean("tipoGuiaTramiteDao");
    }

	public TipoGuiaTramite getObject(Integer id) {
		return dao.getObject(id);
	}

	public List <TipoGuiaTramite> getObjects() {
		return  dao.getObjects();
	}

	public void removeObject(Integer id) {
		dao.removeObject(id);
	}

	public void saveObject(TipoGuiaTramite object) throws ConstraintViolationException {
		dao.saveObject(object);
	}

	public List searchObjects(SearchObject search) {
		return dao.searchObjects(search);
	}
}
