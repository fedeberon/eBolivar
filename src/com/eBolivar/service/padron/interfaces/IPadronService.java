package com.eBolivar.service.padron.interfaces;

import com.eBolivar.common.SearchObject;
import com.eBolivar.domain.Padron;
import java.util.List;

public interface IPadronService {
    Padron save(Padron var1);

    Padron get(Integer var1);

    Padron getByNumero(String var1);

    Padron getPadron(String var1);

    List<Padron> search(SearchObject var1);

    Padron getByNumeroYTipo(Padron var1);
}
