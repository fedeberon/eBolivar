package com.eBolivar.service;

import java.util.List;

import com.eBolivar.dao.ReclamoDaoImpl;
import com.eBolivar.domain.Reclamos;

public class ReclamoServicesImpl {

	ReclamoDaoImpl dao;

	
	public ReclamoServicesImpl() {
		dao = new ReclamoDaoImpl();
	}

	public void ejecutarQuery(String query) {
		dao.ejecutarQuery(query);
	}
	
	public Reclamos getReclamoById(Integer codigo){
		return dao.getReclamoById(codigo);
	}
	
	public List<Reclamos> getReclamos(){
		return dao.getReclamos();
	}
	
	public Reclamos saveReclamos(Reclamos reclamo){
		return dao.saveReclamo(reclamo);
	}
	
}
