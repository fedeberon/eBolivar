package com.eBolivar.dao;

import com.eBolivar.dao.interfaces.ICuitPorTasaRepository;
import com.eBolivar.domain.PadronAsociado;
import com.eBolivar.domain.Persona;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Fede Beron on 27/2/2017.
 */
@Repository
public class CuitPorTasaDaoHibernateImpl implements ICuitPorTasaRepository {

    @Autowired
    @Qualifier("sessionFactoryJpa")
    private SessionFactory sessionFactory;


    @Override
    public boolean isCuitAsociadoAPadron(String padron) {
        try(CloseableSession session = new CloseableSession(sessionFactory.openSession())){
            Query query =  session.delegate().createQuery("select count(p) from PadronAsociado p where p.padron.numero = :padron");
            query.setParameter("padron", padron);

            int result = ((Number) query.uniqueResult()).intValue();

            return result > 0;
        }
        catch (HibernateException e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public PadronAsociado save(PadronAsociado padronAsociado) {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.getTransaction();
            tx.begin();
            session.save(padronAsociado);
            tx.commit();

            return padronAsociado;
        }catch (HibernateException e){
            tx.rollback();
            throw e;
        }
        finally {
            if (session != null || session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public boolean exist(PadronAsociado padronAsociado) {
        Session session = null;
        try {
            String sql = "SELECT COUNT(*) FROM CUIT_POR_TASAS WHERE CPT_PADRON = :padron AND PAD_PER_ID = :idPersona";
            session = sessionFactory.openSession();
            Query query = session.createSQLQuery(sql);
            query.setParameter("padron", padronAsociado.getPadron());
            query.setInteger("idPersona", padronAsociado.getPersona().getId());
            int result = ((Number) query.uniqueResult()).intValue();

            return result > 0;

        } finally {
            if (session != null || session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public PadronAsociado get(Integer id) {
        try(CloseableSession session = new CloseableSession(sessionFactory.openSession())){
            return (PadronAsociado) session.delegate().get(PadronAsociado.class, id);
        }
        catch (HibernateException e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<PadronAsociado> byPersona(Persona persona) {
        try(CloseableSession session = new CloseableSession(sessionFactory.openSession())){
            Query query =  session.delegate().createQuery("from PadronAsociado where persona = :persona");
            query.setParameter("persona", persona);

            return query.list();
        }
        catch (HibernateException e){
            e.printStackTrace();
            throw e;
        }
    }
}
