package com.eBolivar.service.padron;

import com.eBolivar.dao.padron.interfaces.IPadronRepository;
import com.eBolivar.domain.Impuesto;
import com.eBolivar.domain.Padron;
import com.eBolivar.service.impuesto.interfaces.IImpuestoService;
import com.eBolivar.service.padron.interfaces.IPadronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Fede Beron on 11/7/2017.
 */
@Service
public class PadronService implements IPadronService {

    @Autowired
    private IPadronRepository dao;

    @Autowired
    private IImpuestoService impuestoService;

    @Override
    public Padron save(Padron padron) {
        return dao.save(padron);
    }

    @Override
    public Padron get(Integer id) {
        return dao.get(id);
    }

    @Override
    public Padron getByNumero(String id) {
        return dao.getByNumero(id);
    }

    @Override
    public Padron getPadron(String numero) {
        if(!impuestoService.isUnPadron(numero)) throw new IllegalArgumentException("No existe el numero de padron en la base de datos.");
        Impuesto impuesto = impuestoService.getByPadron(numero).get(0);
        Padron padron = this.getByNumero(impuesto.getNumeroDePadron());
        if(padron == null) {
            padron = new Padron(impuesto.getNumeroDePadron(), impuesto.getTipoImpuesto());
            this.save(padron);
        }

        return padron;
    }


}
