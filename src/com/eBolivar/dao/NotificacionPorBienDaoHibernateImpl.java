package com.eBolivar.dao;

import com.eBolivar.domain.NotificacionPorBien;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class NotificacionPorBienDaoHibernateImpl extends HibernateDaoSupport {
	
	public NotificacionPorBien getObject(Integer id) {
		return (NotificacionPorBien) getHibernateTemplate().get(NotificacionPorBien.class, id);
	}

	public List <NotificacionPorBien> getObjects() {
		return getHibernateTemplate().find("from NotificacionPorBien order by id");
	}

	public void removeObject(Integer id) {
		Object object = getHibernateTemplate().load(NotificacionPorBien.class, id);
        getHibernateTemplate().delete(object);
	}

	public void saveObject(NotificacionPorBien object) {
		getHibernateTemplate().saveOrUpdate(object);		
	}

	public void saveListObject(List<NotificacionPorBien> objects, Session session) {
		for(NotificacionPorBien unaNotificacion : objects){
			session.saveOrUpdate(unaNotificacion);
		}
	}

    public List<NotificacionPorBien> getNotifiacionesDeTasa(Long codigoElectronicoPago){
        Session session = getSessionFactory().getCurrentSession();
        Query query = session.createQuery("from NotificacionPorBien where codigoElectronicoPago = :codigoElectronicoPago");
        query.setLong("codigoElectronicoPago", codigoElectronicoPago);

        return query.list();
    }


}
