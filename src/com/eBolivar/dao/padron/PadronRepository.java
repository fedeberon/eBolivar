package com.eBolivar.dao.padron;

import com.eBolivar.dao.CloseableSession;
import com.eBolivar.dao.padron.interfaces.IPadronRepository;
import com.eBolivar.domain.Padron;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * Created by Fede Beron on 11/7/2017.
 */
@Repository
public class PadronRepository implements IPadronRepository {

    @Autowired
    @Qualifier("sessionFactoryJpa")
    private SessionFactory sessionFactory;

    @Override
    public Padron save(Padron padron){
        Transaction tx = null;
        try(CloseableSession session = new CloseableSession(sessionFactory.openSession())){
            tx = session.delegate().getTransaction();
            tx.begin();
            session.delegate().save(padron);
            tx.commit();

            return padron;
        }
        catch (HibernateException e){
            e.printStackTrace();
            tx.rollback();
            throw e;
        }
    }

    @Override
    public Padron get(Integer id){
        try(CloseableSession session = new CloseableSession(sessionFactory.openSession())){
            return (Padron) session.delegate().get(Padron.class, id);
        }
        catch (HibernateException e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Padron getByNumero(String numero){
        try(CloseableSession session = new CloseableSession(sessionFactory.openSession())){
            Query query = session.delegate().createQuery("from Padron where numero = :numero");
            query.setString("numero", numero);

            return (Padron) query.uniqueResult();
        }
        catch (HibernateException e){
            e.printStackTrace();
            throw e;
        }
    }
}
