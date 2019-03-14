package com.eBolivar.dao.direccionPadron;

import com.eBolivar.dao.CloseableSession;
import com.eBolivar.dao.direccionPadron.interfaces.IDireccionPadronRespository;
import com.eBolivar.domain.DeclaracionJurada;
import com.eBolivar.domain.DireccionPadron;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by erwin on 13/3/2019.
 */

@Repository
public class DireccionPadronRespository implements IDireccionPadronRespository {


    @Autowired
    @Qualifier("sessionFactoryJpa")
    private SessionFactory sessionFactory;



    public List<DireccionPadron> getByNumeroDePadron(String padron){
        try(CloseableSession session = new CloseableSession(sessionFactory.openSession())){
            Query query = session.delegate().createQuery("from DireccionPadron where numeroPadron = ?");
            query.setParameter(0, padron);

            return query.list();
        }
        catch (HibernateException e){
            e.printStackTrace();
            throw e;
        }
    }

}
