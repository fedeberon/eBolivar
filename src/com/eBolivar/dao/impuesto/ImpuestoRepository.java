package com.eBolivar.dao.impuesto;

import com.eBolivar.dao.interfaces.IImpuestoRepository;
import com.eBolivar.domain.Impuesto;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ImpuestoRepository implements IImpuestoRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean isUnPadron(String padron){
        Session session = null;
        try{
            session = sessionFactory.openSession();
            Query query = session.createSQLQuery("select count(*) from Impuestos i  where i.IMP_NUMERO_PADRON = :padron");
            query.setString("padron", padron);
            int result = ((Number) query.uniqueResult()).intValue();

            return result > 0;
        }catch (HibernateException e){
            e.printStackTrace();
            throw e;
        }
        finally {
            if(session != null || session.isOpen()){
                session.close();
            }
        }
    }

    @Override
    public List<Impuesto> getByPadron(String numeroDePadron) {
        Session session = null;
        try{
            session = sessionFactory.openSession();
            Query query = session.createQuery("from Impuesto where numeroDePadron = :numeroDePadron ");
            query.setString("numeroDePadron", numeroDePadron);

            return query.list();
        }
        catch (HibernateException e){
            throw e;
        }
        finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }



}
