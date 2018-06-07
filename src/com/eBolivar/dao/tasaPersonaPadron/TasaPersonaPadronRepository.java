package com.eBolivar.dao.tasaPersonaPadron;

import com.eBolivar.dao.CloseableSession;
import com.eBolivar.dao.tasaPersonaPadron.interfaces.ITasaPersonaPadronRepository;
import com.eBolivar.domain.Padron;
import com.eBolivar.domain.Persona;
import com.eBolivar.domain.TasaPersonaPadron;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class TasaPersonaPadronRepository implements ITasaPersonaPadronRepository {

    @Autowired
    @Qualifier("sessionFactoryJpa")
    private SessionFactory sessionFactory;




    @Override
    public List<TasaPersonaPadron> findByPersonaPadron(Persona persona, Padron padron) {
        try(CloseableSession session = new CloseableSession(sessionFactory.openSession())){
            Query query = session.delegate().createQuery("from TasaPersonaPadron where persona = :persona and padron = :padron");
            query.setParameter("persona", persona);
            query.setParameter("padron", padron);

            return query.list();
        }
        catch (HibernateException e){
            e.printStackTrace();
            throw e;
        }
    }
}
