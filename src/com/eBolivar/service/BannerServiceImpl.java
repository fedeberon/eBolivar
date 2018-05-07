/** @author FedeBeron * @version 1.0 */

package com.eBolivar.service;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

import com.eBolivar.common.SearchObject;
import com.eBolivar.dao.BannerDaoHibernateImpl;
import com.eBolivar.domain.Banner;

public class BannerServiceImpl {

	private BannerDaoHibernateImpl dao = null;

    public BannerServiceImpl() {
    	dao = (BannerDaoHibernateImpl) PersistenceContextSingleton.getInstance().getBean("bannerDao");
    }

	public Banner getObject(Integer id ) {
		return dao.getObject(id);
	}

	public List <Banner> getObjects() {
		return  dao.getObjects();
	}

	public void removeObject( Integer id) {
		dao.removeObject(id);
	}

	public void saveObject(Banner object) throws ConstraintViolationException {
		dao.saveObject(object);
	}

	public List searchObjects(SearchObject search) {
		return dao.searchObjects(search);
	}
}
