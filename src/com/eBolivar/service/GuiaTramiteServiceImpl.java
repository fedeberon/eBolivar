/** @author FedeBeron * @version 1.0 */

package com.eBolivar.service;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

import com.eBolivar.common.SearchObject;
import com.eBolivar.dao.GuiaTramiteDaoHibernateImpl;
import com.eBolivar.domain.GuiaTramite;
import com.eBolivar.domain.TipoGuiaTramite;

public class GuiaTramiteServiceImpl {

	private GuiaTramiteDaoHibernateImpl dao = null;

    public GuiaTramiteServiceImpl() {
    	dao = (GuiaTramiteDaoHibernateImpl) PersistenceContextSingleton.getInstance().getBean("guiaTramiteDao");
    }

	public GuiaTramite getObject(Integer id) {
		return dao.getObject(id);
	}

	public List <GuiaTramite> getObjects() {
		return  dao.getObjects();
	}

	public void removeObject(Integer id) {
		dao.removeObject(id);
	}

	public void saveObject(GuiaTramite object) throws ConstraintViolationException {
		dao.saveObject(object);
	}

	public List searchObjects(SearchObject search) {
		return dao.searchObjects(search);
	}
	
	public List <TipoGuiaTramite> getTipoTramitesObjects() {
		return dao.getTipoTramitesObjects();
	}
}
