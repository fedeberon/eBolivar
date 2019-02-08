package com.eBolivar.dao.declaracionJurada;

import com.eBolivar.bean.Pagination;
import com.eBolivar.dao.CloseableSession;
import com.eBolivar.dao.declaracionJurada.interfaces.IDeclaracionJuradaRepository;
import com.eBolivar.domain.DeclaracionJurada;
import com.eBolivar.domain.PadronAsociado;
import com.eBolivar.domain.Persona;
import com.eBolivar.enumeradores.PeriodoEnum;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import org.hibernate.*;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.servlet.ServletOutputStream;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Fede Beron on 10/7/2017.
 */
@Repository
public class DeclaracionJuradaRepository implements IDeclaracionJuradaRepository{

    @Autowired
    @Qualifier("sessionFactoryJpa")
    private SessionFactory sessionFactory;

    @Override
    public DeclaracionJurada get(Long id){
        try(CloseableSession session = new CloseableSession(sessionFactory.openSession())){
            return (DeclaracionJurada) session.delegate().get(DeclaracionJurada.class, id);

        }
        catch (HibernateException e){
            e.printStackTrace();
            throw e;
        }
    }


    @Override
    public DeclaracionJurada getUltimaDeclaracionJuradaPorPersona(Persona persona){
        try(CloseableSession session = new CloseableSession(sessionFactory.openSession())){
            Query query = session.delegate().createQuery("from DeclaracionJurada order by asc where persona = :persona");
            query.setParameter("persona", persona);
            query.setMaxResults(1);

            return (DeclaracionJurada) query.uniqueResult();
        }
        catch (HibernateException e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public DeclaracionJurada save(DeclaracionJurada declaracionJurada) {
        Transaction tx = null;
        try(CloseableSession session = new CloseableSession(sessionFactory.openSession())){
            tx = session.delegate().getTransaction();
            tx.begin();
            declaracionJurada.getTasas().forEach(tasaAsociada -> tasaAsociada.setDeclaracionJurada(declaracionJurada));
            session.delegate().saveOrUpdate(declaracionJurada);
            tx.commit();

            return  declaracionJurada;
        }
        catch (HibernateException e){
            e.printStackTrace();
            tx.rollback();
            throw e;
        }
    }

    @Override
    public void export(DeclaracionJurada declaracionJurada, ServletOutputStream outputStream) {
        Map<String, Object> map = new HashMap();
        map.put("idDeclaracionJurada", declaracionJurada.getId());
        JasperReport reporte ;
        try {
            String file = declaracionJurada.getPeriodo().equals(PeriodoEnum.ANUAL) ? "/ddjj-anual.jasper" : "/ddjj.jasper";
            reporte = (JasperReport) JRLoader.loadObject(new File("/actualizaciones" + file));
            JasperPrint jp = this.crearPrint(reporte, map);
            JRPdfExporter pdfExporter = new JRPdfExporter();
            pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
            pdfExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
            pdfExporter.exportReport();
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    @Override
    public DeclaracionJurada getUltimaDeclaracionJurada(String padron, Long cuit) {
        try(CloseableSession session = new CloseableSession(sessionFactory.openSession())){
            Query query = session.delegate().createQuery("from DeclaracionJurada  where persona.idPersona = :persona and padron.numero = :padron order by Id desc");
            query.setLong("persona", cuit);
            query.setString("padron", padron);
            query.setMaxResults(1);

            return (DeclaracionJurada) query.uniqueResult();
        }
        catch (HibernateException e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<DeclaracionJurada> findAllPageable(Integer pageNumber) {
        try(CloseableSession session = new CloseableSession(sessionFactory.openSession())){
            Query query = session.delegate().createQuery("from DeclaracionJurada order by id desc");
            query.setFirstResult((pageNumber - 1) * Pagination.MAX_PAGE );
            query.setMaxResults(Pagination.MAX_PAGE);

            return query.list();
        }
        catch (HibernateException e){
            throw e;
        }
    }

    @SuppressWarnings("Duplicates")
    @Override
    public List<DeclaracionJurada> find(String valor) {
        try (CloseableSession session = new CloseableSession(sessionFactory.openSession())) {
            Criteria criteria = session.delegate().createCriteria(DeclaracionJurada.class);
            criteria.createAlias("persona", "persona").createAlias("padron", "padron");
            try{
                Long id = Long.parseLong(valor);
                criteria.add(
                        Restrictions.or(
                                Restrictions.ilike("padron.numero", valor, MatchMode.ANYWHERE),
                                    Restrictions.or(Restrictions.ilike("persona.nombre", valor, MatchMode.ANYWHERE),
                                        Restrictions.or(Restrictions.ilike("persona.apellido" , valor),
                                                Restrictions.or(Restrictions.eq("persona.idPersona", id), Restrictions.ilike("persona.numeroDocumento", valor, MatchMode.ANYWHERE))))));
            }
            catch (NumberFormatException e){
                criteria.add(
                        Restrictions.or(
                                Restrictions.ilike("padron.numero", valor, MatchMode.ANYWHERE),
                                Restrictions.or(Restrictions.ilike("persona.nombre", valor, MatchMode.ANYWHERE),
                                        Restrictions.or(Restrictions.ilike("persona.apellido" , valor), Restrictions.ilike("persona.numeroDocumento", valor, MatchMode.ANYWHERE)))));
            }

            return criteria.list();

        } catch (HibernateException e) {
            throw e;
        }
    }

    @Override
    @SuppressWarnings("Duplicates")
    public List<DeclaracionJurada> findAllPageable(String valor, Integer pageNumber) {
        try (CloseableSession session = new CloseableSession(sessionFactory.openSession())) {
            Criteria criteria = session.delegate().createCriteria(DeclaracionJurada.class);
            criteria.createAlias("persona", "persona").createAlias("padron", "padron");
            try{
                Long id = Long.parseLong(valor);
                criteria.add(
                        Restrictions.or(
                                Restrictions.ilike("padron.numero", valor, MatchMode.ANYWHERE),
                                Restrictions.or(Restrictions.ilike("persona.nombre", valor, MatchMode.ANYWHERE),
                                        Restrictions.or(Restrictions.ilike("persona.apellido" , valor),
                                                Restrictions.or(Restrictions.eq("persona.idPersona", id), Restrictions.ilike("persona.numeroDocumento", valor, MatchMode.ANYWHERE))))));
            }
            catch (NumberFormatException e){
                criteria.add(
                        Restrictions.or(
                                Restrictions.ilike("padron.numero", valor, MatchMode.ANYWHERE),
                                Restrictions.or(Restrictions.ilike("persona.nombre", valor, MatchMode.ANYWHERE),
                                        Restrictions.or(Restrictions.ilike("persona.apellido" , valor), Restrictions.ilike("persona.numeroDocumento", valor, MatchMode.ANYWHERE)))));
            }

            criteria.setFirstResult((pageNumber - 1) * Pagination.MAX_PAGE );
            criteria.setMaxResults(Pagination.MAX_PAGE);

            return criteria.list();

        } catch (HibernateException e) {
            throw e;
        }
    }

    @Override
    public void imprimirAcuseDeRecibo(DeclaracionJurada declaracionJurada, ServletOutputStream outputStream) {
        Map<String, Object> map = new HashMap();
        map.put("idDeclaracionJurada", declaracionJurada.getId());
        JasperReport reporte;
        try {
            reporte = (JasperReport) JRLoader.loadObject(new File("/actualizaciones/acuseReciboDDJJ.jasper"));
            JasperPrint jp = this.crearPrint(reporte, map);
            JRPdfExporter pdfExporter = new JRPdfExporter();
            pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
            pdfExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
            pdfExporter.exportReport();
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<DeclaracionJurada> getByPadronAsociado(PadronAsociado padronAsociado, Integer pageNumber) {
        try(CloseableSession session = new CloseableSession(sessionFactory.openSession())){
            Query query = session.delegate().createQuery("from DeclaracionJurada where padron = :padron and persona = :persona order by id desc");
            query.setParameter("padron", padronAsociado.getPadron());
            query.setParameter("persona", padronAsociado.getPersona());
            query.setFirstResult((pageNumber - 1) * Pagination.MAX_PAGE );
            query.setMaxResults(Pagination.MAX_PAGE);

            return query.list();
        }
        catch (HibernateException e){
            throw e;
        }
    }

    @Override
    public List<DeclaracionJurada> getByPersona(Persona persona) {
        try(CloseableSession session = new CloseableSession(sessionFactory.openSession())){
            Query query = session.delegate().createQuery("from DeclaracionJurada where persona = :persona order by id desc");
            query.setParameter("persona", persona);

            return query.list();
        }
        catch (HibernateException e){
            throw e;
        }
    }


    private JasperPrint crearPrint(JasperReport jr, Map<String , Object> mapa) {
        try (CloseableSession session = new CloseableSession(sessionFactory.openSession())) {
            return JasperFillManager.fillReport(jr, mapa, session.delegate().connection());

        } catch (JRException e) {
            e.printStackTrace();
            return null;
        }
    }
}



























