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
import com.eBolivar.domain.Barrios;

public class BarriosDaoHibernateImpl extends HibernateDaoSupport {
	
	public Barrios getObject(Integer id) {
		return (Barrios) getHibernateTemplate().get(Barrios.class, id);
	}

	public List <Barrios> getObjects() {
		return getHibernateTemplate().find("from Barrios order by nombre");
	}

	public void removeObject(Integer id) {
		Object object = getHibernateTemplate().load(Barrios.class, id);
        getHibernateTemplate().delete(object);
	}

	public void saveObject(Barrios object) {
		getHibernateTemplate().saveOrUpdate(object);		
	}
	
	public List searchObjects(SearchObject search) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Barrios.class);
		criteria.addOrder(Order.asc("id"));
		criteria.createAlias("localidad", "loc");
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
			} else if(search.getCampo().equalsIgnoreCase("localidad")) {
					criteria.add(Restrictions.ilike("loc.nombre", search.getValor()));
			} else if(search.getCampo().equalsIgnoreCase("departamento")){
				criteria.add(Restrictions.ilike("dep.nombre", search.getValor()));
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

	public List<Barrios>obtenerBarriosPorLocalidades(Integer idLocalidad){
		Criteria criteria = getSession().createCriteria(Barrios.class)
				.add(Restrictions.eq("localidad.codigo", idLocalidad))
				.addOrder(Order.asc("nombre"));
	return criteria.list();
	}

}
