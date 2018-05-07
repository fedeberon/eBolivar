package com.eBolivar.service;

import java.util.List;

import org.hibernate.Session;

import com.eBolivar.dao.DetalleFacturaDaoHibernateImpl;
import com.eBolivar.domain.DetalleFactura;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("detalleFacturaService")
@Transactional
public class DetalleFacturaServiceImpl {

	DetalleFacturaDaoHibernateImpl dao;
	
	public DetalleFacturaServiceImpl(){
		
		dao = (DetalleFacturaDaoHibernateImpl) PersistenceContextSingleton.getInstance().getBean("detalleFacturaDao");
		
	}
	
	public DetalleFactura getObject(Integer id){
		return dao.getObject(id);
	}
	
	public void saveObject(DetalleFactura object){
		dao.saveObject(object);
	}

	public void saveListObject(List<DetalleFactura> detallesFactura, Session session) {
		dao.saveListObject(detallesFactura,session);
	}

    public List<DetalleFactura> getDetalleDeTasaPorNumeroDeFactura(String idFactura){
        return dao.getDetalleDeTasaPorNumeroDeFactura(idFactura);
    }
}
