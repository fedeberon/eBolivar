package com.eBolivar.dao.persona;

import com.eBolivar.dao.interfaces.IPersonaRepository;
import com.eBolivar.domain.Domicilio;
import com.eBolivar.domain.PadronAsociado;
import com.eBolivar.domain.Persona;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonaRepository implements IPersonaRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Persona save(Persona persona){
        for(Domicilio domicilio : persona.getDomicilio()){
            domicilio.setPersona(persona);
        }

        Session session  = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.getTransaction();
            tx.begin();
            session.save(persona);
            tx.commit();

            return persona;
        }
        catch (Exception e){
            tx.rollback();
            System.out.print("===================>" + e);
            throw e;
        }
        finally {
            if(session != null || session.isOpen()){
                session.close();
            }
        }
    }

    @Override
    public Persona get(Integer id){
        Session session  = null;
        try{
            session = sessionFactory.openSession();
            Persona persona = (Persona) session.get(Persona.class, id);
            Hibernate.initialize(persona.getDomicilio());

            return persona;
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

    @Override
    public Persona getByCUIT(String cuit){
        Session session = null;
        try{
            session = sessionFactory.openSession();
            Query query = session.createQuery("from Persona where idPersona =:cuit");
            query.setString("cuit" , cuit);

            return (Persona) query.uniqueResult();
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


    @Override
    public List<Persona> getByNombreYApellido(String nombre, String apellido){
        Session session = null;
        try{
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(Persona.class);

            if(nombre != null || !nombre.isEmpty()){
                criteria.add(Restrictions.ilike("nombre" , "%" + nombre + "%"));
            }

            if(apellido != null || !apellido.isEmpty()){
                criteria.add(Restrictions.ilike("apellido", "%" + apellido + "%"));
            }

            return criteria.list();
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

    @Override
    public List<PadronAsociado> getByPadron(String padron){
        Session session = null;
        try{
            session = sessionFactory.openSession();
            Query query = session.createQuery("from PadronAsociado where padron = :padron ");
            query.setString("padron", padron);

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
