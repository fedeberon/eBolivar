package com.eBolivar.dao.padron;

import com.eBolivar.bean.Pagination;
import com.eBolivar.common.SearchObject;
import com.eBolivar.dao.CloseableSession;
import com.eBolivar.dao.padron.interfaces.IPadronRepository;
import com.eBolivar.domain.Padron;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import org.hibernate.*;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PadronRepository implements IPadronRepository {
    @Autowired
    @Qualifier("sessionFactoryJpa")
    private SessionFactory sessionFactory;

    public PadronRepository() {
    }

    public Padron save(Padron padron) {
        Transaction tx = null;

        try {
            CloseableSession e = new CloseableSession(this.sessionFactory.openSession());
            Throwable var4 = null;

            Padron var5;
            try {
                tx = e.delegate().getTransaction();
                tx.begin();
                e.delegate().saveOrUpdate(padron);
                tx.commit();
                var5 = padron;
            } catch (Throwable var15) {
                var4 = var15;
                throw var15;
            } finally {
                if (e != null) {
                    if (var4 != null) {
                        try {
                            e.close();
                        } catch (Throwable var14) {
                            var4.addSuppressed(var14);
                        }
                    } else {
                        e.close();
                    }
                }

            }

            return var5;
        } catch (HibernateException var17) {
            var17.printStackTrace();
            tx.rollback();
            throw var17;
        }
    }


    public Padron get(Integer id) {
        try {
            CloseableSession e = new CloseableSession(this.sessionFactory.openSession());
            Throwable var3 = null;

            Padron var4;
            try {
                var4 = (Padron) e.delegate().get(Padron.class, id);
            } catch (Throwable var14) {
                var3 = var14;
                throw var14;
            } finally {
                if (e != null) {
                    if (var3 != null) {
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

    public Padron getByNumero(String numero) {
        try {
            CloseableSession e = new CloseableSession(this.sessionFactory.openSession());
            Throwable var3 = null;

            Padron var5;
            try {
                Query query = e.delegate().createQuery("from Padron where numero = :numero");
                query.setString("numero", numero);
                var5 = (Padron) query.uniqueResult();
            } catch (Throwable var15) {
                var3 = var15;
                throw var15;
            } finally {
                if (e != null) {
                    if (var3 != null) {
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


    public List<Padron> search(SearchObject searchObject) {
        Session session = null;

        List var4;
        try {
            session = this.sessionFactory.openSession();
            Criteria e = session.createCriteria(Padron.class);
            if (searchObject.getPadron().getNumero() != null && !searchObject.getPadron().getNumero().isEmpty()) {
                e.add(Restrictions.ilike("numero", "%" + searchObject.getPadron().getNumero() + "%"));
            }

            e.setFirstResult((searchObject.getPage() - 1) * Pagination.MAX_PAGE.intValue());
            e.setMaxResults(Pagination.MAX_PAGE.intValue());
            var4 = e.list();
        } catch (HibernateException var8) {
            throw var8;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }

        }

        return var4;
    }

    public Padron getByNumeroYTipo(Padron p) {
        try {
            CloseableSession e = new CloseableSession(this.sessionFactory.openSession());
            Throwable var3 = null;

            Padron var5;
            try {
                Query query = e.delegate().createQuery("from Padron where numero = :numero and tipoImpuesto.id = :idTipo");
                query.setString("numero", p.getNumero());
                query.setParameter("idTipo", p.getTipoImpuesto().getCodigo());
                var5 = (Padron) query.uniqueResult();
            } catch (Throwable var15) {
                var3 = var15;
                throw var15;
            } finally {
                if (e != null) {
                    if (var3 != null) {
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

    @Override
    public void obtenerPadronQr(Padron padron, ServletOutputStream outputStream) {
        Map<String, Object> map = new HashMap();
        map.put("numPadron", padron.getNumero());
        JasperReport reporte;
        try {
            reporte = (JasperReport) JRLoader.loadObject(new File("/actualizaciones/pruebaQr.jasper"));
            JasperPrint jp = this.crearPrint(reporte, map);
//            JRPdfExporter pdfExporter = new JRPdfExporter();
//            pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
//            pdfExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
//            pdfExporter.exportReport();
            extractPrintImage(outputStream, jp);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    private JasperPrint crearPrint(JasperReport jr, Map<String, Object> mapa) {
        try (CloseableSession session = new CloseableSession(sessionFactory.openSession())) {
            return JasperFillManager.fillReport(jr, mapa, session.delegate().connection());

        } catch (JRException e) {
            e.printStackTrace();
            return null;
        }
    }


    private void extractPrintImage(OutputStream ouputStream, JasperPrint print) {
        try {
            DefaultJasperReportsContext.getInstance();
            JasperPrintManager printManager = JasperPrintManager.getInstance(DefaultJasperReportsContext.getInstance());

            BufferedImage rendered_image = null;
            rendered_image = (BufferedImage) printManager.printPageToImage(print, 0, 1.6f);
            ImageIO.write(rendered_image, "png", ouputStream);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}