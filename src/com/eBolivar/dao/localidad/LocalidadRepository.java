package com.eBolivar.dao.localidad;

import com.eBolivar.dao.CloseableSession;
import com.eBolivar.dao.localidad.interfaces.ILocalidadRepository;
import com.eBolivar.domain.Localidad;
import com.eBolivar.service.localidad.ILocalidadService;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Lucas Cortés on 22/02/2019.
 */
@Repository
public class LocalidadRepository implements ILocalidadRepository {

    private SessionFactory sessionFactory;

    public LocalidadRepository() {}

    @Autowired
    public LocalidadRepository(@Qualifier("sessionFactoryJpa") SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Localidad> findAll(){
        try(CloseableSession session = new CloseableSession(sessionFactory.openSession())){
            Query query = session.delegate().createQuery("from Localidad");

            return query.list();
        }
        catch (HibernateException e){
            e.printStackTrace();
            throw e;
        }
    }

}
