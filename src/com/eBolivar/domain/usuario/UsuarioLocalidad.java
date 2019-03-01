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
    private Usuario usuario;

    @OneToOne
    @JoinColumn(name = "USL_LOC_ID")
    private Localidad localidadAsociada;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Localidad getLocalidadAsociada() {
        return localidadAsociada;
    }

    public void setLocalidadAsociada(Localidad localidadAsociada) {
        this.localidadAsociada = localidadAsociada;
    }
}
