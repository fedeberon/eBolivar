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
        return dao.getByPadron(padron);
    }

}
