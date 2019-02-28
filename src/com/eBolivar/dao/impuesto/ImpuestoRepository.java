package com.eBolivar.dao.impuesto;

import com.eBolivar.dao.interfaces.IImpuestoRepository;
import com.eBolivar.domain.Impuesto;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ImpuestoRepository implements IImpuestoRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public ImpuestoRepository() {
    }

    public boolean isUnPadron(String padron) {
        Session session = null;

        boolean var5;
        try {
            session = this.sessionFactory.openSession();
            SQLQuery e = session.createSQLQuery("select count(*) from IMPUESTOS i  where i.IMP_NUMERO_PADRON = :padron");
            e.setString("padron", padron);
            int result = ((Number)e.uniqueResult()).intValue();
            var5 = result > 0;
        } catch (HibernateException var9) {
            var9.printStackTrace();
            throw var9;
        } finally {
            if(session != null || session.isOpen()) {
                session.close();
            }

        }

        return var5;
    }

    public List<Impuesto> getByPadron(String numeroDePadron) {
        Session session = null;

        List var4;
        try {
            session = this.sessionFactory.openSession();
            Query e = session.createQuery("from Impuesto where numeroDePadron = :numeroDePadron ");
            e.setString("numeroDePadron", numeroDePadron);
            var4 = e.list();
        } catch (HibernateException var8) {
            throw var8;
        } finally {
            if(session != null && session.isOpen()) {
                session.close();
            }

        }

        return var4;
    }
}