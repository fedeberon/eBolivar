package com.eBolivar.service.declaracionJurada;

import com.eBolivar.bean.FormatoUtil;
import com.eBolivar.dao.declaracionJurada.interfaces.IDeclaracionJuradaRepository;
import com.eBolivar.domain.DeclaracionJurada;
import com.eBolivar.domain.Padron;
import com.eBolivar.domain.Persona;
import com.eBolivar.domain.TasaAsociada;
import com.eBolivar.enumeradores.EstadoDeDeclaracionJurada;
import com.eBolivar.service.declaracionJurada.interfaces.IDeclaracionJuradaService;
import com.eBolivar.service.padron.interfaces.IPadronService;
import com.eBolivar.service.persona.interfaces.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Fede Beron on 10/7/2017.
 */
@Service
public class DeclaracionJuradaService implements IDeclaracionJuradaService{

    @Autowired
    private IDeclaracionJuradaRepository dao;

    @Autowired
    private IPersonaService personaService;

    @Autowired
    private IPadronService padronService;

    @Override
    public DeclaracionJurada get(Long id){
        return dao.get(id);
    }

    @Override
    public DeclaracionJurada getUltimaDeclaracionJuradaPorPersona(Persona persona){
        return dao.getUltimaDeclaracionJuradaPorPersona(persona);
    }

    @Override
    public DeclaracionJurada save(DeclaracionJurada declaracionJurada){
        Persona persona = personaService.getByCUIT(declaracionJurada.getPersona().getIdPersona().toString());
        declaracionJurada.setPersona(persona);
        Padron padron = padronService.getByNumero(declaracionJurada.getPadron().getNumero());
        declaracionJurada.setPadron(padron);
        declaracionJurada.setFecha(LocalDateTime.now());

        return dao.save(declaracionJurada);
    }

    @Override
    public void actualizarImportesCalculados(DeclaracionJurada declaracionJurada){
        this.descontarDeduccionesSobreBaseImponible(declaracionJurada);
        this.calcularAlicuotaSobreBaseImponible(declaracionJurada);
        this.sumarTotalAPagar(declaracionJurada);

        this.save(declaracionJurada);
    }

    private void sumarTotalAPagar(DeclaracionJurada declaracionJurada) {
        Double total = declaracionJurada.getTasas().stream().mapToDouble(tasaAsociada -> tasaAsociada.getImporteCalculadoSobreBaseImponible()).sum();
        TasaAsociada tasaAsociadaConMayorPorcentajeDeAlicuota = this.obtenerTasaAsociadaConMayorPorcentajeDeAlicuota(declaracionJurada);
        total = (total > tasaAsociadaConMayorPorcentajeDeAlicuota.getTasa().getImporte() ? total : tasaAsociadaConMayorPorcentajeDeAlicuota.getTasa().getImporte());
        declaracionJurada.setTotalCalculado(FormatoUtil.formatearImporte(total));
    }

    private TasaAsociada obtenerTasaAsociadaConMayorPorcentajeDeAlicuota(DeclaracionJurada declaracionJurada){
        TasaAsociada tasaAsociadaDeAlicuotaACalcular = declaracionJurada.getTasas().stream().max((t1, t2)-> Float.compare(t1.getTasa().getAlicuta(), t2.getTasa().getAlicuta())).get();

        return tasaAsociadaDeAlicuotaACalcular;
    }

    @Override
    public void export(DeclaracionJurada declaracionJurada, ServletOutputStream outputStream) {
        dao.export(declaracionJurada, outputStream);
    }

    @Override
    public DeclaracionJurada getUltimaDeclaracionJurada(String padron, Long cuit) {
        return dao.getUltimaDeclaracionJurada(padron, cuit);
    }

    @Override
    public List<DeclaracionJurada> findAllPageable(Integer page) {
        return dao.findAllPageable(page);
    }

    private void calcularAlicuotaSobreBaseImponible(DeclaracionJurada declaracionJurada){
        declaracionJurada.getTasas().forEach(tasaAsociada -> {
            Double importeCalculado = tasaAsociada.getBaseImponible() * tasaAsociada.getTasa().getAlicuta()  / 1000;
            tasaAsociada.setImporteCalculadoSobreBaseImponible(importeCalculado);
        });
    }


    @Override
    public List<DeclaracionJurada> find(String valor){
        return dao.find(valor);
    }

    private void descontarDeduccionesSobreBaseImponible(DeclaracionJurada declaracionJurada){
        declaracionJurada.getTasas().forEach(o -> o.setBaseImponible(o.getBaseImponible() - o.getDeduccionArticulo90() - o.getDeduccionArticulo89()));
    }



}
