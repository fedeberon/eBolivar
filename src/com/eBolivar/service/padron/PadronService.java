package com.eBolivar.service.padron;

import com.eBolivar.common.SearchObject;
import com.eBolivar.dao.padron.interfaces.IPadronRepository;
import com.eBolivar.domain.DeclaracionJurada;
import com.eBolivar.domain.Impuesto;
import com.eBolivar.domain.Padron;
import com.eBolivar.service.impuesto.interfaces.IImpuestoService;
import com.eBolivar.service.padron.interfaces.IPadronService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;

@Service
public class PadronService implements IPadronService {
    @Autowired
    private IPadronRepository dao;
    @Autowired
    private IImpuestoService impuestoService;

    public PadronService() {
    }

    @Override
    public void obtenerPadronQr(Padron padron, ServletOutputStream outputStream) {
        dao.obtenerPadronQr(padron, outputStream);
    }

    public Padron save(Padron padron) {
        return this.dao.save(padron);
    }

    public Padron get(Integer id) {
        return this.dao.get(id);
    }

    public Padron getByNumero(String id) {
        return this.dao.getByNumero(id);
    }


    public Padron getPadron(String numero) {
        if(!this.impuestoService.isUnPadron(numero)) {
            throw new IllegalArgumentException("No existe el numero de padron en la base de datos.");
        } else {
            Impuesto impuesto = (Impuesto)this.impuestoService.getByPadron(numero).get(0);
            Padron padron = this.getByNumero(impuesto.getNumeroDePadron());
            if(padron == null) {
                padron = new Padron(impuesto.getNumeroDePadron(), impuesto.getTipoImpuesto());
                this.save(padron);
            }

            return padron;
        }
    }

    public List<Padron> search(SearchObject searchObject) {
        return this.dao.search(searchObject);
    }

    public Padron getByNumeroYTipo(Padron p) {
        return this.dao.getByNumeroYTipo(p);
    }


}
