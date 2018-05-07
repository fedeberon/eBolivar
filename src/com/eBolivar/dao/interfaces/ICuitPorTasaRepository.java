package com.eBolivar.dao.interfaces;

import com.eBolivar.domain.PadronAsociado;

public interface ICuitPorTasaRepository {
    boolean isCuitAsociadoAPadron(String padron);

    PadronAsociado save(PadronAsociado padronAsociado);

    boolean exist(PadronAsociado padronAsociado);

    PadronAsociado get(Integer id);
}
