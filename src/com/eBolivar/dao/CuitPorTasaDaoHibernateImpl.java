package com.eBolivar.dao;

import com.eBolivar.dao.interfaces.ICuitPorTasaRepository;
import com.eBolivar.domain.Localidad;
import com.eBolivar.domain.Padron;
import com.eBolivar.domain.PadronAsociado;
import com.eBolivar.domain.Persona;
import org.hibernate.*;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public class CuitPorTasaDaoHibernateImpl implements ICuitPorTasaRepository {
    @Autowired
    @Qualifier("sessionFactoryJpa")
    private SessionFactory sessionFactory;

    public CuitPorTasaDaoHibernateImpl() {
    }

    public boolean isCuitAsociadoAPadron(String padron) {
        try {
            CloseableSession e = new CloseableSession(this.sessionFactory.openSession());
            Throwable var3 = null;

            boolean var6;
            try {
                Query query = e.delegate().createQuery("select count(p) from PadronAsociado p where p.padron.numero = :padron");
                query.setParameter("padron", padron);
                int result = ((Number)query.uniqueResult()).intValue();
                var6 = result > 0;
            } catch (Throwable var16) {
                var3 = var16;
                throw var16;
            } finally {
                if(e != null) {
                    if(var3 != null) {
                        try {
                            e.close();
                        } catch (Throwable var15) {
                            var3.addSuppressed(var15);
                        }
                    } else {
                        e.close();
                    }
                }

            }

            return var6;
        } catch (HibernateException var18) {
            var18.printStackTrace();
            throw var18;
        }
    }

    public PadronAsociado save(PadronAsociado padronAsociado) {
        Session session = null;
        Transaction tx = null;

        PadronAsociado e;
        try {
            session = this.sessionFactory.openSession();
            tx = session.getTransaction();
            tx.begin();
            session.save(padronAsociado);
            tx.commit();
            e = padronAsociado;
        } catch (HibernateException var8) {
            tx.rollback();
            throw var8;
        } finally {
            if(session != null || session.isOpen()) {
                session.close();
            }

        }

        return e;
    }

    public boolean exist(PadronAsociado padronAsociado) {
        Session session = null;

        boolean var6;
        try {
            String sql = "SELECT COUNT(*) FROM CUIT_POR_TASAS WHERE CPT_PADRON = :padron AND PAD_PER_ID = :idPersona";
            session = this.sessionFactory.openSession();
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter("padron", padronAsociado.getPadron());
            query.setInteger("idPersona", padronAsociado.getPersona().getId().intValue());
            int result = ((Number)query.uniqueResult()).intValue();
            var6 = result > 0;
        } finally {
            if(session != null || session.isOpen()) {
                session.close();
            }

        }

        return var6;
    }

    public PadronAsociado get(Integer id) {
        try {
            CloseableSession e = new CloseableSession(this.sessionFactory.openSession());
            Throwable var3 = null;

            PadronAsociado var4;
            try {
                var4 = (PadronAsociado)e.delegate().get(PadronAsociado.class, id);
            } catch (Throwable var14) {
                var3 = var14;
                throw var14;
            } finally {
                if(e != null) {
                    if(var3 != null) {
                        try {
                            e.close();
                        } catch (Throwable var13) {
                            var3.addSuppressed(var13);
                        }
                    } else {
                        e.close();
                    }
                }

            }

            return var4;
        } catch (HibernateException var16) {
            var16.printStackTrace();
            throw var16;
        }
    }

    public Padron get(String numero) {
        try {
            CloseableSession e = new CloseableSession(this.sessionFactory.openSession());
            Throwable var3 = null;

            Padron var5;
            try {
                Query query = e.delegate().createQuery("from Padron where numero = :numero");
                query.setParameter("numero", numero);
                var5 = (Padron)query.uniqueResult();
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


    @SuppressWarnings("Duplicates")
    public List<PadronAsociado> byPersona(Persona persona) {
        try(CloseableSession e = new CloseableSession(this.sessionFactory.openSession())) {
            Query query = e.delegate().createQuery("from PadronAsociado where persona = :persona");
            query.setParameter("persona", persona);

            return query.list();
        } catch (HibernateException var17) {
            throw var17;
        }
    }

    @Override
    @SuppressWarnings("Duplicates")
    public List<PadronAsociado> byPersona(Persona persona, Collection<Localidad> localidades) {
        try(CloseableSession e = new CloseableSession(this.sessionFactory.openSession())) {
            Query query = e.delegate().createQuery("from PadronAsociado where persona = :persona and padron.localidad in (:localidades)");
            query.setParameter("persona", persona);
            query.setParameter("localidades", localidades);

            return query.list();
        } catch (HibernateException var17) {
            throw var17;
        }
    }

    public void remove(Integer id) {
        try {
            CloseableSession e = new CloseableSession(this.sessionFactory.openSession());
            Throwable var3 = null;

            try {
                Query query = e.delegate().createQuery("delete PadronAsociado where id = :id");
                query.setParameter("id", id);
                query.executeUpdate();
            } catch (Throwable var13) {
                var3 = var13;
                throw var13;
            } finally {
                if(e != null) {
                    if(var3 != null) {
                        try {
                            e.close();
                        } catch (Throwable var12) {
                            var3.addSuppressed(var12);
                        }
                    } else {
                        e.close();
                    }
                }

            }

        } catch (HibernateException var15) {
            var15.printStackTrace();
            throw var15;
        }
    }
}
