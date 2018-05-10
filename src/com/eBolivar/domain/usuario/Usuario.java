package com.eBolivar.domain.usuario;

import com.eBolivar.domain.rol.Rol;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.stream.Collectors;


@Entity
@Table(name = "USUARIOS")
public class Usuario implements UserDetails {


    @Id
    @Column(name = "USU_USERNAME")
    private String username;

    @Column(name = "USU_NOMBRE")
    private String nombre;

    @Column(name = "USU_APELLIDO")
    private String apellido;

    @Column(name = "USU_PASSWORD")
    private String password;

    @ManyToOne
    @JoinColumn(name = "USU_ROL_ID")
    private Rol rol;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return rol.getPermisos().stream().map(permiso -> new SimpleGrantedAuthority("ROLE_" + permiso.getNombre())).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public String toString() {
        return username + " - "  + rol.getNombre();
    }
}
