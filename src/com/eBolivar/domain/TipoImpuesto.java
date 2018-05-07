package com.eBolivar.domain;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name ="TIPO_IMPUESTO")
public class TipoImpuesto implements Serializable {

    @Id
    @Column(name ="TI_ID")
    private Integer codigo;

    @Column(name ="TI_NOMBRE")
    private String nombre;

    @Transient
    private String patronBusqueda;

    @Transient
    private String estado;

    @Transient
    private String comentarios;

    @Transient
    private String color;

    @Transient
    private String urlImg;

	public String getUrlImg() {
		return urlImg;
	}

    public void setUrlImg(String urlImg) {
		this.urlImg = urlImg;
	}

    public String getComentarios() {
		return comentarios;
	}

    public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

    public String getColor() {
		return color;
	}

    public void setColor(String color) {
		this.color = color;
	}

    public String getEstado() {
		return estado;
	}

    public void setEstado(String estado) {
		this.estado = estado;
	}

    public String getPatronBusqueda() {
		return patronBusqueda;
	}

    public void setPatronBusqueda(String patronBusqueda) {
		this.patronBusqueda = patronBusqueda;
	}

    public Integer getCodigo() {
		return codigo;
	}

    public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

    public String getNombre() {
		return nombre;
	}

    public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
