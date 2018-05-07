package com.eBolivar.dao.credencialAutenticacionAFIP;

import com.eBolivar.domain.LoginTicketResponse;
import com.eBolivar.dao.interfaces.ICredencialAutenticacionAFIPRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class CredencialAutenticacionAFIPRepository implements ICredencialAutenticacionAFIPRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public LoginTicketResponse get(){
        Session session  = null;
        try {
            session = sessionFactory.openSession();
            Query query = session.createQuery("from LoginTicketResponse where fechaDeCaducacion > :ahora");
            query.setDate("ahora", new Date());

            return (LoginTicketResponse) query.uniqueResult();

        }
        finally {
            if(session != null || session.isOpen()){
                session.close();
            }
        }
    }

    @Override
    public LoginTicketResponse save(LoginTicketResponse loginTicketResponse){
        Session session  = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.getTransaction();
            tx.begin();
            session.save(loginTicketResponse);
            tx.commit();

            return loginTicketResponse;
        }
        catch (Exception e){
            tx.rollback();
            throw e;
        }
        finally {
            if(session != null || session.isOpen()){
                session.close();
            }
        }
    }
}