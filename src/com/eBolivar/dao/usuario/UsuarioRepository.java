package com.eBolivar.dao.usuario;

import com.eBolivar.dao.CloseableSession;
import com.eBolivar.dao.usuario.interfaces.IUsuarioRepository;
import com.eBolivar.domain.usuario.Usuario;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;



@Repository
public class UsuarioRepository implements IUsuarioRepository {


    @Autowired
    @Qualifier("sessionFactoryJpa")
    private SessionFactory sessionFactory;

    @Override
    public Usuario get(String username){
        try(CloseableSession session = new CloseableSession(sessionFactory.openSession())){
            return (Usuario) session.delegate().get(Usuario.class, username);
        }
        catch (HibernateException e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Usuario save(Usuario usuario) {
        Transaction tx = null;
        try(CloseableSession session = new CloseableSession(sessionFactory.openSession())){
            tx = session.delegate().beginTransaction();
            session.delegate().saveOrUpdate(usuario);
            tx.commit();

            return usuario;
        }
        catch (HibernateException e){
            tx.rollback();
            throw e;
        }






    }
}
