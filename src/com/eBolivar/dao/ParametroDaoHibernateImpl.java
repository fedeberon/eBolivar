/** @author Santiago Scalzadonna * @version 1.0 */ 
package com.eBolivar.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.eBolivar.common.IBusinessObject;
import com.eBolivar.common.SearchObject;
import com.eBolivar.domain.Parametro;

public class ParametroDaoHibernateImpl extends HibernateDaoSupport{

	public IBusinessObject getObject(Serializable id) {
		return (IBusinessObject) getHibernateTemplate().get(Parametro.class, id);
	}

	public List <IBusinessObject> getObjects() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Parametro.class);
	    criteria.addOrder(Order.asc("nombre"));
	    return getHibernateTemplate().findByCriteria(criteria);
	}

	public void removeObject(Serializable id) {
		Object object = getHibernateTemplate().load(Parametro.class, id);
        getHibernateTemplate().delete(object);
	}

	
	public void saveObject(IBusinessObject object) {
		getHibernateTemplate().saveOrUpdate(object);		
	}
	
	 public List <IBusinessObject> getObjectsRest(String paramName) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Parametro.class);
	    criteria.add(Restrictions.like("nombre", paramName));
	    criteria.addOrder(Order.desc("valor"));
	    return getHibernateTemplate().findByCriteria(criteria);
	}
	
	
	 public Parametro getName(String value) {
			Criteria criteria = getSession().createCriteria(Parametro.class);
		    criteria.add(Restrictions.like("valor", value));
		    criteria.addOrder(Order.desc("valor"));
		    return (Parametro) criteria.uniqueResult();
	 }
	 
	 
	public List searchObjects(SearchObject search){
		
		DetachedCriteria criteria = DetachedCriteria.forClass(Parametro.class);
	    criteria.addOrder(Order.asc("nombre"));
	    
	    if (search.getPage()>0){
	    	int maxResults = 20;
	    	int page = search.getPage()-1;
	    	return getHibernateTemplate().findByCriteria(criteria,page*maxResults,maxResults);
	    } 
	    else return getHibernateTemplate().findByCriteria(criteria);	
	    
	}

	
	 
	
}

