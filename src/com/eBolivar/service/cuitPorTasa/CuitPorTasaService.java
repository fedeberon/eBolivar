package com.eBolivar.service.cuitPorTasa;

import com.eBolivar.dao.interfaces.ICuitPorTasaRepository;
import com.eBolivar.domain.DireccionPadron;
import com.eBolivar.domain.PadronAsociado;
import com.eBolivar.domain.Persona;
import com.eBolivar.service.cuitPorTasa.interfaces.ICuitPorTasaService;
import java.util.List;

import com.eBolivar.service.direccionPadron.interfaces.IDireccionPadronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CuitPorTasaService implements ICuitPorTasaService
{
    @Autowired
    private ICuitPorTasaRepository dao;

    @Autowired
    private IDireccionPadronService direccionPadronService;

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

    public com.eBolivar.domain.Padron getByNumero(String numero)
    {
        return dao.get(numero);
    }

    public List<PadronAsociado> byPersona(Persona persona){
        List<PadronAsociado> padrones = dao.byPersona(persona);

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