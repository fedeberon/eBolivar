package com.eBolivar.dao.usuarioLocalidad;

import com.eBolivar.bean.Pagination;
import com.eBolivar.dao.CloseableSession;
import com.eBolivar.domain.Localidad;
import com.eBolivar.domain.NotificacionPadron;
import com.eBolivar.domain.usuario.Usuario;
import com.eBolivar.domain.usuario.UsuarioLocalidad;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Damian Gallego on 28/3/2019.
 */
@Repository
public class UsuarioLocalidadRepository implements IUsuarioLocalidadRepository{

    @Autowired
    @Qualifier("sessionFactoryJpa")
    private SessionFactory sessionFactory;


    @Override
    public List<UsuarioLocalidad> findAllByLocalidad(Localidad localidad){
        try(CloseableSession session = new CloseableSession(sessionFactory.openSession())){
            Criteria criteria = session.delegate().createCriteria(UsuarioLocalidad.class);
            criteria.add(Restrictions.eq("localidadAsociada", localidad));

            return criteria.list();
        }
        catch (HibernateException e){
            throw e;
        }
    }

}
