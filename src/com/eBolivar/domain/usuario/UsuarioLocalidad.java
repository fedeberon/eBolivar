package com.eBolivar.domain.usuario;

import com.eBolivar.domain.Localidad;
import com.eBolivar.domain.administradorCuenta.AdministradorCuenta;

import javax.persistence.*;

/**
 * Created by Lucas Cortés on 14/02/2019.
 */

@Entity
@Table(name ="USUARIO_LOCALIDAD")
public class UsuarioLocalidad {

    @Id
    @Column(name = "USL_ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "USL_USU_USERNAME")
    private AdministradorCuenta administradorCuenta;

    @OneToOne
    @JoinColumn(name = "USL_LOC_ID")
    private Localidad localidad;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AdministradorCuenta getAdministradorCuenta() {
        return administradorCuenta;
    }

    public void setAdministradorCuenta(AdministradorCuenta administradorCuenta) {
        this.administradorCuenta = administradorCuenta;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }
}
