package com.eBolivar.dao.interfaces;


import com.eBolivar.domain.Impuesto;

import java.util.List;

public interface IImpuestoRepository {
    boolean isUnPadron(String padron);

    List<Impuesto> getByPadron(String numeroDePadron);
}
