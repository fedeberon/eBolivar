package com.eBolivar.service.impuesto.interfaces;

import com.eBolivar.domain.Impuesto;

import java.util.List;

public interface IImpuestoService {

    boolean isUnPadron(String padron);

    List<Impuesto> getByPadron(String padron);
}
