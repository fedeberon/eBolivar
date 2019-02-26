package com.eBolivar.dao;

import com.eBolivar.common.SearchObject;
import com.eBolivar.dao.CloseableSession;
import com.eBolivar.domain.EstadoDeTasa;
import com.eBolivar.domain.Impuesto;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class ImpuestoDaoHibernateImpl extends HibernateDaoSupport {
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    @Qualifier("sessionFactoryJpa")
    private SessionFactory sessionFactoryJpa;

    public ImpuestoDaoHibernateImpl() {
    }

    public Impuesto getObject(String id) {
        return (Impuesto)this.getHibernateTemplate().get(Impuesto.class, id);
    }

    public List<Impuesto> getObjects() {
        return this.getHibernateTemplate().find("from Impuestos order by id");
    }

    public void removeObject(Integer id) {
        Object object = this.getHibernateTemplate().load(Impuesto.class, id);
        this.getHibernateTemplate().delete(object);
    }

    public void saveObject(Impuesto object) {
        this.getHibernateTemplate().saveOrUpdate(object);
    }

    public void saveListObject(List<Impuesto> objects, Session session) {
        int count = 0;
        Iterator var4 = objects.iterator();

        while(var4.hasNext()) {
            Impuesto i = (Impuesto)var4.next();
            session.saveOrUpdate(i);
            ++count;
            if(count % 20 == 0) {
                session.flush();
                session.clear();
            }
        }

    }

    public void updateListObject(List<Impuesto> objects, Session session) {
        int count = 0;
        Iterator var4 = objects.iterator();

        while(var4.hasNext()) {
            Impuesto i = (Impuesto)var4.next();
            session.update(i);
            ++count;
            if(count % 20 == 0) {
                session.flush();
                session.clear();
            }
        }

    }

    public void updateListObject(List<Impuesto> objects) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        int count = 0;

        try {
            Iterator e = objects.iterator();

            while(e.hasNext()) {
                Impuesto i = (Impuesto)e.next();
                session.update(i);
                ++count;
                if(count % 20 == 0) {
                    session.flush();
                    session.clear();
                }
            }

            transaction.commit();
        } catch (Exception var10) {
            var10.printStackTrace();
            transaction.rollback();
        } finally {
            if(session != null && session.isOpen()) {
                session.close();
            }

        }

    }

    public List searchObjects(SearchObject search) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Impuesto.class);
        criteria.addOrder(Order.asc("id"));
        if(search.getValor() != null && search.getValor().length() > 0 && search.getCampo() != null) {
            search.setValor(search.getValor().toUpperCase());
            MatchMode maxResults = MatchMode.ANYWHERE;
            if(search.getUbicacion() != null) {
                if(search.getUbicacion().equalsIgnoreCase("EXACT")) {
                    maxResults = MatchMode.EXACT;
                } else if(search.getUbicacion().equalsIgnoreCase("START")) {
                    maxResults = MatchMode.START;
                }
            }

            if(search.getCampo().equalsIgnoreCase("TODOS")) {
                try {
                    criteria.add(Restrictions.or(Restrictions.ilike("Id", search.getValor(), maxResults), Restrictions.idEq(Integer.valueOf(Integer.parseInt(search.getValor())))));
                } catch (NumberFormatException var6) {
                    criteria.add(Restrictions.ilike("Id", search.getValor(), maxResults));
                }
            } else if(search.getCampo().equalsIgnoreCase("id")) {
                try {
                    criteria.add(Restrictions.idEq(Integer.valueOf(Integer.parseInt(search.getValor()))));
                } catch (Exception var5) {
                    criteria.add(Restrictions.idEq(Integer.valueOf(-1)));
                }
            } else {
                criteria.add(Restrictions.ilike(search.getCampo(), search.getValor(), maxResults));
            }
        }

        if(search.getPage() > 0) {
            byte maxResults1 = 20;
            int page = search.getPage() - 1;
            return this.getHibernateTemplate().findByCriteria(criteria, page * maxResults1, maxResults1);
        } else {
            return this.getHibernateTemplate().findByCriteria(criteria);
        }
    }

    public List<Impuesto> obtenerImpuestosByCliente(String idCliente) {
        Criteria criteria = this.getSession().createCriteria(Impuesto.class).add(Restrictions.eq("idCliente", idCliente));
        return criteria.list();
    }

    public Impuesto getObjectByCUIT(String cuit) {
        return (Impuesto)this.getHibernateTemplate().find("from Impuesto where num_cuit_0=?", cuit);
    }

    public Impuesto getObjectByCodigoElectronicoPago(String codigoElectronicoPago) {
        Query q = this.getSession().createQuery("from Impuesto where codigoElectronicoPago = :codigoElectronicoPago").setParameter("codigoElectronicoPago", codigoElectronicoPago);
        return (Impuesto)q.uniqueResult();
    }

    public List<Impuesto> getByDominio(String dominio) {
        return this.getHibernateTemplate().find("from Impuesto where SUBSTR (idFactura, 4, 8) = \'" + dominio + "\' ");
    }

    public List<Impuesto> getLikeDominio(String dominio) {
        return this.getHibernateTemplate().find("from Impuesto where SUBSTR (idFactura, 4, 8) like \'%" + dominio + "%\' ");
    }

    public List<Impuesto> getByPadron(String nroPadron) {
        try {
            CloseableSession e = new CloseableSession(this.sessionFactoryJpa.openSession());
            Throwable var3 = null;

            List var5;
            try {
                Query query = e.delegate().createQuery("from Impuesto where numeroDePadron = :nroPadron");
                query.setParameter("nroPadron", nroPadron);
                var5 = query.list();
            } catch (Throwable var15) {
                var3 = var15;
                throw var15;
            } finally {
                if(e != null) {
                    if(var3 != null) {
                        try {
                            e.close();
                        } catch (Throwable var14) {
                            var3.addSuppressed(var14);
                        }
                    } else {
                        e.close();
                    }
                }

            }

            return var5;
        } catch (HibernateException var17) {
            var17.printStackTrace();
            throw var17;
        }
    }

    public List<Impuesto> getActivosByPadron(String nroPadron) {
        return this.getHibernateTemplate().find("from Impuesto where numeroDePadron =  \'" + nroPadron + "\' and estadoDeTasa.id = " + EstadoDeTasa.ACTIVO + " ORDER BY fechaGeneracion DESC ");
    }

    public List<Impuesto> getLikeNroPadron(String nroPadron) {
        return this.getHibernateTemplate().find("from Impuesto where SUBSTR (codigoDeBarra, 8, 8) like \'%" + nroPadron + "%\' ");
    }

    public List<Impuesto> getByPadronByTipoImpuesto(String nroPadron, Integer tipo) {
        return this.getHibernateTemplate().find("from Impuesto where numeroDePadron =  \'" + nroPadron + "\' and tipoImpuesto.codigo = " + tipo);
    }

    public List<Impuesto> getLikePadronByTipoImpuesto(String nroPadron, String tipo) {
        return this.getHibernateTemplate().find("from Impuesto where SUBSTR (codigoDeBarra, 8, 8) like \'%" + nroPadron + "%\' and SUBSTR (codigoDeBarra, 6, 2) like \'%" + tipo + "%\'");
    }

    public List<Impuesto> getByCodigoElectronico(String codigoElectronico) {
        return this.getHibernateTemplate().find("from Impuesto where codigoElectronicoPago like \'%" + codigoElectronico + "%\' ");
    }

    public List<Impuesto> obtenerImpuestosSinNotificacion(String nombreArchivo) {
        org.hibernate.classic.Session session = null;

        List var4;
        try {
            session = this.getSessionFactory().openSession();
            Query query = session.createQuery("from Impuesto  i where rotuloArchivo = :nombreArchivo and i.notificadoPorMail is null");
            query.setString("nombreArchivo", nombreArchivo);
            var4 = query.list();
        } finally {
            if(session != null || session.isOpen()) {
                session.close();
            }

        }

        return var4;
    }

    public JasperPrint crearReporte(JasperReport jasperStream, Map params) throws JRException {
        return JasperFillManager.fillReport(jasperStream, params, super.getSessionFactory().openSession().connection());
    }
}
