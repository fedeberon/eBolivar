package com.eBolivar.service;

import com.eBolivar.dao.NotificacionPorBienDaoHibernateImpl;
import com.eBolivar.domain.NotificacionPorBien;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("notificacionPorBienService")
@Transactional
public class NotificacionPorBienServiceImpl {

    NotificacionPorBienDaoHibernateImpl dao;

    public NotificacionPorBienServiceImpl() {
        dao = (NotificacionPorBienDaoHibernateImpl) PersistenceContextSingleton.getInstance().getBean("notificacionPorBienDao");
    }

    public NotificacionPorBien getObject(Integer id) {
        return dao.getObject(id);
    }

    public List<NotificacionPorBien> getObjects() {
        return dao.getObjects();
    }

    public void removeObject(Integer id) {
        dao.removeObject(id);
    }

    public void saveObject(NotificacionPorBien object) throws ConstraintViolationException {
        dao.saveObject(object);
    }

    public void saveListObject(List<NotificacionPorBien> notificacionesPorBien, Session session) {
        dao.saveListObject(notificacionesPorBien, session);
    }

    public List<NotificacionPorBien> getNotifiacionesDeTasa(Long codigoElectronicoPago){
        return dao.getNotifiacionesDeTasa(codigoElectronicoPago);
    }

}
