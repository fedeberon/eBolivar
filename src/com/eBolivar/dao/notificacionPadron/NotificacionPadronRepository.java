package com.eBolivar.dao.notificacionPadron;

import com.eBolivar.bean.Pagination;
import com.eBolivar.common.SearchObject;
import com.eBolivar.dao.CloseableSession;
import com.eBolivar.dao.notificacionPadron.interfaces.INotificacionPadronRepository;
import com.eBolivar.domain.DeclaracionJurada;
import com.eBolivar.domain.NotificacionPadron;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Fede Beron on 21/02/2019.
 */
@Repository
public class NotificacionPadronRepository implements INotificacionPadronRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public NotificacionPadronRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<NotificacionPadron> findAll(SearchObject searchObject){
        try(CloseableSession session = new CloseableSession(sessionFactory.openSession())){
            Criteria criteria = session.delegate().createCriteria(NotificacionPadron.class);

            if(searchObject.getValor() != null && !searchObject.getValor().isEmpty()){
                criteria.add(Restrictions.or(
                        Restrictions.ilike("padron", searchObject.getValor(), MatchMode.ANYWHERE),
                            Restrictions.ilike("direccionEnvio", searchObject.getValor(), MatchMode.ANYWHERE)));
            }

            criteria.setFirstResult((searchObject.getPage() - 1) * Pagination.MAX_PAGE );
            criteria.setMaxResults(Pagination.MAX_PAGE);

            return criteria.list();
        }
        catch (HibernateException e){
            throw e;
        }


    }


    @Override
    public List<NotificacionPadron> findAll(String value, Integer page){
        try(CloseableSession session = new CloseableSession(sessionFactory.openSession())){
            Criteria criteria = session.delegate().createCriteria(NotificacionPadron.class);

            if(!value.isEmpty()){
                criteria.add(Restrictions.or(
                        Restrictions.ilike("padron", value, MatchMode.ANYWHERE),
                        Restrictions.ilike("direccionEnvio", value, MatchMode.ANYWHERE)));
            }

            criteria.setFirstResult((page - 1) * Pagination.MAX_PAGE );
            criteria.setMaxResults(Pagination.MAX_PAGE);

            return criteria.list();
        }
        catch (HibernateException e){
            throw e;
        }


    }


}
