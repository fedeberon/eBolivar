/** @author Fede Beron* @version 1.0 */

package com.eBolivar.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.eBolivar.common.SearchObject;
import com.eBolivar.domain.NotificacionPadron;

public class NotificacionPadronDaoHibernateImpl extends HibernateDaoSupport {
	
	public NotificacionPadron getObject(Integer id) {
		return (NotificacionPadron) getHibernateTemplate().get(NotificacionPadron.class, id);
	}

	public List <NotificacionPadron> getObjects() {
		return getHibernateTemplate().find("from NotificacionPadron where estado != 'INACTIVO' order by id");
	}

	public void removeObject(Integer id) {
		Object object = getHibernateTemplate().load(NotificacionPadron.class, id);
        getHibernateTemplate().delete(object);
	}

	public void saveObject(NotificacionPadron object) {
		getHibernateTemplate().saveOrUpdate(object);		
	}

	
	public void updateObject(NotificacionPadron object) {
		getHibernateTemplate().update(object);		
	}
	
	public List searchObjects(SearchObject search) {
		DetachedCriteria criteria = DetachedCriteria.forClass(NotificacionPadron.class);
		criteria.addOrder(Order.desc("id"));

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
			int maxResults = 10;
			int page = search.getPage() - 1;
			return getHibernateTemplate().findByCriteria(criteria,
					page * maxResults, maxResults);
		} else
			return getHibernateTemplate().findByCriteria(criteria);
	}
	
	
	public NotificacionPadron getObjetByPadron(String nroPadron){
		try{
		Query query = getSession().createQuery("from NotificacionPadron where padron = :nroPadron ");
		query.setString("nroPadron", nroPadron);
		return (NotificacionPadron) query.uniqueResult();
		}
		catch(Exception e){
			e.printStackTrace();
			return null; 
		}
	}
	
	
	public Boolean exist(String nroPadron){
		Query query = getSession().createQuery("from NotificacionPadron where padron = :nroPadron ");
		query.setString("nroPadron", nroPadron);
 
		if(query.list().size() > 0) return true;
		
		else return false;
	}
	
	public Integer obtenerPadronesParaActualizar(){
		
		try{
		Criteria criteria = getSession().createCriteria(NotificacionPadron.class)
				.add(Restrictions.isNull("confirmado"));
		List<NotificacionPadron> lista = criteria.list();
		int result = lista.size();
		return result;
		}
		catch (Exception e) {
			return null;
		}
	}
	
}
