package com.eBolivar.dao;

import com.eBolivar.domain.DetalleFactura;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class DetalleFacturaDaoHibernateImpl extends HibernateDaoSupport{
	
	public DetalleFactura getObject(Integer id) {
		return (DetalleFactura) getHibernateTemplate().get(DetalleFactura.class, id);
	}

	public List <DetalleFactura> getObjects() {
		return getHibernateTemplate().find("from DetalleFactura order by id");
	}

	public void removeObject(Integer id) {
		Object object = getHibernateTemplate().load(DetalleFactura.class, id);
        getHibernateTemplate().delete(object);
	}

	public void saveObject(DetalleFactura object) {
		getHibernateTemplate().saveOrUpdate(object);		
	}

	public void saveListObject(List<DetalleFactura> objects, Session session) {
		for(DetalleFactura unDetalleFactura : objects){
			session.saveOrUpdate(unDetalleFactura);
		}
	}

    public List<DetalleFactura> getDetalleDeTasaPorNumeroDeFactura(String idFactura){
        Session session = getSessionFactory().getCurrentSession();
        Query query = session.createQuery("from DetalleFactura where idFactura = :idFactura");
        query.setString("idFactura", idFactura);

        return query.list();
    }

}
