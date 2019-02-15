package com.eBolivar.dao.persona;

import com.eBolivar.common.SearchObject;
import com.eBolivar.domain.Domicilio;
import com.eBolivar.domain.Persona;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PersonaRepository implements com.eBolivar.dao.interfaces.IPersonaRepository
{
    @Autowired
    @org.springframework.beans.factory.annotation.Qualifier("sessionFactoryJpa")
    private SessionFactory sessionFactory;

    public PersonaRepository() {}

    public Persona save(Persona persona)
    {
        if (persona.getDomicilio() != null) {
            for (Domicilio domicilio : persona.getDomicilio()) {
                domicilio.setPersona(persona);
            }
        }

        Object session = null;
        Transaction tx = null;
        try
        {
            session = sessionFactory.openSession();
            tx = ((Session)session).getTransaction();
            tx.begin();
            ((Session)session).saveOrUpdate(persona);
            tx.commit();

            return persona;
        }
        catch (Exception e) {
            tx.rollback();
            throw e;
        }
        finally {
            if ((session != null) || (((Session)session).isOpen())) {
                ((Session)session).close();
            }
        }
    }

    public Persona get(Integer id)
    {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Persona persona = (Persona)session.get(Persona.class, id);
            Hibernate.initialize(persona.getDomicilio());

            return persona;
        }
        catch (HibernateException e) {
            throw e;
        }
        finally {
            if ((session != null) && (session.isOpen())) {
                session.close();
            }
        }
    }

    public Persona getByCUIT(String cuit)
    {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Query query = session.createQuery("from Persona where idPersona =:cuit");
            query.setString("cuit", cuit);
            Persona persona = (Persona)query.uniqueResult();
            if ((persona != null) && (persona.getDomicilio() != null)) {
                Hibernate.initialize(persona.getDomicilio());
            }


            return persona;
        }
        catch (HibernateException e) {
            throw e;
        }
        finally {
            if ((session != null) && (session.isOpen())) {
                session.close();
            }
        }
    }

    public List<Persona> getByNombreYApellido(String nombre, String apellido)
    {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(Persona.class);

            if ((nombre != null) || (!nombre.isEmpty())) {
                criteria.add(Restrictions.ilike("nombre", "%" + nombre + "%"));
            }

            if ((apellido != null) || (!apellido.isEmpty())) {
                criteria.add(Restrictions.ilike("apellido", "%" + apellido + "%"));
            }

            return criteria.list();
        }
        catch (HibernateException e) {
            throw e;
        }
        finally {
            if ((session != null) && (session.isOpen())) {
                session.close();
            }
        }
    }

    public List<com.eBolivar.domain.PadronAsociado> getByPadron(com.eBolivar.domain.Padron padron)
    {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Query query = session.createQuery("from PadronAsociado where padron = :padron ");
            query.setParameter("padron", padron);

            return query.list();
        }
        catch (HibernateException e) {
            throw e;
        }
        finally {
            if ((session != null) && (session.isOpen())) {
                session.close();
            }
        }
    }

    public List<Persona> search(SearchObject searchObject)
    {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(Persona.class);

            if ((searchObject.getPersona().getNombre() != null) && (!searchObject.getPersona().getNombre().isEmpty())) {
                criteria.add(Restrictions.ilike("nombre", "%" + searchObject.getPersona().getNombre() + "%"));
            }

            if ((searchObject.getPersona().getApellido() != null) && (!searchObject.getPersona().getApellido().isEmpty())) {
                criteria.add(Restrictions.ilike("apellido", "%" + searchObject.getPersona().getApellido() + "%"));
            }

            if (searchObject.getPersona().getIdPersona() != null) {
                criteria.add(Restrictions.eq("idPersona", searchObject.getPersona().getIdPersona()));
            }

            criteria.setFirstResult((searchObject.getPage() - 1) * com.eBolivar.bean.Pagination.MAX_PAGE.intValue());
            criteria.setMaxResults(com.eBolivar.bean.Pagination.MAX_PAGE.intValue());

            return criteria.list();
        }
        catch (HibernateException e) {
            throw e;
        }
        finally {
            if ((session != null) && (session.isOpen())) {
                session.close();
            }
        }
    }
}