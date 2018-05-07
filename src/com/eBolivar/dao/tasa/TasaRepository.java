package com.eBolivar.dao.tasa;


import com.eBolivar.dao.CloseableSession;
import com.eBolivar.dao.tasa.interfaces.ITasaRepository;
import com.eBolivar.domain.Tasa;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TasaRepository implements ITasaRepository {

    @Autowired
    @Qualifier("sessionFactoryJpa")
    private SessionFactory sessionFactory;

    @Override
    public List<Tasa> findAll(){
        try(CloseableSession session = new CloseableSession(sessionFactory.openSession())){
            return session.delegate().createQuery("from Tasa").list();
        }
        catch (HibernateException e){
            e.printStackTrace();
            throw e;
        }
    }

}
