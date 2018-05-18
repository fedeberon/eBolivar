package com.eBolivar.dao.usuario;

import com.eBolivar.dao.CloseableSession;
import com.eBolivar.dao.usuario.interfaces.IUsuarioRepository;
import com.eBolivar.domain.administradorCuenta.AdministradorCuenta;
import com.eBolivar.domain.usuario.User;
import com.eBolivar.domain.usuario.Usuario;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UsuarioRepository implements IUsuarioRepository {

    @Autowired
    @Qualifier("sessionFactoryJpa")
    private SessionFactory sessionFactory;

    @Override
    public User get(String username){
        try(CloseableSession session = new CloseableSession(sessionFactory.openSession())){
            Query query = session.delegate().createQuery("from User where username = :username");
            query.setParameter("username", username);

            return (User) query.uniqueResult();
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

    @Override
    public List<User> findAll() {
        try(CloseableSession session = new CloseableSession(sessionFactory.openSession())){
            return session.delegate().createQuery("from User").list();
        }
        catch (HibernateException e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<AdministradorCuenta> findAllAdministradoresDeCuenta() {
        try(CloseableSession session = new CloseableSession(sessionFactory.openSession())){
            return session.delegate().createQuery("from AdministradorCuenta").list();
        }
        catch (HibernateException e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void updateLikeAdministradorDeCuenta(String username) {
        Transaction tx = null;
        try(CloseableSession session = new CloseableSession(sessionFactory.openSession())){
            tx = session.delegate().beginTransaction();
            Query query = session.delegate().createSQLQuery("UPDATE USUARIOS SET TIPO_USUARIO = 'ADMINISTRADOR_DE_CUENTA' WHERE USU_USERNAME = :username");
            query.setString("username", username);
            tx.commit();
            session.delegate().flush();
        }
        catch (HibernateException e){
            tx.rollback();
            throw e;
        }
    }
}
