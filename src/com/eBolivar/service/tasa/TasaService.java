package com.eBolivar.service.tasa;

import com.eBolivar.dao.tasa.interfaces.ITasaRepository;
import com.eBolivar.domain.Persona;
import com.eBolivar.domain.Tasa;
import com.eBolivar.domain.TasaPersonaPadron;
import com.eBolivar.enumeradores.AnioEnum;
import com.eBolivar.service.tasa.interfaces.ITasaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TasaService implements ITasaService {

    @Autowired
    private ITasaRepository dao;

    @Override
    public List<Tasa> findAll(){
        return dao.findAll();
    }

    @Override
    public List<Tasa> findAllAnio(String anio) {
        return dao.findAllAnio(anio) ;
    }

    @Override
    public List<Tasa> findByTasaPersonaPadron(List<TasaPersonaPadron> tasasPorPadronPorPesona, AnioEnum anio) {
        List<Long> codigos = tasasPorPadronPorPesona.stream().map(TasaPersonaPadron::getCodigo).collect(Collectors.toList());
        codigos.add(1l);
        if(tasasPorPadronPorPesona != null && tasasPorPadronPorPesona.size() == 0){
            return this.findAll();
        }
        else {

            List<Tasa> tasas = dao.findByTasaPersonaPadron(codigos, anio);

            if(tasas.size() == 0 && tasas != null){
                return this.findAll();
            }
            else{
                return tasas;
            }
        }
    }
}
