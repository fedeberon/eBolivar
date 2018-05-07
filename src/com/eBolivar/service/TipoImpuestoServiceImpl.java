/** @author FedeBeron * @version 1.0 */

package com.eBolivar.service;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

import com.eBolivar.common.SearchObject;
import com.eBolivar.dao.TipoImpuestoDaoHibernateImpl;
import com.eBolivar.domain.TipoImpuesto;

public class TipoImpuestoServiceImpl {

	private TipoImpuestoDaoHibernateImpl dao = null;

    public TipoImpuestoServiceImpl() {
    	dao = (TipoImpuestoDaoHibernateImpl) PersistenceContextSingleton.getInstance().getBean("tipoImpuestoDao");
    }

	public TipoImpuesto getObject(Integer id ) {
		return dao.getObject(id);
	}

	public List <TipoImpuesto> getObjects() {
		return  dao.getObjects();
	}

	public void saveObject(TipoImpuesto object) throws ConstraintViolationException {
		dao.saveObject(object);
	}

	public List searchObjects(SearchObject search) {
		return dao.searchObjects(search);
	}
}
