package com.eBolivar.service.impuesto;

import com.eBolivar.dao.interfaces.IImpuestoRepository;
import com.eBolivar.domain.Impuesto;
import com.eBolivar.service.impuesto.interfaces.IImpuestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("impuestoServicio")
public class ImpuestoService implements IImpuestoService {

    @Autowired
    private IImpuestoRepository dao;

    @Override
    public boolean isUnPadron(String padron){
        return dao.isUnPadron(padron);
    }

    @Override
    public List<Impuesto> getByPadron(String padron) {
        List<Impuesto> impuestos = dao.getByPadron(padron);
        impuestos.forEach(this::parsearImporte);

        return impuestos;
    }

    public void parsearImporte(Impuesto i) {
        int importe = Integer.parseInt(i.getImporte1reVencimiento());
        String strImporte = String.valueOf(importe);
        String entero = strImporte.substring(0, strImporte.length() - 2);
        if (entero.length() > 3) {
            entero = entero.substring(0, entero.length() - 3) + "," + entero.substring(entero.length() - 3, entero.length());
        }
        String decimal = strImporte.substring(strImporte.length() - 2, strImporte.length());
        i.setImporte1reVencimiento("$ " + entero + "." + decimal);
    }
}
