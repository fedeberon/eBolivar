package com.eBolivar.dao.personaAsociada;


import com.eBolivar.dao.CloseableSession;
import com.eBolivar.dao.personaAsociada.interfaces.IPersonaAsociadaRepository;
import com.eBolivar.domain.administradorCuenta.PersonaAsociada;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonaAsociadaRepository implements IPersonaAsociadaRepository{

    @Autowired
    @Qualifier("sessionFactoryJpa")
    private SessionFactory sessionFactory;

    @Override
    public PersonaAsociada save(PersonaAsociada personaAsociada) {
        Transaction tx = null;
        try(CloseableSession session = new CloseableSession(sessionFactory.openSession())){
            tx = session.delegate().beginTransaction();
            session.delegate().saveOrUpdate(personaAsociada);
            tx.commit();

            return personaAsociada;
        }
        catch (HibernateException e){
            tx.rollback();
            throw e;
        }
    }

    @Override
    public List<PersonaAsociada> findAll(String username) {
        try(CloseableSession session = new CloseableSession(sessionFactory.openSession())){
            Query query = session.delegate().createQuery("from PersonaAsociada where administradorCuenta.username = :username");
            query.setParameter("username", username);

            return query.list();
        }
        catch (HibernateException e){
            e.printStackTrace();
            throw e;
        }
    }



}
