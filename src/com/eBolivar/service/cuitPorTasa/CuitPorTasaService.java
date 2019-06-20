package com.eBolivar.service.cuitPorTasa;

import com.eBolivar.dao.interfaces.ICuitPorTasaRepository;
import com.eBolivar.domain.*;
import com.eBolivar.domain.usuario.User;
import com.eBolivar.domain.usuario.Usuario;
import com.eBolivar.domain.usuario.UsuarioLocalidad;
import com.eBolivar.service.cuitPorTasa.interfaces.ICuitPorTasaService;

import java.util.ArrayList;
import java.util.List;

import com.eBolivar.service.direccionPadron.interfaces.IDireccionPadronService;
import com.eBolivar.service.usuario.interfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CuitPorTasaService implements ICuitPorTasaService
{
    @Autowired
    private ICuitPorTasaRepository dao;

    @Autowired
    private IDireccionPadronService direccionPadronService;

    @Autowired
    private IUsuarioService usuarioService;

    public CuitPorTasaService() {}

    public boolean isCuitAsociadoAPadron(String padron)
    {
        return dao.isCuitAsociadoAPadron(padron);
    }

    public PadronAsociado save(PadronAsociado padronAsociado)
    {
        return dao.save(padronAsociado);
    }

    public boolean exist(PadronAsociado padronAsociado)
    {
        return dao.exist(padronAsociado);
    }

    public PadronAsociado get(Integer id)
    {
        return dao.get(id);
    }

    public Padron getByNumero(String numero)
    {
        return dao.get(numero);
    }

    public List<PadronAsociado> byPersona(Persona persona){
        User usuario = usuarioService.getAutenticate();
        List<UsuarioLocalidad> localidadesDelUsuario = usuarioService.getLocalidades(usuario);
        List<Localidad> localidades = new ArrayList<>();
        localidadesDelUsuario.forEach(usuarioLocalidad -> localidades.add(usuarioLocalidad.getLocalidadAsociada()));

        List<PadronAsociado> padrones = localidades.isEmpty() ? dao.byPersona(persona) : dao.byPersona(persona, localidades);

        padrones.forEach(padronAsociado -> {
            List<DireccionPadron> direcciones = direccionPadronService.getByNumeroDePadron(padronAsociado.getPadron().getNumero());
            padronAsociado.setDireccionesDelPadron(direcciones);
        });

        return padrones;
    }

    public void remove(Integer id) {
        dao.remove(id);
    }
}