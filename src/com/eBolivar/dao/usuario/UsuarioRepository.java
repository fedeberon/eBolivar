package com.eBolivar.dao.usuario;

import com.eBolivar.bean.Pagination;
import com.eBolivar.dao.CloseableSession;
import com.eBolivar.dao.usuario.interfaces.IUsuarioRepository;
import com.eBolivar.domain.DeclaracionJurada;
import com.eBolivar.domain.administradorCuenta.AdministradorCuenta;
import com.eBolivar.domain.usuario.User;
import com.eBolivar.domain.usuario.Usuario;
import org.hibernate.*;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

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
            return session.delegate().createQuery("from Usuario").list();
        }
        catch (HibernateException e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<User> findPageable(Integer pageNumber) {
        try(CloseableSession session = new CloseableSession(sessionFactory.openSession())){
            Query query = session.delegate().createQuery("from Usuario");
            query.setFirstResult((pageNumber - 1) * Pagination.MAX_PAGE );
            query.setMaxResults(Pagination.MAX_PAGE);

            return query.list();
        }
        catch (HibernateException e){
            throw e;
        }
    }

    @Override
    public List<AdministradorCuenta> findAdministradorCuenta(String valor, Integer pageNumber){
        try (CloseableSession session = new CloseableSession(sessionFactory.openSession())) {
            Criteria criteria = session.delegate().createCriteria(AdministradorCuenta.class);
            criteria.createAlias("rol" , "rol");
           //hacer if de vacio
            criteria.add(
                          Restrictions.or(Restrictions.ilike("username", valor, MatchMode.ANYWHERE),
                                Restrictions.or(Restrictions.ilike("nombre", valor, MatchMode.ANYWHERE),
                                        Restrictions.or( Restrictions.ilike("apellido" , valor), Restrictions.ilike("rol.nombre", valor)))));

            criteria.setFirstResult((pageNumber - 1) * Pagination.MAX_PAGE );
            criteria.setMaxResults(Pagination.MAX_PAGE);

            return criteria.list();

        } catch (HibernateException e) {
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
            query.executeUpdate();

            tx.commit();
        }
        catch (HibernateException e){
            tx.rollback();
            throw e;
        }
    }

}
