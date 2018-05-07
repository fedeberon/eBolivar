/** @author Fede Beron * @version 1.0 */

package com.eBolivar.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.eBolivar.common.SearchObject;
import com.eBolivar.domain.Calles;

public class CallesDaoHibernateImpl extends HibernateDaoSupport {
	
	public Calles getObject(Integer id) {
		return (Calles) getHibernateTemplate().get(Calles.class, id);
	}

	public List <Calles> getObjects() {
		return getHibernateTemplate().find("from Calles order by nombre");
	}

	public void removeObject(Integer id) {
		Object object = getHibernateTemplate().load(Calles.class, id);
        getHibernateTemplate().delete(object);
	}

	public void saveObject(Calles object) {
		getHibernateTemplate().saveOrUpdate(object);		
	}
	
	public List searchObjects(SearchObject search) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Calles.class);
		criteria.addOrder(Order.asc("nombre"));
		criteria.createAlias("barrio", "barrios");
		criteria.createAlias("barrios.localidad", "loc");
		criteria.createAlias("loc.departamento", "dep");
		

		if (search.getValor() != null && search.getValor().length() > 0
				&& search.getCampo() != null) {
			search.setValor(search.getValor().toUpperCase());
			MatchMode mm = MatchMode.ANYWHERE;
			if (search.getUbicacion() != null) {
				if (search.getUbicacion().equalsIgnoreCase("EXACT")) {
					mm = MatchMode.EXACT;
				} else if (search.getUbicacion().equalsIgnoreCase("START")) {
					mm = MatchMode.START;
				}
			}
			if (search.getCampo().equalsIgnoreCase("TODOS")) {
				try {
					criteria.add(Restrictions.idEq(Integer.parseInt(search.getValor())));
				} catch (Exception e) {
					criteria.add(Restrictions.ilike("nombre", search.getValor(),mm));
				}
			} else if(search.getCampo().equalsIgnoreCase("barrios")) {
				criteria.add(Restrictions.ilike("barrios.nombre", search.getValor(),mm));
			} else if(search.getCampo().equalsIgnoreCase("loc.nombre")) {
				criteria.add(Restrictions.ilike("loc.nombre", search.getValor(),mm));
			} else if(search.getCampo().equalsIgnoreCase("dep.nombre")) {
				criteria.add(Restrictions.ilike("dep.nombre", search.getValor(),mm));
			}
		}

		if (search.getPage() > 0) {
			int maxResults = 20;
			int page = search.getPage() - 1;
			return getHibernateTemplate().findByCriteria(criteria,
					page * maxResults, maxResults);
		} else
			return getHibernateTemplate().findByCriteria(criteria);
	}


	public List obtenerCallesPorBarrio(Integer idBarrio){
		Criteria criteria = getSession().createCriteria(Calles.class)
				.add(Restrictions.like("barrio.codigo", idBarrio))
				.addOrder(Order.asc("nombre")); 
		return criteria.list();
		
	}



}
