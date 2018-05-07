/** @author FedeBeron * @version 1.0 */

package com.eBolivar.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.eBolivar.common.SearchObject;
import com.eBolivar.dao.EspacioDaoHibernateImpl;
import com.eBolivar.domain.Espacio;
import com.eBolivar.domain.Imagen;

public class EspacioServiceImpl {

	private EspacioDaoHibernateImpl dao = null;

	public EspacioServiceImpl() {
		dao = (EspacioDaoHibernateImpl) PersistenceContextSingleton
				.getInstance().getBean("espacioDao");
	}

	public Espacio getObject(Integer id) {
		return dao.getObject(id);
	}

	public List<Espacio> getObjects() {
		return dao.getObjects();
	}

	public void removeObject(Integer id) {
		dao.removeObject(id);
	}

	public void saveObject(Espacio object) throws ConstraintViolationException {
		dao.saveObject(object);
	}

	public List searchObjects(SearchObject search) {
		return dao.searchObjects(search);
	}

	public List obtenerEspacios(Integer tipo) {
		return dao.obtenerEspacios(tipo);
	}

	public List obtenerEspacios(Integer tipo ,String area) {
		return dao.obtenerEspacios(tipo , area);
	}

	public List<Espacio> getObjects(Integer tipoDeObra , String area) {
		return dao.getObjects(tipoDeObra, area);
	}

	public List getDistinctAreas(Integer tipoEspacio) {
		return dao.getDistinctAreas(tipoEspacio);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JSONObject obtenerLugares(Integer tipoEspacio) {
		List areas = getDistinctAreas(tipoEspacio);
		
		String icono = tipoEspacio  ==  Espacio.OBRAS ? "/img/construction_red.png" : "/img/tasks.png";
		
		JSONObject jTipoDeLugar = new JSONObject();
		JSONArray jTipoDeLugares = new JSONArray();
		
		
		for(Object area : areas){
			JSONObject jArea = new JSONObject();
			jArea.put("title", area.toString());
			jArea.put("key", tipoEspacio );
			jArea.put("isFolder", true );
			jArea.put("icon", icono );
			
			
			List<Espacio> espaciosPorArea = getObjects( tipoEspacio , area.toString());
					JSONArray jEspacios = new JSONArray();
					for(Espacio espacio : espaciosPorArea){
						JSONObject jEspacio = new JSONObject();
						jEspacio.put("title", espacio.getTitulo());
						jEspacio.put("key", espacio.getId());
						jEspacio.put("href", "/eBolivar/espacio/show?idEspacio=" + espacio.getId());
						jEspacio.put("TARGET", "_blank");
						jEspacio.put("icon", icono);
						jEspacio.put("hideCheckbox", true );
						jEspacios.add(jEspacio);
					}
			jArea.put("children", jEspacios);
			jTipoDeLugares.add(jArea);
		}

		jTipoDeLugar.put("title", tipoEspacio  ==  Espacio.OBRAS ? "OBRAS" : "ACTIVIDADES");
		jTipoDeLugar.put("key", tipoEspacio );
		jTipoDeLugar.put("children", jTipoDeLugares);
		jTipoDeLugar.put("isFolder", true );
		jTipoDeLugar.put("hideCheckbox", true );
		jTipoDeLugar.put("icon", icono );
		jTipoDeLugar.put("expand", true );

		return jTipoDeLugar;
	}
	

	public void guardarImagen(Espacio espacio) throws IOException {
		if(espacio.getFile()!= null && espacio.getFile().isEmpty()) return ;
			 
			String destinationFolder = "c:/imagenes/espacios/" + espacio.getId() + "/";
			File destinationFile = new File(destinationFolder);

			if (!destinationFile.exists()) {
				destinationFile.mkdirs();
			}
			destinationFile = new File( destinationFolder + espacio.getFile().getOriginalFilename());
			espacio.getFile().transferTo(destinationFile); // guardo el
			
			Imagen imagen = new Imagen();
			
			imagen.setPath("/img/espacios/" + espacio.getId() + "/" + espacio.getFile().getOriginalFilename());
			
			espacio.getImagenes().add(imagen);
			
	}
 
}
