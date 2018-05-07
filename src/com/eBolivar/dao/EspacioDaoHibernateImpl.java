/** @author Fede Beron* @version 1.0 */

package com.eBolivar.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.eBolivar.common.SearchObject;
import com.eBolivar.domain.Espacio;

public class EspacioDaoHibernateImpl extends HibernateDaoSupport {
	
	public Espacio getObject(Integer id) {
		return (Espacio) getHibernateTemplate().get(Espacio.class, id);
	}

	public List <Espacio> getObjects() {
		return getHibernateTemplate().find("from Espacio order by tipoEspacio.id , area");
	}

	public void removeObject(Integer id) {
		Object object = getHibernateTemplate().load(Espacio.class, id);
        getHibernateTemplate().delete(object);
	}

	public void saveObject(Espacio object) {
		getHibernateTemplate().saveOrUpdate(object);		
	}
	
	public List searchObjects(SearchObject search) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Espacio.class);
		criteria.addOrder(Order.asc("id"));

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
					criteria.add(Restrictions.or(Restrictions.ilike("Id",
							search.getValor(), mm), Restrictions.idEq(Integer
							.parseInt(search.getValor()))));
				} catch (java.lang.NumberFormatException e) {
					criteria.add(Restrictions.ilike("Id", search.getValor(),
							mm));
				}
			} else if(search.getCampo().equalsIgnoreCase("id")) {
				try {
					criteria.add(Restrictions.idEq(Integer.parseInt(search.getValor())));
				} catch (Exception e) {
					criteria.add(Restrictions.idEq(-1));
				}
			} else {
				criteria.add(Restrictions.ilike(search.getCampo(),
						search.getValor(), mm));
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
	
	public List<Espacio> obtenerEspacios(Integer tipo){
		Criteria criteria = getSession().createCriteria(Espacio.class)
				.add(Restrictions.eq("tipoEspacio.id", tipo));
		return criteria.list();
	}

	public List<Espacio> obtenerEspacios(Integer tipo , String area){
		Criteria criteria = getSession().createCriteria(Espacio.class)
				.add(Restrictions.eq("tipoEspacio.id", tipo))
				.add(Restrictions.eq("area", area));
		return criteria.list();
	}

	public List<Espacio> getObjects(Integer tipoDeObra , String area) {
		Criteria criteria = getSession().createCriteria(Espacio.class)
				.add(Restrictions.eq("tipoEspacio.id", tipoDeObra))
				.add(Restrictions.ilike("area", area));
		return criteria.list();
	}

	public List getDistinctAreas(Integer tipoEspacio) {
		Criteria criteria = getSession().createCriteria(Espacio.class);
				
		criteria.setProjection(Projections.distinct(Projections.property("area")))
		.add(Restrictions.eq("tipoEspacio.id", tipoEspacio));
		
		return criteria.list();
	}

}
