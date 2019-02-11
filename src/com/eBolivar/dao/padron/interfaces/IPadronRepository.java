package com.eBolivar.dao.padron.interfaces;

import com.eBolivar.common.SearchObject;
import com.eBolivar.domain.Padron;
import java.util.List;

public interface IPadronRepository {
    Padron save(Padron var1);

    Padron get(Integer var1);

    Padron getByNumero(String var1);

    List<Padron> search(SearchObject var1);

    Padron getByNumeroYTipo(Padron var1);
}
