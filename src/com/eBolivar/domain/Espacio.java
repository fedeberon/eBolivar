/** @author FedeBeron * @version 1.0 */

package com.eBolivar.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Espacio implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public static final Integer OBRAS = 1;
	public static final Integer ACTIVIDAD = 2;
    private Integer id;
    private String titulo;
    private BigDecimal latitud;
    private BigDecimal longitud;
    private TipoEspacio tipoEspacio = new TipoEspacio();
    private List<Imagen> imagenes = new ArrayList();
    private String area;
    private String informacion;
    private String monto;
    private String fondos;
    private String dias;
    private String horarios;
    private String direccion;
    private MultipartFile file;
	
    

    public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getInformacion() {
		return informacion;
	}
	public void setInformacion(String informacion) {
		this.informacion = informacion;
	}
	public String getMonto() {
		return monto;
	}
	public void setMonto(String monto) {
		this.monto = monto;
	}
	public String getFondos() {
		return fondos;
	}
	public void setFondos(String fondos) {
		this.fondos = fondos;
	}
	public String getDias() {
		return dias;
	}
	public void setDias(String dias) {
		this.dias = dias;
	}
	public String getHorarios() {
		return horarios;
	}
	public void setHorarios(String horarios) {
		this.horarios = horarios;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public BigDecimal getLatitud() {
		return latitud;
	}
	public void setLatitud(BigDecimal latitud) {
		this.latitud = latitud;
	}
	public BigDecimal getLongitud() {
		return longitud;
	}
	public void setLongitud(BigDecimal longitud) {
		this.longitud = longitud;
	}
	public TipoEspacio getTipoEspacio() {
		return tipoEspacio;
	}
	public void setTipoEspacio(TipoEspacio tipoEspacio) {
		this.tipoEspacio = tipoEspacio;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<Imagen> getImagenes() {
		return imagenes;
	}
	public void setImagenes(List<Imagen> imagenes) {
		this.imagenes = imagenes;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
    
    
 
     
}
