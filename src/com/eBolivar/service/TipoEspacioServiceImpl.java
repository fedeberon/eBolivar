/** @author FedeBeron * @version 1.0 */

package com.eBolivar.service;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

import com.eBolivar.common.SearchObject;
import com.eBolivar.dao.TipoEspacioDaoHibernateImpl;
import com.eBolivar.domain.TipoEspacio;

public class TipoEspacioServiceImpl {

	private TipoEspacioDaoHibernateImpl dao = null;

    public TipoEspacioServiceImpl() {
    	dao = (TipoEspacioDaoHibernateImpl) PersistenceContextSingleton.getInstance().getBean("tipoEspacioDao");
    }

	public TipoEspacio getObject(Integer id) {
		return dao.getObject(id);
	}

	public List <TipoEspacio> getObjects() {
		return  dao.getObjects();
	}

	public void removeObject(Integer id) {
		dao.removeObject(id);
	}

	public void saveObject(TipoEspacio object) throws ConstraintViolationException {
		dao.saveObject(object);
	}

	public List searchObjects(SearchObject search) {
		return dao.searchObjects(search);
	}
}
