/**
 * @author Fede Beron* @version 1.0
 */

package com.eBolivar.dao;

import com.eBolivar.common.SearchObject;
import com.eBolivar.domain.EstadoDeTasa;
import com.eBolivar.domain.Impuesto;
import com.eBolivar.domain.Padron;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.hibernate.*;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;
import java.util.Map;

public class ImpuestoDaoHibernateImpl extends HibernateDaoSupport {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    @Qualifier("sessionFactoryJpa")
    private SessionFactory sessionFactoryJpa;

    public Impuesto getObject(String id) {
        return (Impuesto) getHibernateTemplate().get(Impuesto.class, id);
    }

    public List<Impuesto> getObjects() {
        return getHibernateTemplate().find("from Impuestos order by id");
    }

    public void removeObject(Integer id) {
        Object object = getHibernateTemplate().load(Impuesto.class, id);
        getHibernateTemplate().delete(object);
    }

    public void saveObject(Impuesto object) {
        getHibernateTemplate().saveOrUpdate(object);
    }

    public void saveListObject(List<Impuesto> objects, Session session) {
        int count = 0;
        for (Impuesto i : objects) {
            session.saveOrUpdate(i);

            if (++count % 20 == 0) {
                session.flush();
                session.clear();
            }
        }
    }

    public void updateListObject(List<Impuesto> objects, Session session) {
        int count = 0;

        for (Impuesto i : objects) {

            session.update(i);

            if (++count % 20 == 0) {
                session.flush();
                session.clear();
            }
        }
    }

    public void updateListObject(List<Impuesto> objects) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        int count = 0;

        try {
            for (Impuesto i : objects) {

                session.update(i);

                if (++count % 20 == 0) {
                    session.flush();
                    session.clear();
                }

            }

            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public List searchObjects(SearchObject search) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Impuesto.class);
        criteria.addOrder(Order.asc("id"));

        if (search.getValor() != null && search.getValor().length() > 0
                && search.getCampo() != null) {
            search.setValor(search.getValor().toUpperCase());
            MatchMode mm = MatchMode.ANYWHERE;
            if (search.getUbicacion() != null) {
                if (search.getUbicacion().equalsIgnoreCase("EXACT")) {
                    mm = MatchMode.EXACT;
                } else if (search.getUbicacion().equalsIgnoreCase("START")) {
                    mm = MatchMode.START;
                }
            }
            if (search.getCampo().equalsIgnoreCase("TODOS")) {
                try {
                    criteria.add(Restrictions.or(Restrictions.ilike("Id",
                            search.getValor(), mm), Restrictions.idEq(Integer
                            .parseInt(search.getValor()))));
                } catch (java.lang.NumberFormatException e) {
                    criteria.add(Restrictions.ilike("Id", search.getValor(),
                            mm));
                }
            } else if (search.getCampo().equalsIgnoreCase("id")) {
                try {
                    criteria.add(Restrictions.idEq(Integer.parseInt(search.getValor())));
                } catch (Exception e) {
                    criteria.add(Restrictions.idEq(-1));
                }
            } else {
                criteria.add(Restrictions.ilike(search.getCampo(),
                        search.getValor(), mm));
            }
        }

        if (search.getPage() > 0) {
            int maxResults = 20;
            int page = search.getPage() - 1;
            return getHibernateTemplate().findByCriteria(criteria,
                    page * maxResults, maxResults);
        } else
            return getHibernateTemplate().findByCriteria(criteria);
    }

    public List<Impuesto> obtenerImpuestosByCliente(String idCliente) {
        Criteria criteria = getSession().createCriteria(Impuesto.class)
                .add(Restrictions.eq("idCliente", idCliente));
        return criteria.list();

    }

    public Impuesto getObjectByCUIT(String cuit) {
        return (Impuesto) getHibernateTemplate().find("from Impuesto where num_cuit_0=?", cuit);
    }

    public Impuesto getObjectByCodigoElectronicoPago(String codigoElectronicoPago) {
        Query q = getSession().createQuery("from Impuesto where codigoElectronicoPago = :codigoElectronicoPago")
                .setParameter("codigoElectronicoPago", codigoElectronicoPago);
        return (Impuesto) q.uniqueResult();
    }

    public List<Impuesto> getByDominio(String dominio) {
        return getHibernateTemplate().find("from Impuesto where SUBSTR (idFactura, 4, 8) = '" + dominio + "' ");
    }

    public List<Impuesto> getLikeDominio(String dominio) {
        return getHibernateTemplate().find("from Impuesto where SUBSTR (idFactura, 4, 8) like '%" + dominio + "%' ");
    }

    public List<Impuesto> getByPadron(String nroPadron) {
//        return getHibernateTemplate().find("from Impuesto where numeroDePadron =  '" + nroPadron + "' ");

        try(CloseableSession session = new CloseableSession(sessionFactoryJpa.openSession())){
            Query query = session.delegate().createQuery("from Impuesto where numeroDePadron = :nroPadron");
            query.setParameter("nroPadron", nroPadron);
            return query.list();

        }
        catch (HibernateException e){
            e.printStackTrace();
            throw e;
        }
    }

    public List<Impuesto> getActivosByPadron(String nroPadron) {
        return getHibernateTemplate().find("from Impuesto where numeroDePadron =  '" + nroPadron + "' and estadoDeTasa.id = " + EstadoDeTasa.ACTIVO + " ORDER BY fechaGeneracion DESC ");
    }

    public List<Impuesto> getLikeNroPadron(String nroPadron) {
        return getHibernateTemplate().find("from Impuesto where SUBSTR (codigoDeBarra, 8, 8) like '%" + nroPadron + "%' ");
    }

    public List<Impuesto> getByPadronByTipoImpuesto(String nroPadron, Integer tipo) {
        return getHibernateTemplate().find("from Impuesto where numeroDePadron =  '" + nroPadron + "' and tipoImpuesto.codigo = " + tipo );
    }

    public List<Impuesto> getLikePadronByTipoImpuesto(String nroPadron, String tipo) {
        return getHibernateTemplate().find("from Impuesto where SUBSTR (codigoDeBarra, 8, 8) like '%" + nroPadron + "%' and SUBSTR (codigoDeBarra, 6, 2) like '%" + tipo + "%'");
    }

    public List<Impuesto> getByCodigoElectronico(String codigoElectronico) {
        return getHibernateTemplate().find("from Impuesto where codigoElectronicoPago like '%" + codigoElectronico + "%' ");
    }

    public List<Impuesto> obtenerImpuestosSinNotificacion(String nombreArchivo) {
        Session session = null;
        try {
            session = getSessionFactory().openSession();
            Query query = session.createQuery("from Impuesto  i where rotuloArchivo = :nombreArchivo and i.notificadoPorMail is null");
            query.setString("nombreArchivo", nombreArchivo);
            return query.list();
        }
        finally {
            if(session != null || session.isOpen()){
                session.close();
            }
        }

    }

    public JasperPrint crearReporte(JasperReport jasperStream, Map params) throws JRException {
        return JasperFillManager.fillReport(jasperStream, params, super.getSessionFactory().openSession().connection());
    }
}
